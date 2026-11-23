# 🧩 josselinonduty's patches for Morphe

[Morphe](https://morphe.software) patches for Android apps, focused on privacy and a cleaner UI.

Unofficial community project. Not affiliated with or endorsed by Morphe, Snap Inc. or any patched app. All trademarks belong to their owners.

## 📱 Apps

| App | Package | Version | Focus |
|-----|---------|---------|-------|
| Snapchat | `com.snapchat.android` | 14.33.1.0 | Privacy, minimal UI |

### Snapchat

- Targets `14.33.1.0` only.
- Patches match stable anchors (strings, structure), not obfuscated names. A new release can still break them.
- `Spoof Snapchat signature` is required: login fails once the APK is re-signed. It reports the original public certificate to the app, without root.

## ❓ How to use these patches

1. Open Morphe Manager and add this repo as a patch source: https://github.com/josselinonduty/morphe-patches
2. Provide an unpatched APK of a supported version (see [Apps](#-apps)) and select the patches you want.

You can also download the `.mpp` bundle from the [latest release](https://github.com/josselinonduty/morphe-patches/releases/latest).

Found a bug or want a feature? [Open an issue](https://github.com/josselinonduty/morphe-patches/issues/new/choose).

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->

<!-- Do not modify this section by hand. The patch list is generated when release.yml creates a new release.
     
     If you wish for the patches list to be collapsed, then remove the word 'EXPANDED' from the comment tag above.

     If you wish to manually keep this list updated then remove the PATCHES_START and PATCHES_END 
     comment blocks entirely. -->

#### A list of your patches will automatically be shown here after your first patches release is created.

<!-- PATCHES_END -->

&nbsp;

## ➕ Adding a new app

1. Patches: `patches/src/main/kotlin/fr/josselinonduty/<app>/`, with an `<App>.kt` holding the `Compatibility` constant and the app's patch builders. Generic helpers go in `fr/josselinonduty/shared/`, patches that work on any app in `fr/josselinonduty/all/`.
2. Extension (only if the patches need Java code): `extensions/<app>/` with `name = "extensions/<app>.mpe"` and its own `android.namespace`. Patches load it with `extendWith("extensions/<app>.mpe")`.
3. Add the app to the Apps table above and to the `App` dropdown in `.github/ISSUE_TEMPLATE/`.
4. Use the app as the commit scope: `feat(<app>): ...`, `bump(<app>): <version>`.

All apps ship in one bundle and share one release version.

## 🛠️ Development

1. Follow the [Morphe development setup](https://github.com/MorpheApp/morphe-documentation/tree/main/docs/morphe-development). Java 21.
2. Add `gpr.user` and `gpr.key` (a GitHub token with `read:packages`) to `~/.gradle/gradle.properties`. Morphe's dependencies are served from GitHub Packages, which requires authentication even for public repos.
3. Build: `./gradlew :patches:buildAndroid` (output: `patches/build/libs/patches-*.mpp`).
4. Contributions are welcome: branch from `dev` and open a pull request against it. Commits follow [Conventional Commits](https://www.conventionalcommits.org). Merging `dev` into `main` creates a stable release.

Releases are signed. The public key is `morphe-signing.pub`.

## 📄 License

GPLv3 with the Morphe additional terms, see [LICENSE](LICENSE) and [NOTICE](NOTICE).
