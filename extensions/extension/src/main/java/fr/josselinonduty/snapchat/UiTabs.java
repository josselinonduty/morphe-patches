package fr.josselinonduty.snapchat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** UI helpers for the tab bar. */
public final class UiTabs {
    private UiTabs() {}

    // The app's tab model getter returns a cached list; the bar controller relies on
    // that stability. Keep one list (and the same spec objects) for the process.
    private static List<Object> cached;

    public static synchronized List<Object> twoTabs(Object first, Object second) {
        if (cached == null) {
            cached = Collections.unmodifiableList(Arrays.asList(first, second));
        }
        return cached;
    }
}
