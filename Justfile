# vim: foldmethod=marker foldmarker={{{,}}}:
# vim: set ft=just:

set shell := ["bash", "-uc"]

GHIDRA_VERSION := "11.0.3"
GHIDRA_DATE := "20240410"

# Justfile Help message {{{

gold:=`tput setaf 3`
reset:=`tput setaf 5`
format:="'%4s"+gold+"%-20s"+reset+"%s\\n' ''"

@default:
    printf "\n"
    printf {{format}} "Ghidra Version" "{{GHIDRA_VERSION}}"
    printf {{format}} "Ghidra Date" "{{GHIDRA_DATE}}"
    printf "\n"
    tput setaf 5
    echo "Commands"
    tput setaf 4
    echo "-----------------------------"
    just --list | grep -v '^ *\(Avail\|default\).*' | xargs -I {} printf {{format}} {}
    printf "\n"

# }}}

# Extra Commands {{{

# Cleans out the old docker images that are no longer in use
clean:
    @docker system prune

# Cleans out the old docker images that are no longer in use, and the volumes
clean-all:
    @docker system prune --volumes

# }}}

# Ghidra commands {{{

# Recompiles the plugin and restarts the container
reload:
    just redist
    just kill
    just restart

# Recompiles the plugin
recompile:
    #!/bin/bash
    cd UnknownCyberPlugin
    ../gradlew clean build
    mv ./build/libs/UnknownCyberPlugin.jar lib/
    rm -rf build

# Packages the dependencies for distribution
redist: recompile
    #!/bin/bash
    cd UnknownCyberPlugin
    mkdir -p dist/unknowncyber
    cp -r extension.properties lib LICENSE.txt Module.manifest dist/unknowncyber/
    zip UnknownCyberPlugin-src.zip src/*
    mv UnknownCyberPlugin-src.zip dist/unknowncyber/lib/
    cd dist
    tar czvf unknowncyberghidraplugin.tgz unknowncyber
    zip -r unknowncyberghidraplugin.zip unknowncyber
    mv *.tgz *.zip ../..
    rm -rf ../dist
    cd ../..
    sha256sum unknowncyberghidraplugin.* > checksum

# Kills Ghidra
kill:
    docker kill ghidra

# Restarts Ghidra
restart:
    docker restart ghidra

# }}}

# Docker commands {{{

# Build the Ghidra docker image
build V=GHIDRA_VERSION D=GHIDRA_DATE:
    V=${V:-GHIDRA_VERSION}
    D=${D:-GHIDRA_DATE}
    cd docker \
        && docker-buildx build \
            --build-arg version={{V}} \
            --build-arg date={{D}} \
            -t virusbattleacr.azurecr.io/unknowncyber/ghidra:{{V}} .

# Builds the Ghidra docker image, ignoring cache
rebuild V=GHIDRA_VERSION D=GHIDRA_DATE:
    V=${V:-GHIDRA_VERSION}
    D=${D:-GHIDRA_DATE}
    cd docker \
        && docker-buildx build --no-cache \
            --build-arg version={{V}} \
            --build-arg date={{D}} \
            -t virusbattleacr.azurecr.io/unknowncyber/ghidra:{{V}} .

# Tags the versioned ghidra to the latest
latest +V=GHIDRA_VERSION:
    docker tag virusbattleacr.azurecr.io/unknowncyber/ghidra:{{V}} virusbattleacr.azurecr.io/unknowncyber/ghidra:latest

# Pushes the ghidra docker image to dockerhub
push +V=GHIDRA_VERSION:
    docker push virusbattleacr.azurecr.io/unknowncyber/ghidra:{{V}}

# }}}
