package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.octo_spoon.octo_spoon_mobile.R;


/**
 * Created by ESTEBANFML on 20-10-2017.
 */

public class VideoListAdapter  extends BaseAdapter {

    private final Context context;
    private final HashMap<String,String> itemMap;

    private TextView itemName;
    private TextView itemDescription;
    private String[] mKeys;

    public VideoListAdapter(Context context, HashMap<String,String> itemMap) {
        this.context = context;
        this.itemMap = itemMap;
        this.mKeys = itemMap.keySet().toArray(new String[itemMap.size()]);

    }

    @Override
    public int getCount() {
        if(itemMap != null) {
            return itemMap.size();
        }

        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return itemMap.get(mKeys[position]);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //firebaseFirebase.init();

        if (convertView == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.video_list_item,null);
        }

        itemName = convertView.findViewById(R.id.item_name);
        itemDescription = convertView.findViewById(R.id.item_description);

        Map.Entry<String,String> entry=itemMap.entrySet().iterator().next();

        String key = mKeys[position];
        String value = getItem(position).toString();

        itemName.setText(key);
        itemDescription.setText(value);

        return convertView;
    }

}
