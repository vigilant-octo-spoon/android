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
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    private EditText editUsersReflexion;
    private EditText editUsersSuggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_evaluate);

        getSupportActionBar().setTitle("Evaluar");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        fabToCommunicate = (FloatingActionButton) findViewById(R.id.fab_to_communicate);
        setFabToCommunicateListener();
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

                editUsersReflexion = fragments.get(1).getView().findViewById(R.id.edit_reflexion_step_one);
                editUsersSuggestions = fragments.get(1).getView().findViewById(R.id.edit_reflexion_step_two);

                // TODO: 07-11-2017 API REQUEST
                startActivity(StageCommunicateActivity.getIntent(StageEvaluateActivity.this));

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
