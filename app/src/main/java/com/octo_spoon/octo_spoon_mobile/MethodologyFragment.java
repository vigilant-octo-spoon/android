package com.octo_spoon.octo_spoon_mobile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.octo_spoon.octo_spoon_mobile.Adapters.MethodologyListAdapter;
import com.octo_spoon.octo_spoon_mobile.Backend.CurrentInformationHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.FetchUserMethodologies;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.Methodology;


public class MethodologyFragment extends Fragment {

    private DBHelper vosdb;
    private CurrentInformationHelper db;
    public FetchUserMethodologies fumTask = null;
    private ListView lv_methodologies;
    public TextView emptyText;
    public MethodologyListAdapter mla;
    private SwipeRefreshLayout refreshMethodologies;
    private View mProgressView;

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    public MethodologyFragment() {
        // Required empty public constructor
    }

    public static MethodologyFragment newInstance(String param1, String param2) {
        MethodologyFragment fragment = new MethodologyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vosdb = new DBHelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_methodology, container, false);
        db = CurrentInformationHelper.getInstance();
        lv_methodologies = (ListView) root.findViewById(R.id.listView_methodologies);
        lv_methodologies.setOverScrollMode(View.OVER_SCROLL_NEVER);
        refreshMethodologies = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefresh_methodology);
        mProgressView = root.findViewById(R.id.methodology_progress);
        emptyText = (TextView) root.findViewById(R.id.filesEmpty);
        emptyText.setTextColor(Color.DKGRAY);
        mla = new MethodologyListAdapter(getActivity(), db.userMethodologies);
        lv_methodologies.setAdapter(mla);
        Log.i("SIZE",Integer.toString(db.userMethodologies.size()));

        if (db.userMethodologies.size() == 0) {
            showProgress(true);

            fumTask = new FetchUserMethodologies(vosdb, getActivity(), this);
            fumTask.execute();
        }
        lv_methodologies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Methodology methodology = mla.getItem(i);
                startActivity(MethodologyActivity.getIntent(getActivity(), methodology.getId()));
            }
        });
        return root;
    }

    public void setEmptyVisibility() {
        CurrentInformationHelper db = CurrentInformationHelper.getInstance();
        System.out.println("PSD:" + db.userMethodologies.size());
        if (db.userMethodologies.size() > 0) {
            emptyText.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.VISIBLE);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        mla.notifyDataSetChanged();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            refreshMethodologies.setVisibility(show ? View.GONE : View.VISIBLE);
            refreshMethodologies.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    refreshMethodologies.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            emptyText.setVisibility(show ? View.GONE : View.VISIBLE);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            refreshMethodologies.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
