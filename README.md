# 🧩 josselinonduty's patches for Morphe

Personal [Morphe](https://morphe.software) patches for Android apps.

Personal use. Not affiliated with Morphe or the patched apps.

## 📱 Apps

| App | Package | Version | Focus |
|-----|---------|---------|-------|
| Snapchat | `com.snapchat.android` | 14.33.1.0 | Privacy, minimal UI |

### Snapchat

- Targets `14.33.1.0` only.
- Patches match stable anchors (strings, structure), not obfuscated names. A new release can still break them.
- `Spoof Snapchat signature` is required: login fails once the APK is re-signed. It reports the original public certificate to the app, without root.

## ❓ How to use these patches

Add this repo URL to Morphe Manager: https://github.com/josselinonduty/morphe-patches

The repo is private: Morphe Manager needs a GitHub personal access token with read access to it.

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->

<!-- Do not modify this section by hand. The patch list is generated when release.yml creates a new release.
     
     If you wish for the patches list to be collapsed, then remove the word 'EXPANDED' from the comment tag above.

     If you wish to manually keep this list updated then remove the PATCHES_START and PATCHES_END 
     comment blocks entirely. -->

#### A list of your patches will automatically be shown here after your first patches release is created.

&nbsp;

## 🛠️ Development

1. Follow the [Morphe development setup](https://github.com/MorpheApp/morphe-documentation/tree/main/docs/morphe-development). Java 21.
2. Add `gpr.user` and `gpr.key` (token with `read:packages`) to `~/.gradle/gradle.properties`.
3. Build: `./gradlew :patches:buildAndroid` (output: `patches/build/libs/patches-*.mpp`).
4. Work on `dev`. Commits follow [Conventional Commits](https://www.conventionalcommits.org). Merging `dev` into `main` creates a stable release.

Releases are signed. The public key is `morphe-signing.pub`.

## 📄 License

GPLv3 with the Morphe additional terms, see [LICENSE](LICENSE) and [NOTICE](NOTICE).
