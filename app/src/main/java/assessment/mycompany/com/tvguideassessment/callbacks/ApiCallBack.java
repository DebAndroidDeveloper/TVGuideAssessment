package assessment.mycompany.com.tvguideassessment.callbacks;

import android.content.Intent;

/**
 * Created by Priya on 8/26/2015.
 */
public interface ApiCallBack {

    /**
     * This method is called from broadcast receiver in case of an error
     *
     * @param intent
     */
    void onHttpResponseError(Intent intent);

    /**
     * This method is called from broadcast receive when data has been loaded in
     * sqlite database
     */
    void onHttpRequestComplete(Intent intent);
}
