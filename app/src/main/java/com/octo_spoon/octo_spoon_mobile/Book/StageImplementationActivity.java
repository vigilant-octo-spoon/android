package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.octo_spoon.octo_spoon_mobile.R;

public class StageImplementationActivity extends AppCompatActivity {

    private FloatingActionButton fabToEvaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_implementation);
        getSupportActionBar().setTitle("Implementar");

        fabToEvaluate = (FloatingActionButton) findViewById(R.id.fab_to_evaluate);
        fabToEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StageEvaluateActivity.getIntent(StageImplementationActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageImplementationActivity.class);
        return intent;
    }
}
