package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo_spoon.octo_spoon_mobile.R;


public class EvaluateQuestionsFragment extends Fragment {
    public EvaluateQuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluate_questions, container, false);
    }

}
