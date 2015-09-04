package assessment.mycompany.com.tvguideassessment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Set;

/**
 * Created by Debasis on 8/28/2015.
 */
public class SharedPreferenceUtil {
    private static final String TAG = SharedPreferenceUtil.class.getCanonicalName();


    /**
     * Save a key-value pair to SharedPreferences
     *
     * @param context The current context
     * @param key     The key for the key-value pair
     * @param value   The value of the key-value pair (String)
     */
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
        Log.d(TAG, "Saved prefence: " + key + " as " + value);
    }

    /**
     * Save a key-value pair to SharedPreferences
     *
     * @param context The current context
     * @param key     The key for the key-value pair
     * @param value   The value of the key-value pair (String)
     */
    public static void savePreference(Context context, String key, Set<String> value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        editor.commit();
        Log.d(TAG, "Saved prefence: " + key + " as " + value);
    }


    public static String readPreference(Context context, String key, String defaultValue) {
        //if (Log.isD(TAG)) {
        //Log.d(TAG, "readPreference : Start");
        Log.d(TAG, "readPreference : key :" + key
                + " ; defaultValue : " + defaultValue);
        //}
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);

        //if (Log.isD(TAG)) {
        //Log.d(TAG, "readPreference : END");
        Log.d(TAG, "readPreference : key :" + key + " ; value : "
                + sp.getString(key, defaultValue));
        //}

        return sp.getString(key, defaultValue);
    }

    public static void clearPreferences(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }

    public static Set<String> readPreference(Context context, String key, Set<String> defaultValue) {
        Log.d(TAG, "readPreference : key :" + key
                + " ; defaultValue : " + defaultValue);
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);

        Log.d(TAG, "readPreference : key :" + key + " ; value : "
                + sp.getStringSet(key, defaultValue));
        return sp.getStringSet(key, defaultValue);
    }

}
