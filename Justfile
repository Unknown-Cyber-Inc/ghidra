# vim: foldmethod=marker foldmarker={{{,}}}:
# vim: set ft=just:

set shell := ["bash", "-uc"]

GHIDRA_VERSION := "10.3.2"

# Justfile Help message {{{

gold:=`tput setaf 3`
reset:=`tput setaf 5`
format:="'%4s"+gold+"%-20s"+reset+"%s\\n' ''"

@default:
    printf "\n"
    printf {{format}} "Ghidra Version" "{{GHIDRA_VERSION}}"
    printf "\n"
    tput setaf 5
    echo "Commands"
    tput setaf 4
    echo "-----------------------------"
    just --list | grep -v '^ *\(Avail\|default\).*' | xargs -I {} printf {{format}} {}
    printf "\n"

# }}}

# Ghidra commands {{{

# Recompiles the plugin and restarts the container
reload:
    just recompile
    just kill
    just restart

# Recompiles the plugin
recompile:
    docker exec -it -u root --workdir /root/.ghidra/.ghidra_10.3.2_PUBLIC/Extensions/UnknownCyberPlugin ghidra gradle jar
    docker exec -it -u root --workdir /root/.ghidra/.ghidra_10.3.2_PUBLIC/Extensions/UnknownCyberPlugin ghidra mv ./build/libs/UnknownCyberPlugin.jar lib/

# Kills Ghidra
kill:
    docker kill ghidra

# Restarts Ghidra
restart:
    docker restart ghidra

# }}}

# Docker commands {{{

# Build the Ghidra docker image
build +V=GHIDRA_VERSION:
    cd docker && docker-buildx build --build-arg version={{V}} -t unknowncyber/ghidra:{{V}} .

# Builds the Ghidra docker image, ignoring cache
rebuild +V=GHIDRA_VERSION:
    cd docker && docker-buildx build --no-cache --build-arg version={{V}} -t unknowncyber/ghidra:{{V}} .

# Pushes the ghidra docker image to dockerhub
push +V=GHIDRA_VERSION:
    docker push unknowncyber/ghidra:{{V}}

# }}}
