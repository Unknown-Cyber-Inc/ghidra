#!/bin/bash
#
#

export PATH=/opt/java/bin/:/opt/gradle/gradle-8.4/bin:$PATH
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
wget -O /tmp/openjdk.tar.gz 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz' \
    && sudo mkdir -p $JAVA_HOME \
    && sudo tar --extract --file /tmp/openjdk.tar.gz --directory $JAVA_HOME --strip-components 1 --no-same-owner \
    && sudo rm -f /tmp/openjdk.tar.gz $JAVA_HOME/src.zip \
    && find $JAVA_HOME/lib -name '*.so' -exec dirname '{}' ';' | sort -u | sudo tee -a /etc/ld.so.conf.d/docker-openjdk.conf \
    && sudo ldconfig \
    && sudo java -Xshare:dump \
    && rm -rf ~/.java

# Install Ghidra
wget https://github.com/NationalSecurityAgency/ghidra/releases/download/Ghidra_10.4_build/ghidra_10.4_PUBLIC_20230928.zip \
    && sudo unzip ghidra_10.4_PUBLIC_20230928.zip -d /opt/ \
    && sudo mv /opt/ghidra_10.4_PUBLIC /opt/ghidra \
    && rm -rf ghidra_10.4_PUBLIC_20230928.zip

# Install Gradle
wget https://services.gradle.org/distributions/gradle-8.4-bin.zip \
    && sudo unzip gradle-8.4-bin.zip -d /opt/gradle \
    && rm -rf gradle-8.4-bin.zip \
    && cd /opt/gradle \
    && sudo mv gradle-8.4 gradle \
    && sudo ./gradle/bin/gradle init \
    && sudo ./gradle/bin/gradle wrapper

echo alias ghidra=\'/opt/ghidra/support/launch.sh fg jdk Ghidra 768M \"\" ghidra.GhidraRun\' >> ~/.bashrc
echo export JAVA_HOME=$JAVA_HOME >> ~/.bashrc
source ~/.bashrc
