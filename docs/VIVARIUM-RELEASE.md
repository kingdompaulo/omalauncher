This custom Omalauncher build adds real transparent widget tiles and pinch-to-zoom for Vivarium.

- Transparent widget backgrounds now reveal the home wallpaper. Their idle tile outline is hidden; focus and arrangement still show an outline.
- `onPinch` exposes incremental scale and centroid coordinates. It shares gesture recognition with orbit dragging.
- Vivarium includes holographic terrain, lights, exploration controls and direct pinch zoom.
- The exact previous Vivarium prototype is upgraded automatically, with its source backed up under `widgets/.backups/`. Edited widgets and persisted residents are preserved.

Download `omalauncher-vivarium-0.7.2-1.apk` below in your browser. `52-vivarium.owl` is also included as a separate download.

This APK uses a dedicated custom signing key, not the upstream Play/GitHub signing key. Android can update an existing installation only when the signing certificates match. If Android rejects the update, do not uninstall the existing launcher: uninstalling can remove its Linux environment and data. Keep the APK for a compatible custom installation or rebuild with the original signing key.

The workflow runs the OWL and Android unit tests before publishing. Real phone rendering and two-finger gestures still require installation and device verification. The world remains an early visual prototype.
