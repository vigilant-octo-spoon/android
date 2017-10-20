package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.octo_spoon.octo_spoon_mobile.R;

public class StagePlanActivity extends AppCompatActivity {

    private FloatingActionButton fabToImplementation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_plan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Planificar");

        fabToImplementation = (FloatingActionButton) findViewById(R.id.fab_to_implementation);
        fabToImplementation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StageImplementationActivity.getIntent(StagePlanActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StagePlanActivity.class);
        return intent;
    }
}
