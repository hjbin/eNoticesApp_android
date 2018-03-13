package com.twinly.enotices.enoticesapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.adapter.ActivityListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.ActivityModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ActivityListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ActivityListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivityListFragment extends Fragment implements BusinessResponse{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SCHOOL_DB = "school_db";
    private static final String SECRET_ID = "secret_id";
    private static final String TYPE="type";
    private static final String FILTER="filter";

    // TODO: Rename and change types of parameters
    private String school_db;
    private String secret_id;
    private String filter;
    private String type;

    private ListView lv_public;
    private ActivityModel activityModel;
    private ActivityListAdapter listAdapter;

    private OnFragmentInteractionListener mListener;

    public ActivityListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param school_db Parameter 1.
     * @param secret_id Parameter 2.
     * @return A new instance of fragment ActivityListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivityListFragment newInstance(String school_db, String secret_id,String type,String filter) {
        ActivityListFragment fragment = new ActivityListFragment();
        Bundle args = new Bundle();
        args.putString(SCHOOL_DB, school_db);
        args.putString(SECRET_ID, secret_id);
        args.putString(TYPE,type);
        args.putString(FILTER,filter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            school_db = getArguments().getString(SCHOOL_DB);
            secret_id = getArguments().getString(SECRET_ID);
            type=getArguments().getString(TYPE);
            filter=getArguments().getString(FILTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_public_listview,null);


        lv_public=view.findViewById(R.id.lv_public);


        activityModel=new ActivityModel(getActivity());
        activityModel.addResponseListener(this);
        activityModel.getActivityList(school_db,secret_id,filter,type);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnMessageResponse(String url, JSONObject jo, AjaxStatus status) throws JSONException {
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.SHOW_UNREAD_ACTIVITY))){
            Log.i("[ActivityList]","[show_activity]: "+jo.toString());
            if (jo.optBoolean("logged")){
                if (listAdapter==null){
                    listAdapter=new ActivityListAdapter(this.getActivity(),activityModel.actList);
                    lv_public.setAdapter(listAdapter);
                }else {
                    listAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
