package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.PostBinnacleTask;
import com.octo_spoon.octo_spoon_mobile.Backend.UpdateStep;
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

    private int meth_id;
    private boolean read_only  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_implementation);
        meth_id = getIntent().getIntExtra("meth_id", -1);;
        read_only = getIntent().getBooleanExtra("read_only", false);
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
                            StageImplementationActivity.this,
                            meth_id
                    ).execute();
                } catch (Exception e){
                    Toast.makeText(StageImplementationActivity.this, "No se logró grabar la bitácora", Toast.LENGTH_SHORT).show();

                }

                try {
                    new UpdateStep(vosdb, StageImplementationActivity.this, meth_id).execute().get();
                } catch (Exception e) {
                    Log.i("error", e.toString());
                    return;
                }
                StageImplementationActivity.this.finish();
            }
        });
        if (read_only) {
            fillWithStoredValues();
            //removeEditingProperties();
        }
    }

    private void fillWithStoredValues() {
        //TODO: Bring info from DB and fill the fields
    }


    private void removeEditingProperties() {
        editFromDate.setInputType(InputType.TYPE_NULL);
        editDates.setInputType(InputType.TYPE_NULL);
        editFinalDate.setInputType(InputType.TYPE_NULL);
        editObservations.setInputType(InputType.TYPE_NULL);
        editAdvances.setInputType(InputType.TYPE_NULL);
        editObstacles.setInputType(InputType.TYPE_NULL);
        editNewIdeas.setInputType(InputType.TYPE_NULL);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageImplementationActivity.class);
        return intent;
    }
}
