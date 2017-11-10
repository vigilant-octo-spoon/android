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
import com.octo_spoon.octo_spoon_mobile.Backend.PostReportTask;
import com.octo_spoon.octo_spoon_mobile.Backend.UpdateStep;
import com.octo_spoon.octo_spoon_mobile.MainActivity;
import com.octo_spoon.octo_spoon_mobile.R;

public class StageCommunicateActivity extends AppCompatActivity {

    private FloatingActionButton fabToEnd;

    private EditText editComments;
    private int meth_id;
    private boolean read_only  = false;
    private DBHelper vosdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_communicate);

        meth_id = getIntent().getIntExtra("meth_id", -1);;
        read_only = getIntent().getBooleanExtra("read_only", false);

        getSupportActionBar().setTitle("Comunicar");
        vosdb = new DBHelper(this);



        editComments = (EditText) findViewById(R.id.edit_communicate);

        fabToEnd = (FloatingActionButton) findViewById(R.id.fab_to_end);
        fabToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    new PostReportTask(
                            vosdb,
                            editComments.getText().toString(),
                            StageCommunicateActivity.this,
                            meth_id
                    ).execute();
                } catch (Exception e) {
                    Toast.makeText(StageCommunicateActivity.this, "No se logró grabar los comentarios", Toast.LENGTH_SHORT).show();

                }
                try {
                    new UpdateStep(vosdb, StageCommunicateActivity.this, meth_id).execute().get();
                } catch (Exception e) {
                    Log.i("error", e.toString());
                }
                StageCommunicateActivity.this.finish();
            }
        });
        if (read_only) {
            fillWithStoredValues();
            removeEditingProperties();
        }
    }

    private void fillWithStoredValues() {
    }

    private void removeEditingProperties() {
        editComments.setInputType(InputType.TYPE_NULL);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageCommunicateActivity.class);
        return intent;
    }
}
