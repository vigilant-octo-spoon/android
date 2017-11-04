package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.octo_spoon.octo_spoon_mobile.R;

public class BookStartActivity extends AppCompatActivity {


    private FloatingActionButton fabToConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_main);

        fabToConnect = (FloatingActionButton) findViewById(R.id.fab_to_connect);
        fabToConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(StagePlanificationActivity.getIntent(BookStartActivity.this));
            }
        });

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,BookStartActivity.class);
        return intent;
    }
}
