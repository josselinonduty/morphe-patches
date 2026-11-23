package fr.josselinonduty.snapchat.location

import fr.josselinonduty.shared.disableComponents
import fr.josselinonduty.shared.removeUsesPermissions
import fr.josselinonduty.snapchat.snapResourcePatch

// Spec: S3.1, S3.3. Anchors: <uses-permission> and Loda component names (not obfuscated).
// LodaDaemonService has foregroundServiceType="location": it would throw SecurityException
// without FOREGROUND_SERVICE_LOCATION, so the Loda components are disabled too.
val removeLocationPermissionsPatch = snapResourcePatch(
    name = "Remove location permissions",
    description = "Removes location permissions and disables Loda location components.",
) {
    execute {
        document("AndroidManifest.xml").use {
            it.removeUsesPermissions(
                setOf(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_BACKGROUND_LOCATION",
                    "android.permission.ACCESS_MEDIA_LOCATION",
                    "android.permission.FOREGROUND_SERVICE_LOCATION",
                ),
            )
            it.disableComponents(
                setOf(
                    "com.snap.location.loda.bindings.LodaDaemonService",
                    "com.snap.location.loda.bindings.LodaMainAppMailboxService",
                    "com.snap.location.loda.bindings.GeofenceBroadcastReceiver",
                ),
            )
        }
    }
}
