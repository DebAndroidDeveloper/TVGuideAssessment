package assessment.mycompany.com.tvguideassessment.system.network;

import android.content.Intent;

/**
 * Created by Debasis on 8/26/2015.
 */
public class ShowInfoApiManager extends NetworkManager {
    private int mRequestId = -1;

    @Override
    public void execute(Intent intent) {

    }

    @Override
    public int getRequestId() {
        return mRequestId;
    }

    @Override
    public String getTag() {
        return ShowInfoApiManager.class.getCanonicalName();
    }
}
