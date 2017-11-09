package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.PostBinnacleTask;
import com.octo_spoon.octo_spoon_mobile.R;

public class StageImplementationActivity extends AppCompatActivity {

    private FloatingActionButton fabToEvaluate;


    private EditText editFromDate;
    private EditText editDates;
    private EditText editFinalDate;
    private EditText editObservations;
    private EditText editAdvances;
    private EditText editObstacles;
    private EditText editNewIdeas;
    private DBHelper vosdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_implementation);
        getSupportActionBar().setTitle("Implementar");

        vosdb = new DBHelper(this);

        editFromDate = (EditText) findViewById(R.id.edit_initial_date);
        editDates = (EditText) findViewById(R.id.edit_dates);
        editFinalDate = (EditText) findViewById(R.id.edit_final_date);
        editObservations = (EditText) findViewById(R.id.edit_observations);
        editAdvances = (EditText) findViewById(R.id.edit_advances);
        editObstacles = (EditText) findViewById(R.id.edit_obstacles);
        editNewIdeas = (EditText) findViewById(R.id.edit_new_ideas);

        fabToEvaluate = (FloatingActionButton) findViewById(R.id.fab_to_evaluate);
        fabToEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    new PostBinnacleTask(
                            vosdb,
                            editFromDate.getText().toString(),
                            editFinalDate.getText().toString(),
                            editDates.getText().toString(),
                            editObservations.getText().toString(),
                            editAdvances.getText().toString(),
                            editObstacles.getText().toString(),
                            editNewIdeas.getText().toString(),
                            StageImplementationActivity.this
                    ).execute();
                } catch (Exception e){
                    Toast.makeText(StageImplementationActivity.this, "No se logró grabar la bitácora", Toast.LENGTH_SHORT).show();

                }

                // TODO: 07-11-2017 IMPLEMENTATION REQUEST
                startActivity(StageEvaluateActivity.getIntent(StageImplementationActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageImplementationActivity.class);
        return intent;
    }
}
