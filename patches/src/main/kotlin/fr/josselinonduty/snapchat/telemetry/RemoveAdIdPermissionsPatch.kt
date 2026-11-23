package fr.josselinonduty.snapchat.telemetry

import fr.josselinonduty.snapchat.removeUsesPermissions
import fr.josselinonduty.snapchat.snapResourcePatch

// Spec: S1.4. Anchor: <uses-permission> names (not obfuscated).
val removeAdIdPermissionsPatch = snapResourcePatch(
    name = "Remove advertising ID permissions",
    description = "Removes the advertising-ID permissions.",
) {
    execute {
        document("AndroidManifest.xml").use {
            it.removeUsesPermissions(
                setOf("com.google.android.gms.permission.AD_ID", "android.permission.ACCESS_ADSERVICES_AD_ID"),
            )
        }
    }
}
