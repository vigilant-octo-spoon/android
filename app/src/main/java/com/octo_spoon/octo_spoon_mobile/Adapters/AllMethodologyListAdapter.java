package com.octo_spoon.octo_spoon_mobile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.FollowMethodology;
import com.octo_spoon.octo_spoon_mobile.Book.StageCommunicateActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StageEvaluateActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StageImplementationActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StagePlanificationActivity;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.AllMethodology;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.Methodology;

import java.util.List;

import com.octo_spoon.octo_spoon_mobile.R;

/**
 * Created by Pablo on 19/10/16.
 */


public class AllMethodologyListAdapter extends ArrayAdapter<AllMethodology> {

    public Context contextApp;
    public AllMethodologyListAdapter(Context context, List<AllMethodology> objects) {
        super(context, 0, objects);
        this.contextApp = context;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_methodology,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView fileName = convertView.findViewById(R.id.textView_methodologyTitle);
        Button button1 = convertView.findViewById(R.id.plan_button);
        Button button2 = convertView.findViewById(R.id.impl_button);
        Button button3 = convertView.findViewById(R.id.eval_button);
        Button button4 = convertView.findViewById(R.id.comm_button);

        final AllMethodology methodology = getItem(position);
        final int meth_id = methodology.id;
        //Set all button text to gray
        button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
        button1.setClickable(true);
        button2.setClickable(false);
        button3.setClickable(false);
        button4.setClickable(false);
        button1.setText(R.string.addMeth);
        button2.setVisibility(View.GONE);
        button3.setVisibility(View.GONE);
        button4.setVisibility(View.GONE);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    System.out.println("PSD: About to do a follow");
                    new FollowMethodology(new DBHelper(contextApp), contextApp, meth_id, methodology.title).execute();
                } catch (Exception e) {
                    System.out.println("PSD: Failure doing follow");
                }

            }
        });

        fileName.setText(methodology.title);

        return convertView;
    }
}
