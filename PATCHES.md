# Patches

> Generated from `patches-list.json` — **v1.0.0-dev.1** (`dev`) · **10 patches** across **1 apps** · back to [README](README.md)

---

## Snapchat (com.snapchat.android)

**Supported versions:** `14.33.1.0`

| Patch | Details |
|---|---|
| **Block Blizzard uploads** | Makes the Blizzard analytics upload runner a no-op that reports success. |
| **Disable ads** | Makes the ad gate always report ads disabled so no sponsored items are inserted. |
| **Disable location providers** | Reports no usable location provider so the app cannot read or subscribe to a real fix. |
| **Hide My AI** | Hides the My AI row from the Chat list. |
| **Hide Team Snapchat** | Hides the Team Snapchat row from the Chat list. |
| **Keep only Chat and Camera tabs** | Shrinks the tab bar to Chat + Camera (hides Map, Stories, Spotlight). |
| **Remove Games tab** | Shows the plain Camera icon and label in the center tab instead of Games. |
| **Remove advertising ID permissions** | Removes the advertising-ID permissions. |
| **Remove location permissions** | Removes location permissions and disables Loda location components. |
| **Spoof Snapchat signature** | Required for login: reports Snapchat's original public certificate to in-process readers. |

---
