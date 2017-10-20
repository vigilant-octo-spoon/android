package com.octo_spoon.octo_spoon_mobile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.VideoView;

import com.octo_spoon.octo_spoon_mobile.Backend.CurrentInformationHelper;
import com.octo_spoon.octo_spoon_mobile.ViewStructure.Methodology;

public class MethodologyActivity extends AppCompatActivity {

    private static String ID_KEY="id_key";

    private Methodology methodology;
    private CurrentInformationHelper db;

    private VideoView videoView;
    private TextView textDescription;
    private TextView textOrganization;
    private TextView textCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methodology);
        db = CurrentInformationHelper.getInstance();

        videoView = (VideoView) findViewById(R.id.videoView);
        textDescription = (TextView) findViewById(R.id.text_description);
        textOrganization = (TextView) findViewById(R.id.text_organization);
        textCategory = (TextView) findViewById(R.id.text_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        getMethodology();

        toolbar.setTitle(methodology.getTitle());
        textDescription.setText(methodology.getDescription());
        textOrganization.setText(methodology.getOrganization());
        textCategory.setText(methodology.getCategory());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // TODO: 19-10-2017 fetch methodology
    private void getMethodology() {
        final int methodologyId = getIntent().getIntExtra(ID_KEY,0);
        Log.i("METHODOLOGYID", Integer.toString(methodologyId));
        methodology = db.userMethodologies.get(methodologyId);
        Log.i("METHODOLOGY",methodology.toString());

    }

    public static Intent getIntent(Context context, int methodologyId) {
        Intent intent = new Intent(context,MethodologyActivity.class);
        intent.putExtra(ID_KEY, methodologyId-1);
        return intent;
    }
}
