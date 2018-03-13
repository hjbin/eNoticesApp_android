package com.twinly.enotices.enoticesapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.BeeFramework.model.BeeQuery;
import com.BeeFramework.model.BusinessResponse;
import com.external.androidquery.callback.AjaxStatus;
import com.twinly.enotices.enoticesapp.R;
import com.twinly.enotices.enoticesapp.activity.NoticeContentActivity;
import com.twinly.enotices.enoticesapp.adapter.UnreadNoticeListAdapter;
import com.twinly.enotices.enoticesapp.constants.Constants;
import com.twinly.enotices.enoticesapp.model.MessageModel;
import com.twinly.enotices.enoticesapp.protocol.CHILDREN;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PendingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingFragment extends Fragment implements BusinessResponse {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "secret_id";
    private static final String ARG_PARAM2 = "school_db";

    // TODO: Rename and change types of parameters
    private String secret_id;
    private String school_db;
    private OnFragmentInteractionListener mListener;
    private UnreadNoticeListAdapter mAdapter;
    private ListView lv_msg;

    private MessageModel messageModel;

    public PendingFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param secret_Id Parameter 1.
     * @param school_Db Parameter 2.
     * @return A new instance of fragment PendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingFragment newInstance(String secret_Id, String school_Db) {
        PendingFragment fragment = new PendingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, secret_Id);
        args.putString(ARG_PARAM2, school_Db);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            secret_id = getArguments().getString(ARG_PARAM1);
            school_db = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pending,null);
        lv_msg=view.findViewById(R.id.lv_public);

        lv_msg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getActivity(),"on item click", Toast.LENGTH_SHORT).show();
                Intent it=new Intent(getActivity().getApplicationContext(), NoticeContentActivity.class);
                it.putExtra("school_db",school_db);
                it.putExtra("secret_id",secret_id);
                it.putExtra("notice_id",messageModel.messageList.get(i).id);
                getActivity().startActivity(it);
            }
        });

        messageModel=new MessageModel(this.getActivity());
        messageModel.addResponseListener(this);
        messageModel.getUnreadMessage(secret_id);
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
        if(url.startsWith(BeeQuery.getAbsoluteUrl(Constants.SHOW_UNREAD_MESSAGE))){
            Log.i("[PendingFragment]","[show_unread_message]: "+jo.toString());
            if (mAdapter==null){
                mAdapter=new UnreadNoticeListAdapter(getActivity(),messageModel.messageList);
                lv_msg.setAdapter(mAdapter);
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
