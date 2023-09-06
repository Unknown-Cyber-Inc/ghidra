# Unknown Cyber Ghidra Plugin

## How to run the docker container

 * Make sure to download the latest ghidra docker image
   * `docker pull unknowncyber/ghidra`

 * Start your ghidra container from within your development repository
   * `just dev-up ghidra`

 * If this is your first time running it, compile the jar file from this directory
   * `just reload`

 * When the plugin opens, create or open a project and you're good to go!!
