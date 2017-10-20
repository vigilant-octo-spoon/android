package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.octo_spoon.octo_spoon_mobile.R;

public class StageEvaluateActivity extends AppCompatActivity {

    private FloatingActionButton fabToCommunicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_evaluate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Evaluar");

        fabToCommunicate = (FloatingActionButton) findViewById(R.id.fab_to_communicate);
        fabToCommunicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StageCommunicateActivity.getIntent(StageEvaluateActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageEvaluateActivity.class);
        return intent;
    }
}
