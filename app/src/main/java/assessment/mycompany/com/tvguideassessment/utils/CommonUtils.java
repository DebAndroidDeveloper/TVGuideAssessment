package assessment.mycompany.com.tvguideassessment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Priya on 8/26/2015.
 */
public class CommonUtils {
    private static final String TAG = CommonUtils.class.getCanonicalName();

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            Log.d(TAG, " isOnline : True");
            return true;
        } else {
            Log.d(TAG, " isOnline : False");
            return false;
        }
    }
}
