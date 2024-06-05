FROM ubuntu:22.04 AS base
ARG version=11.0.3
ARG date=20230928
EXPOSE 13100/tcp 13101/tcp 13102/tcp
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8
ENV DEBIAN_FRONTEND=noninteractive
ENV PATH /opt/java/bin/:/opt/gradle/gradle-8.8/bin:$PATH
ENV GHIDRA_HOME /opt/ghidra/
ENV GHIDRA_INSTALL_DIR /opt/ghidra/
ENV PGHIDRA_INSTALL_DIR /opt/ghidra/
ENV JAVA_HOME /opt/java/
ENV JAVA_VERSION=jdk-17.0.9+9
ENV MAXMEM=768M

COPY ca.crt .
# Install the Java JDK
RUN set -x \
    && apt update \
    && apt install -y --no-install-recommends \
        wget \
    && wget --no-check-certificate -O /tmp/openjdk.tar.gz 'https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.9%2B9/OpenJDK17U-jdk_x64_linux_hotspot_17.0.9_9.tar.gz' \
    && mkdir -p $JAVA_HOME \
    && tar --extract --file /tmp/openjdk.tar.gz --directory $JAVA_HOME --strip-components 1 --no-same-owner \
    && rm -f /tmp/openjdk.tar.gz $JAVA_HOME/src.zip \
    && find $JAVA_HOME/lib -name '*.so' -exec dirname '{}' ';' | sort -u > /etc/ld.so.conf.d/docker-openjdk.conf \
    && ldconfig \
    && java -Xshare:dump \
    && echo "yes" | keytool -import -alias "magic" -file ca.crt -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit \
    && rm -rf ~/.java \
    && rm -rf /var/lib/apt/lists/* /var/cache/apt/archives /tmp/* /var/tmp/* \
    && apt -y remove wget \
    && apt -y autoremove


# Install Ghidra and Gradle
RUN apt-get update \
    && apt install -y --no-install-recommends \
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
    && echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen \
    && locale-gen en_US.UTF-8 \
    && wget --no-check-certificate https://github.com/NationalSecurityAgency/ghidra/releases/download/Ghidra_${version}_build/ghidra_${version}_PUBLIC_${date}.zip \
    && unzip ghidra_${version}_PUBLIC_${date}.zip -d /opt/ \
    && mv /opt/ghidra_${version}_PUBLIC /opt/ghidra \
    && rm -rf ghidra_${version}_PUBLIC_${date}.zip \
    && wget https://services.gradle.org/distributions/gradle-8.8-bin.zip \
    && unzip gradle-8.8-bin.zip -d /opt/gradle \
    && rm -rf gradle-8.8-bin.zip \
    && cd /opt/gradle \
    && mv gradle-8.8 gradle \
    && ./gradle/bin/gradle init \
    && ./gradle/bin/gradle wrapper \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /var/cache/apt/archives /tmp/* /var/tmp/* \
    && apt -y remove wget unzip \
    && apt -y autoremove

COPY unknowncyber-entrypoint.sh /unknowncyber-entrypoint.sh

ENTRYPOINT ["/unknowncyber-entrypoint.sh"]
