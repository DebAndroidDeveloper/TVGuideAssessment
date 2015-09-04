package assessment.mycompany.com.tvguideassessment.system.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import assessment.mycompany.com.tvguideassessment.utils.CommonUtils;
import assessment.mycompany.com.tvguideassessment.utils.Constants;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ApiIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "assessment.polaris.com.tvguideassessment.system.network.action.FOO";
    private static final String ACTION_BAZ = "assessment.polaris.com.tvguideassessment.system.network.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "assessment.polaris.com.tvguideassessment.system.network.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "assessment.polaris.com.tvguideassessment.system.network.extra.PARAM2";


    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void getTrendingShows(Context context) {
        Intent getIntent = new Intent();
        getIntent.setAction(Constants.IntentActions.TRENDING_SHOWS);
        getIntent.putExtra(Constants.IntentExtras.REQUEST_ID, Constants.ApiRequestId.GET_TRENDING_SHOWS);
        startApiService(context, getIntent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void getTrendingShowInfo(Context context) {
        Intent getIntent = new Intent();
        getIntent.setAction(Constants.IntentActions.TRENDING_SHOW_INFO);
        getIntent.putExtra(Constants.IntentExtras.REQUEST_ID, Constants.ApiRequestId.GET_TRENDING_SHOW_INFO);
        startApiService(context, getIntent);
    }

    public ApiIntentService() {
        super("ApiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            NetworkManager networkManager = ApiManagerFactory.createApiManager(action, this);
            networkManager.logExecute(networkManager.getClass().getCanonicalName(), intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1));
            networkManager.execute(intent);
        }
    }

    private static boolean startApiService(Context context, Intent intent) {
        if (CommonUtils.isNetworkAvailable(context)) {
            intent.setClass(context, ApiIntentService.class);
            context.startService(intent);
            return true;
        } else {
            Intent errIntent = new Intent();
            errIntent.setAction(Constants.IntentActions.ACTION_ERROR);
            errIntent.putExtra(Constants.IntentExtras.MESSAGE, Constants.IntentExtras.ERROR_NO_NETWORK);
            context.sendBroadcast(intent);
            return false;
        }
    }

}
