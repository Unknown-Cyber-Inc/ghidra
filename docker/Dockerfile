FROM ubuntu:22.04 AS base
ARG version=11.0.3
ARG date=20240410
EXPOSE 13100/tcp 13101/tcp 13102/tcp
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8
ENV DEBIAN_FRONTEND=noninteractive
ENV PATH=/opt/java/bin/:/opt/gradle/gradle-8.8/bin:$PATH
ENV GHIDRA_HOME=/opt/ghidra/
ENV GHIDRA_INSTALL_DIR=/opt/ghidra/
ENV PGHIDRA_INSTALL_DIR=/opt/ghidra/
ENV JAVA_HOME=/opt/java/
ENV JAVA_VERSION=jdk-17.0.9+9
ENV MAXMEM=768M
ENV VB_HOME=/home/unknowncyber
WORKDIR $VB_HOME

RUN groupadd -g 24207 unknowncyber \
    && groupadd -g 137 vboxsf \
    && useradd --no-log-init -u 24207 -g 24207 -d /home/unknowncyber -m unknowncyber \
    && usermod -aG vboxsf unknowncyber \
    # Set the user's password
    && echo "unknowncyber:unknowncyber" | chpasswd \
    # Create shared directory
    && mkdir /shared /home/unknowncyber/.azcopy \
    && mkdir -p $VB_DATA/tmp $VB_DATA/logs $VB_DATA/vbdb $CONFIGS_DIR \
    && mkdir -p $VB_HOME/tmp $VB_HOME/logs $VB_HOME/store/files $VB_HOME/tools \
    && chown -R unknowncyber:unknowncyber /shared $VB_HOME
COPY --chown=unknowncyber dev.crt dev.key /tmp/
COPY --chown=unknowncyber dev.crt dev.key /etc/ssl/unknowncyber/
COPY --chown=unknowncyber dev.crt /etc/ssl/unknowncyber/ca.crt
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

# ---------------------------------------------------------------------------
# Bake the UnknownCyber Ghidra extension into the image.
#
# Installed into the install-dir Extensions tree, NOT ~/.ghidra — the compose
# file bind-mounts $GHIDRA_DIR over /home/unknowncyber/.ghidra, which would
# shadow anything placed there. /opt/ghidra/Ghidra/Extensions is outside that
# mount, so the baked copy survives. (curl is installed in the step above and
# not removed, so it's available here.)
#
# Pinned; bump GHIDRA_PLUGIN_VERSION to ship a new plugin.
# For live plugin development, the hot.yml compose override
# bind-mounts a local build over /opt/ghidra/Ghidra/Extensions/unknowncyber.
#
# NOTE: verify your Ghidra version actually LOADS an install-dir extension in
# your launch mode. Headless analyzers pick these up automatically; the GUI may
# require a one-time per-user activation (which writes into ~/.ghidra). If so,
# have unknowncyber-entrypoint.sh copy this into
# ~/.ghidra/.ghidra_<ver>_PUBLIC/Extensions/ on first start (that dir is
# writable via the $GHIDRA_DIR mount).
# ---------------------------------------------------------------------------
ARG GHIDRA_PLUGIN_VERSION=1.0.1
RUN curl -fsSL \
      "https://github.com/Unknown-Cyber-Inc/ghidra/releases/download/${GHIDRA_PLUGIN_VERSION}/unknowncyberghidraplugin.tgz" \
      | tar xz -C "$GHIDRA_INSTALL_DIR/Ghidra/Extensions/" \
    && rm -rf "$GHIDRA_INSTALL_DIR/Ghidra/Extensions/unknowncyber/dependencies" \
    && rm -f  "$GHIDRA_INSTALL_DIR/Ghidra/Extensions/unknowncyber/requirements.txt" \
    && chown -R unknowncyber:unknowncyber "$GHIDRA_INSTALL_DIR/Ghidra/Extensions/unknowncyber"

COPY unknowncyber-entrypoint.sh /unknowncyber-entrypoint.sh

USER unknowncyber
ENTRYPOINT ["/unknowncyber-entrypoint.sh"]
