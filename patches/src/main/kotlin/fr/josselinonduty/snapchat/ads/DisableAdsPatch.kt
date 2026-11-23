package fr.josselinonduty.snapchat.ads

import app.morphe.patcher.Fingerprint
import fr.josselinonduty.snapchat.returnFalse
import fr.josselinonduty.snapchat.snapBytecodePatch

// Anchor (14.33.1.0: Lmcp;->f(Lwb0;Lmzg;)Z): the single per-surface ad gate
// ("Ads:Gate:isAdEnabled") consulted before ad items are inserted into
// Stories / Spotlight / Chat feeds. Returning false disables ad insertion only;
// unrelated feed logic lives in the neighbouring method.
// Spec: S2.2.
object AdEnabledGateFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf("L", "L"),
    strings = listOf("Ads:Gate:isAdEnabled"),
)

val disableAdsPatch = snapBytecodePatch(
    name = "Disable ads",
    description = "Makes the ad gate always report ads disabled so no sponsored items are inserted.",
) {
    execute { AdEnabledGateFingerprint.method.returnFalse() }
}
