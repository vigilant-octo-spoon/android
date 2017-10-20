package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.octo_spoon.octo_spoon_mobile.R;

public class StageChooseActivity extends AppCompatActivity {

    private FloatingActionButton fabToPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_choose);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Elegir");

        fabToPlan = (FloatingActionButton) findViewById(R.id.fab_to_plan);
        fabToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StagePlanActivity.getIntent(StageChooseActivity.this));
            }
        });

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageChooseActivity.class);
        return intent;
    }
}
