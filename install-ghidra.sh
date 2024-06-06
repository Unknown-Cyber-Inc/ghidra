#!/bin/bash
#
#

GRADLE_VERSION=8.8
GHIDRA_VERSION=11.0.3
DATE=20240410

export PATH=/opt/java/bin/:/opt/gradle/gradle-$GRADLE_VERSION/bin:$PATH
export GHIDRA_HOME=/opt/ghidra/
export GHIDRA_INSTALL_DIR=/opt/ghidra/
export JAVA_HOME=/opt/java/

set -x

# Install dependencies
sudo apt update
sudo apt install -y --no-install-recommends \
    wget \
    tzdata \
    curl \
    ca-certificates \
    fontconfig \
    locales \
    binutils \
    wget \
    unzip \
    libfreetype6 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    openjdk-11-jre-headless

# Install the Java JDK
wget -O /tmp/openjdk.tar.gz 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.9%2B9/OpenJDK17U-jdk_x64_linux_hotspot_17.0.9_9.tar.gz' \
    && sudo mkdir -p $JAVA_HOME \
    && sudo tar --extract --file /tmp/openjdk.tar.gz --directory $JAVA_HOME --strip-components 1 --no-same-owner \
    && sudo rm -f /tmp/openjdk.tar.gz $JAVA_HOME/src.zip \
    && find $JAVA_HOME/lib -name '*.so' -exec dirname '{}' ';' | sort -u | sudo tee -a /etc/ld.so.conf.d/docker-openjdk.conf \
    && sudo ldconfig \
    && sudo java -Xshare:dump \
    && rm -rf ~/.java

# Install Ghidra
wget https://github.com/NationalSecurityAgency/ghidra/releases/download/Ghidra_${GHIDRA_VERSION}_ghidra_${GHIDRA_VERSION}_PUBLIC_$DATE.zip \
    && sudo unzip ghidra_${GHIDRA_VERSION}_PUBLIC_$DATE.zip -d /opt/ \
    && sudo mv /opt/ghidra_${GHIDRA_VERSION}_PUBLIC /opt/ghidra \
    && rm -rf ghidra_${GHIDRA_VERSION}_PUBLIC_$DATE.zip

# Install Gradle
wget https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip \
    && sudo unzip gradle-$GRADLE_VERSION-bin.zip -d /opt/gradle \
    && rm -rf gradle-$GRADLE_VERSION-bin.zip \
    && cd /opt/gradle \
    && sudo mv gradle-$GRADLE_VERSION gradle \
    && sudo ./gradle/bin/gradle init \
    && sudo ./gradle/bin/gradle wrapper

echo alias ghidra=\'/opt/ghidra/support/launch.sh fg jdk Ghidra 768M \"\" ghidra.GhidraRun\' >> ~/.bash_aliases
echo export JAVA_HOME=$JAVA_HOME >> ~/.bash_aliases
mkdir -p ~/.ghidra/.ghidra_${GHIDRA_VERSION}_PUBLIC/
echo $JAVA_HOME > ~/.ghidra/.ghidra_${GHIDRA_VERSION}_PUBLIC/java_home.save
source ~/.bashrc
