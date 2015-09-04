package assessment.mycompany.com.tvguideassessment.utils;

import assessment.mycompany.com.tvguideassessment.system.network.ShowInfoApiManager;
import assessment.mycompany.com.tvguideassessment.system.network.TrendingShowsApiManager;

/**
 * Created by Debasis on 8/26/2015.
 */
public class Constants {
    public interface ApiKeys {

    }

    public interface SharedPreferenceKeys {
        String TRENDING_SHOW_NUMBER = "com.polaris.tvguideassessment.TRENDING_SHOW_NUMBER";
    }

    public interface IntentExtras {
        String ERROR_NO_NETWORK = "com.polaris.tvguideassessment.appIntentExtras.ERROR_NO_NETWORK";
        String MESSAGE = "com.polaris.tvguideassessment.appIntentExtras.MESSAGE";
        String JSON_RESPONSE = "com.polaris.tvguideassessment.appIntentExtras.JSON_RESPONSE";
        String REQUEST_ID = "com.polaris.tvguideassessment.appIntentExtras.ID";
        String TRENDING_SHOWS = "com.polaris.tvguideassessment.appIntentExtras.TREDING_SHOWS";
        String TRENDING_SHOW_INFO = "com.polaris.tvguideassessment.appIntentExtras.TRENDING_SHOW_INFO";
    }

    public interface IntentActions {
        String TRENDING_SHOWS = TrendingShowsApiManager.class.getName();
        String TRENDING_SHOW_INFO = ShowInfoApiManager.class.getName();
        String ACTION_ERROR = "com.polaris.tvguideassessment.appIntentExtras.ACTION_ERROR";
        String ACTION_SUCCESS = "com.polaris.tvguideassessment.appIntentExtras.ACTION_SUCCESS";
    }

    public interface ApiMethods {
        String SHOW_TRENDS = "/showtrends.php";
        String SHOW_INFO = "/getShowInfo.php?url=/m/shows/";
    }

    public interface JsonKeys {
        String ERRORS = "errors";
        String MESSAGE = "message";
        String TRENDING_SHOWS_NUMBER = "num";
    }

    public interface ApiRequestId {
        int API_BASE_VALUE = 200;
        int GET_TRENDING_SHOWS = API_BASE_VALUE + 1;
        int GET_TRENDING_SHOW_INFO = API_BASE_VALUE + 2;
    }
}
