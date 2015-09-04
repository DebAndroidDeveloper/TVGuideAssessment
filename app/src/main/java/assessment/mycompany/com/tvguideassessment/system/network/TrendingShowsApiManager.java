package assessment.mycompany.com.tvguideassessment.system.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import assessment.mycompany.com.tvguideassessment.BuildConfig;
import assessment.mycompany.com.tvguideassessment.system.ApiException;
import assessment.mycompany.com.tvguideassessment.utils.Constants;

/**
 * Created by Debasis on 8/26/2015.
 */
public class TrendingShowsApiManager extends NetworkManager {
    private int mRequestId = -1;

    public TrendingShowsApiManager(Context currentContext) {
        mContext = currentContext;
    }

    @Override
    public void execute(Intent intent) {
        mRequestId = intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1);

        String url = BuildConfig.BASE_TV_SHOW_API_URL + Constants.ApiMethods.SHOW_TRENDS;

        try {
            String response = get(url);
            JSONArray jsonArray = new JSONArray(response);
            Log.d(getTag(), getTag() + " response " + jsonArray.toString(4));
            broadcast(Constants.IntentActions.ACTION_SUCCESS, response, null);
        } catch (IOException e) {
            Log.e(getTag(), getTag() + " IOException " + e.getMessage());
            broadcast(Constants.IntentActions.ACTION_ERROR, getTag() + " " + e.getMessage(), null);
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(getTag(), getTag() + " JSONException " + e.getMessage());
            broadcast(Constants.IntentActions.ACTION_ERROR, getTag() + " " + e.getMessage(), null);
            e.printStackTrace();
        } catch (ApiException e) {
            Log.e(getTag(), getTag() + " Api Exception " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public int getRequestId() {
        return mRequestId;
    }

    @Override
    public String getTag() {
        return TrendingShowsApiManager.class.getCanonicalName();
    }
}
