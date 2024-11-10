# Unknown Cyber Ghidra Plugin
The Unknown Cyber Ghidra Plugin is designed to integrate Unknown Cyber technologies seamlessly with Ghidra.

- Key Features
  * Binary and disassembly uploading
  * Project creation
  * CRUD operations for procedures, procedure groups, and file notes/tags
  * File and procedure similarity matching

## Installation
The following walkthrough uses the tarball delivery. Change instructions where necessary if using the zipfile.
- Download `unknowncyberghidraplugin.tgz` from a ([release](https://github.com/Unknown-Cyber-Inc/ghidra/releases)).
- Verify the download with the release's checksum.
- Extract
  * `tar xvzf unknowncyberghidraplugin.tgz`
- Set the following environment variables:
  * `MAGIC_API_HOST` - Use `https://api.magic.unknowncyber.com` unless using an offline Unknown Cyber system.
  * `MAGIC_API_KEY` - Replace with your Unknown Cyber api key.
