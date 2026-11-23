package fr.josselinonduty.snapchat.ui

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall
import fr.josselinonduty.shared.prepend
import fr.josselinonduty.snapchat.snapBytecodePatch

private const val UI_TABS = "Lfr/josselinonduty/snapchat/UiTabs;"

// Anchor (14.33.1.0: Ly6v;->f()Ljava/util/List;): the tab-bar model getter; the bar adapts to its
// size. The class must own the tab-spec factories m()/o() (-> Lzge0;), which makes it unique.
// UiTabs returns one stable list (a fresh list per call breaks the selected-tab icon).
// Spec: S4.1. Obfuscated names: pinned to this build.
object TabListFingerprint : Fingerprint(
    returnType = "Ljava/util/List;",
    parameters = listOf(),
    filters = listOf(
        methodCall(definingClass = "Lxg11;", name = "getValue"),
    ),
    custom = { _, classDef ->
        val returnsTabSpec = { name: String ->
            classDef.methods.any { it.name == name && it.returnType == "Lzge0;" }
        }
        returnsTabSpec("m") && returnsTabSpec("o")
    },
)

val trimTabsPatch = snapBytecodePatch(
    name = "Keep only Chat and Camera tabs",
    description = "Shrinks the tab bar to Chat + Camera (hides Map, Stories, Spotlight).",
) {
    extendWith("extensions/snapchat.mpe")

    execute {
        // o() = Chat, m() = Camera; Chat must stay first.
        TabListFingerprint.method.prepend(
            """
                invoke-static {p0}, Ly6v;->o(Ly6v;)Lzge0;
                move-result-object v0
                invoke-static {p0}, Ly6v;->m(Ly6v;)Lzge0;
                move-result-object p0
                invoke-static {v0, p0}, $UI_TABS->twoTabs(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;
                move-result-object v0
                return-object v0
            """,
        )
    }
}
