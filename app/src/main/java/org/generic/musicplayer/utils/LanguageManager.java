package org.generic.musicplayer.utils;


import android.content.Context;

/**
 * This class will be responsible for the language setting for the app
 */

public class LanguageManager {

    public static final String LANG_FILIPINO = "PH";
    public static final String LANG_JAPANESE = "JP";

    private Context context;

    public LanguageManager(Context context) {
        this.context = context;
    }

    public void setLanguage(String code) {
        switch(code) {
            case LANG_FILIPINO:
                //for fil
                break;
            case LANG_JAPANESE:
                //for jap
                break;

        }
        setAsGlobalSettings();
    }

    private void setAsGlobalSettings() {

    }

}
