package org.generic.musicplayer.utils;

/**
 * For some reason, android studio do not display in a normal Log command,
 * This is a helper class to display Log.e without supplying the
 * {@code android.util.Log} prefix.
 *
 * @author github.com/jbarry302
 */
public class Log {

    public static void d(String tag, String m) {
        android.util.Log.d(tag, m);
    }

    public static void d(String tag, String m, Exception e) {
        android.util.Log.d(tag, m, e);
    }

    public static void e(String tag, String m) {
        android.util.Log.e(tag, m);
    }

    public static void e(String tag, String m, Exception e) {
        android.util.Log.e(tag, m, e);
    }

    public static void i(String tag, String m) {
        android.util.Log.i(tag, m);
    }

    public static void i(String tag, String m, Exception e) {
        android.util.Log.i(tag, m, e);
    }

    public static void v(String tag, String m) {
        android.util.Log.v(tag, m);
    }

    public static void v(String tag, String m, Exception e) {
        android.util.Log.v(tag, m, e);
    }

    public static void w(String tag, String m) {
        android.util.Log.w(tag, m);
    }

    public static void w(String tag, String m, Exception e) {
        android.util.Log.w(tag, m, e);
    }

}
