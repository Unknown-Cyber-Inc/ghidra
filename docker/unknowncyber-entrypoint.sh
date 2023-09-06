#!/usr/bin/env bash
#

set -e

MAXMEM=${MAXMEM:-768M}
exec /opt/ghidra/support/launch.sh fg jdk Ghidra $MAXMEM "" ghidra.GhidraRun "$@"
