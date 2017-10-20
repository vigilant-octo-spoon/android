package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.octo_spoon.octo_spoon_mobile.R;

public class BookEndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_end);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,BookEndActivity.class);
        return intent;
    }
}
