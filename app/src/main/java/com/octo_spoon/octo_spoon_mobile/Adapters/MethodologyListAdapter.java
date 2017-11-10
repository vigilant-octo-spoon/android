package com.octo_spoon.octo_spoon_mobile.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.octo_spoon.octo_spoon_mobile.Book.StageCommunicateActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StageEvaluateActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StageImplementationActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StagePlanificationActivity;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.Methodology;

import java.util.List;

import com.octo_spoon.octo_spoon_mobile.R;

/**
 * Created by Pablo on 19/10/16.
 */


public class MethodologyListAdapter extends ArrayAdapter<Methodology> {
    public MethodologyListAdapter(Context context, List<Methodology> objects) {
        super(context, 0, objects);

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

        Methodology methodology = getItem(position);
        final int meth_id = methodology.id;
        //Set all button text to gray
        button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        button4.setClickable(false);
        if (methodology.step >= 3) {
            button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StagePlanificationActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", false);
                    getContext().startActivity(intent);
                }
            });
            button1.setClickable(true);
        }
        if (methodology.step >= 4) {
            button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StagePlanificationActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", true);
                    getContext().startActivity(intent);
                }
            });
            button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageImplementationActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", false);
                    getContext().startActivity(intent);
                }
            });
            button2.setClickable(true);
        }
        if (methodology.step >= 5) {
            button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageImplementationActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", true);
                    getContext().startActivity(intent);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageEvaluateActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", false);
                    getContext().startActivity(intent);
                }
            });
            button3.setClickable(true);
        }
        if (methodology.step >= 6) {
            button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageEvaluateActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", true);
                    getContext().startActivity(intent);
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageCommunicateActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", false);
                    getContext().startActivity(intent);
                }
            });
            button4.setClickable(true);
        }
        if (methodology.step >= 7) {
            button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), StageEvaluateActivity.class);
                    intent.putExtra("meth_id", meth_id);
                    intent.putExtra("read_only", true);
                    getContext().startActivity(intent);
                }
            });
        }
        fileName.setText(methodology.title);

        return convertView;
    }
}
