package assessment.mycompany.com.tvguideassessment.callbacks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import assessment.mycompany.com.tvguideassessment.utils.Constants;

public class TVGuideReceiver extends BroadcastReceiver {
    private ApiCallBack apiCallBack;

    public TVGuideReceiver(ApiCallBack apiCallBack) {
        this.apiCallBack = apiCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.IntentActions.ACTION_ERROR)) {
            apiCallBack.onHttpResponseError(intent);
        } else if (intent.getAction().equals(Constants.IntentActions.ACTION_SUCCESS)) {
            apiCallBack.onHttpRequestComplete(intent);
        }
    }
}
