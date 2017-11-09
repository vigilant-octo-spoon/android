package com.octo_spoon.octo_spoon_mobile.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
        //Set all button text to gray
        button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepToDo));
        if (methodology.step >= 3) {
            button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
        }
        if (methodology.step >= 4) {
            button1.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
        }
        if (methodology.step >= 5) {
            button2.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
        }
        if (methodology.step >= 6) {
            button3.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
            button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepInCourse));
        }
        if (methodology.step >= 7) {
            button4.setTextColor(ContextCompat.getColor(getContext(),R.color.stepFinished));
        }

        /*

        if (documentFile.type.equals("folder") && !documentFile.id.equals("0")){
            fileIcon.setImageResource(R.drawable.ic_folder_open_black_24dp);
        }
        else {
            if (documentFile.isDrive){
                fileIcon.setImageResource(R.drawable.ic_cloud_download_black_24dp);
            } else if (documentFile.id.equals("0")){
                fileIcon.setImageResource(R.drawable.ic_arrow_back_black_24dp);
            } else {
                fileIcon.setImageResource(R.drawable.ic_file_download_black_24dp);
            }
        }*/
        fileName.setText(methodology.title);

        return convertView;
    }
}
