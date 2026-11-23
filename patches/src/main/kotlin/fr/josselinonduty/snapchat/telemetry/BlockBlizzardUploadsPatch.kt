package fr.josselinonduty.snapchat.telemetry

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import fr.josselinonduty.snapchat.prepend
import fr.josselinonduty.snapchat.snapBytecodePatch
import com.android.tools.smali.dexlib2.AccessFlags

private const val SINGLE = "Lio/reactivex/rxjava3/core/Single;"
private const val SINGLE_JUST = "Lio/reactivex/rxjava3/internal/operators/single/SingleJust;"

// Anchor (14.33.1.0: Li751;->a): base of every Blizzard upload-window runner
// (subclasses Lji7;/Lli7;, driven by BlizzardV2DurableJob processor Lzs7;).
// Structure: Set.isEmpty() early-out + ObservableFlatMapSingle fan-out.
// Its own empty-set path returns Single.just(window): a clean success, so no retry storm.
// Spec: S1.1.
object BlizzardUploadFingerprint : Fingerprint(
    accessFlags = listOf(AccessFlags.PUBLIC),
    returnType = SINGLE,
    parameters = listOf("L"),
    custom = { _, classDef -> AccessFlags.ABSTRACT.isSet(classDef.accessFlags) }, // runner base class
    filters = listOf(
        methodCall(definingClass = "Ljava/util/Set;", name = "isEmpty"),
        methodCall(
            definingClass = "Lio/reactivex/rxjava3/internal/operators/observable/ObservableFlatMapSingle;",
            name = "<init>",
        ),
    ),
)

val blockBlizzardUploadsPatch = snapBytecodePatch(
    name = "Block Blizzard uploads",
    description = "Makes the Blizzard analytics upload runner a no-op that reports success.",
) {
    execute {
        BlizzardUploadFingerprint.method.prepend(
            """
                new-instance v0, $SINGLE_JUST
                invoke-direct {v0, p1}, $SINGLE_JUST-><init>(Ljava/lang/Object;)V
                return-object v0
            """,
        )
    }
}
