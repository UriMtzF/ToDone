package com.todone.desktop.helpers;

import java.util.prefs.Preferences;

public class PreferencesManager {
    private static final String PREF_LANG = "language";

    public static String getPrefLang() {
        Preferences preferences = Preferences.userNodeForPackage(PreferencesManager.class);
        return preferences.get(PREF_LANG, "en");
    }

    public static void setPrefLang(String prefLang) {
        Preferences preferences = Preferences.userNodeForPackage(PreferencesManager.class);
        preferences.put(PREF_LANG,prefLang);
    }
}
