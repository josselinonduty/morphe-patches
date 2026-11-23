package fr.josselinonduty.snapchat.location

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import fr.josselinonduty.shared.returnNull
import fr.josselinonduty.snapchat.snapBytecodePatch

// Anchor (14.33.1.0: Lxu1;->a()Ljava/lang/String;): picks "fused"/"gps"/"network"
// from LocationManager.getAllProviders(). Both callers (last-known read and
// requestLocationUpdates) short-circuit to "no provider" on null, so returning
// null stops Java location reads and update registration without throwing.
// Spec: S3.1.
object LocationProviderFingerprint : Fingerprint(
    returnType = "Ljava/lang/String;",
    parameters = listOf(),
    strings = listOf("fused", "gps", "network"),
    filters = listOf(
        methodCall(definingClass = "Landroid/location/LocationManager;", name = "getAllProviders"),
    ),
)

val disableLocationProvidersPatch = snapBytecodePatch(
    name = "Disable location providers",
    description = "Reports no usable location provider so the app cannot read or subscribe to a real fix.",
) {
    execute { LocationProviderFingerprint.method.returnNull() }
}
