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
import com.octo_spoon.octo_spoon_mobile.Backend.PostReportTask;
import com.octo_spoon.octo_spoon_mobile.MainActivity;
import com.octo_spoon.octo_spoon_mobile.R;

public class StageCommunicateActivity extends AppCompatActivity {

    private FloatingActionButton fabToEnd;

    private EditText editComments;
    private DBHelper vosdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_communicate);

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
                            StageCommunicateActivity.this
                    ).execute();
                } catch (Exception e) {
                    Toast.makeText(StageCommunicateActivity.this, "No se logró grabar los comentarios", Toast.LENGTH_SHORT).show();

                }
                startActivity(MainActivity.getIntent(StageCommunicateActivity.this));
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageCommunicateActivity.class);
        return intent;
    }
}
