package com.octo_spoon.octo_spoon_mobile.Book;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octo_spoon.octo_spoon_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanificationRouteSheetFragment extends Fragment {


    public PlanificationRouteSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planification_route_sheet, container, false);
    }

}
