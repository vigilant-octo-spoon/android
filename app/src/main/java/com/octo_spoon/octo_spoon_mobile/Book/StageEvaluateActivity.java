package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.PostEvaluationTask;
import com.octo_spoon.octo_spoon_mobile.Backend.UpdateStep;
import com.octo_spoon.octo_spoon_mobile.R;

import java.util.List;

public class StageEvaluateActivity extends AppCompatActivity {

    private FloatingActionButton fabToCommunicate;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private EditText editProcessConnect;
    private EditText editProcessChoose;
    private EditText editProcessPlan;
    private EditText editProcessImplementWorked;
    private EditText editProcessImplementToImprove;
    private EditText editProcessImplementToScale;

    private EditText editUsersReflection;
    private EditText editUsersSuggestions;

    private int meth_id;
    private boolean read_only  = false;

    private DBHelper vosdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_evaluate);

        meth_id = getIntent().getIntExtra("meth_id", -1);;
        read_only = getIntent().getBooleanExtra("read_only", false);

        getSupportActionBar().setTitle("Evaluar");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        vosdb = new DBHelper(this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        fabToCommunicate = (FloatingActionButton) findViewById(R.id.fab_to_communicate);
        setFabToCommunicateListener();

        if (read_only) {
            fillWithStoredValues();
            //removeEditingProperties();
        }
    }

    private void fillWithStoredValues() {
    }

    private void removeEditingProperties() {
        editProcessConnect.setInputType(InputType.TYPE_NULL);
        editProcessChoose.setInputType(InputType.TYPE_NULL);
        editProcessPlan.setInputType(InputType.TYPE_NULL);
        editProcessImplementWorked.setInputType(InputType.TYPE_NULL);
        editProcessImplementToImprove.setInputType(InputType.TYPE_NULL);
        editProcessImplementToScale.setInputType(InputType.TYPE_NULL);
        editUsersReflection.setInputType(InputType.TYPE_NULL);
        editUsersSuggestions.setInputType(InputType.TYPE_NULL);
    }

    public void setFabToCommunicateListener(){
        fabToCommunicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = StageEvaluateActivity.this.getSupportFragmentManager();
                List<Fragment> fragments = fragmentManager.getFragments();

                editProcessConnect = fragments.get(0).getView().findViewById(R.id.edit_step_one);
                editProcessChoose = fragments.get(0).getView().findViewById(R.id.edit_step_two);
                editProcessPlan = fragments.get(0).getView().findViewById(R.id.edit_step_three);
                editProcessImplementWorked = fragments.get(0).getView().findViewById(R.id.edit_step_four_firss);
                editProcessImplementToImprove = fragments.get(0).getView().findViewById(R.id.edit_step_four_second);
                editProcessImplementToScale = fragments.get(0).getView().findViewById(R.id.edit_step_four_third);

                editUsersReflection = fragments.get(1).getView().findViewById(R.id.edit_reflexion_step_one);
                editUsersSuggestions = fragments.get(1).getView().findViewById(R.id.edit_reflexion_step_two);

                try {
                    new PostEvaluationTask(
                            vosdb,
                            editProcessConnect.getText().toString(),
                            editProcessChoose.getText().toString(),
                            editProcessPlan.getText().toString(),
                            editProcessImplementWorked.getText().toString()+";"+
                            editProcessImplementToImprove.getText().toString()+";"+
                            editProcessImplementToScale.getText().toString(),
                            editUsersReflection.getText().toString(),
                            editUsersSuggestions.getText().toString(),
                            StageEvaluateActivity.this,
                            meth_id
                    ).execute();
                } catch (Exception e) {
                    Toast.makeText(StageEvaluateActivity.this, "No se logró grabar la evaluación", Toast.LENGTH_SHORT).show();

                }

                try {
                    new UpdateStep(vosdb, StageEvaluateActivity.this, meth_id).execute().get();
                } catch (Exception e) {
                    Log.i("error", e.toString());
                }
                StageEvaluateActivity.this.finish();

            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch  (position) {
                case 0:
                    return new EvaluateProcessFragment();
                case 1:
                    return new EvaluateQuestionsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Proceso";
                case 1:
                    return "Consultas a Usuarios";
            }
            return null;
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StageEvaluateActivity.class);
        return intent;
    }

}
