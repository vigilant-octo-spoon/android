package com.octo_spoon.octo_spoon_mobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView fileName = (TextView) convertView.findViewById(R.id.textView_methodologyTitle);
        ImageView fileIcon = (ImageView) convertView.findViewById(R.id.imageView_methodologyIcon);

        Methodology methodology = getItem(position);
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
        fileName.setText(methodology.getTitle());

        return convertView;
    }
}
