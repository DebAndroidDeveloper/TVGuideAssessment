package assessment.mycompany.com.tvguideassessment.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import assessment.mycompany.com.tvguideassessment.R;
import assessment.mycompany.com.tvguideassessment.adapters.TrendingShowsListAdapter;
import assessment.mycompany.com.tvguideassessment.callbacks.ApiCallBack;
import assessment.mycompany.com.tvguideassessment.callbacks.TVGuideReceiver;
import assessment.mycompany.com.tvguideassessment.models.TrendingShowDetails;
import assessment.mycompany.com.tvguideassessment.system.network.ApiIntentService;
import assessment.mycompany.com.tvguideassessment.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingShowsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingShowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingShowsFragment extends BaseFragment implements ApiCallBack, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<TrendingShowDetails> trendingShowDetailsList;
    private TrendingShowsListAdapter trendingShowsListAdapter;
    private TVGuideReceiver tvGuideReceiver;
    private IntentFilter mFilter;
    private ProgressDialog mProgressDialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber Parameter 1.
     *                      //@param param2 Parameter 2.
     * @return A new instance of fragment TrendingShowsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrendingShowsFragment newInstance(int sectionNumber) {
        TrendingShowsFragment fragment = new TrendingShowsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNumber);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TrendingShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setRetainInstance(true);
        this.trendingShowDetailsList = new ArrayList<TrendingShowDetails>();
        mProgressDialog = new ProgressDialog(getActivity());
        //mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("One moment please...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        ApiIntentService.getTrendingShows(getActivity());
        //populateList();
    }

    @Override
    public void onResume() {
        super.onResume();
        tvGuideReceiver = new TVGuideReceiver(this);
        mFilter = new IntentFilter();
        mFilter.addAction(Constants.IntentActions.ACTION_ERROR);
        mFilter.addAction(Constants.IntentActions.ACTION_SUCCESS);
        getActivity().registerReceiver(tvGuideReceiver, mFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(tvGuideReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View trendingShowsView = inflater.inflate(R.layout.fragment_trending_shows, container, false);
        ListView trendingShowsList = (ListView) trendingShowsView.findViewById(R.id.trending_shows_listView);
        trendingShowsList.setOnItemClickListener(this);
        trendingShowsListAdapter = new TrendingShowsListAdapter(getActivity(), trendingShowDetailsList);
        trendingShowsList.setAdapter(trendingShowsListAdapter);
        return trendingShowsView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public String getTagName() {
        return TrendingShowsFragment.class.getCanonicalName();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onHttpResponseError(Intent intent) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        String message = intent.getStringExtra(Constants.IntentExtras.MESSAGE);
        Log.e(getTag(), message);
        showErrorDialog(message);
    }

    @Override
    public void onHttpRequestComplete(Intent intent) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1) == Constants.ApiRequestId.GET_TRENDING_SHOWS) {

            try {
                String trendingShows = intent.getStringExtra(Constants.IntentExtras.MESSAGE);
                JSONArray jsonArray = new JSONArray(trendingShows);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject trendingShowsObj = jsonArray.getJSONObject(i);
                    String showNumber = trendingShowsObj.getString(Constants.JsonKeys.TRENDING_SHOWS_NUMBER);
                    String showImageUrl = trendingShowsObj.getString("img");
                    showImageUrl = showImageUrl.replace("\\", "");
                    String showUrl = trendingShowsObj.getString("lnk");
                    String showName = trendingShowsObj.getString("name");
                    String airTime = trendingShowsObj.getString("air");
                    TrendingShowDetails trendingShowDetails = new TrendingShowDetails();
                    trendingShowDetails.setShowName(showName);
                    trendingShowDetails.setAiringChannelName(airTime);
                    trendingShowDetails.setImageUrl(showImageUrl);
                    trendingShowDetailsList.add(trendingShowDetails);
                }
                this.trendingShowsListAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
