package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.octo_spoon.octo_spoon_mobile.R;

import java.util.List;

public class StagePlanificationActivity extends AppCompatActivity {

    //ROUTE SHEET FRAGMENT ELEMENTS
    private EditText editRouteSheetSelectedMethodology;
    private EditText editRouteSheetInitiative;
    private EditText editRouteSheetObjective;
    private EditText editRouteSheetImplementationPlace;
    private EditText editRouteSheetImplementationPeriod;
    private EditText editRouteSheetTeamMember1;
    private EditText editRouteSheetTeamMember2;
    private EditText editRouteSheetTeamMember3;
    private EditText editRouteSheetTeamMember4;
    private EditText editRouteSheetTeamMember5;

    //ROSOURCES FRAGMENT ELEMENTS
    private EditText editResourcesItem1;
    private EditText editResourcesItem2;
    private EditText editResourcesItem3;
    private EditText editResourcesItem4;
    private EditText editResourcesItem5;
    private EditText editResourcesCondition1;
    private EditText editResourcesCondition2;
    private EditText editResourcesCondition3;
    private EditText editResourcesCondition4;
    private EditText editResourcesCondition5;

    //IMPLEMENTATION FRAGMENT ELEMENTS
    private EditText editDiffusionBeforeAudience;
    private EditText editDiffusionBeforeDifussionChannel;
    private EditText editDiffusionBeforeObjective;
    private EditText editDiffusionDuringAudience;
    private EditText editDiffusionDuringDifussionChannel;
    private EditText editDiffusionDuringObjective;
    private EditText editDiffusionAfterAudience;
    private EditText editDiffusionAfterDifussionChannel;
    private EditText editDiffusionAfterObjective;

    FloatingActionButton fabToImplementation;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_planification);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Planificar");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        fabToImplementation = (FloatingActionButton) findViewById(R.id.fab_to_implementation);
        setFabToImplementationListener();

    }

    public void setFabToImplementationListener() {
        fabToImplementation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 07-11-2017 send info to api

                FragmentManager fragmentManager = StagePlanificationActivity.this.getSupportFragmentManager();
                List<Fragment> fragments = fragmentManager.getFragments();

                //ROUTE SHEET FRAGMENT ELEMENTS
                editRouteSheetSelectedMethodology = fragments.get(0).getView().findViewById(R.id.edit_selected_methodology);
                editRouteSheetInitiative = fragments.get(0).getView().findViewById(R.id.edit_initiative);
                editRouteSheetObjective = fragments.get(0).getView().findViewById(R.id.edit_objective);
                editRouteSheetImplementationPlace = fragments.get(0).getView().findViewById(R.id.edit_implementation_place);
                editRouteSheetImplementationPeriod = fragments.get(0).getView().findViewById(R.id.edit_implementation_period);
                editRouteSheetTeamMember1 = fragments.get(0).getView().findViewById(R.id.edit_team_member_1);
                editRouteSheetTeamMember2 = fragments.get(0).getView().findViewById(R.id.edit_team_member_2);
                editRouteSheetTeamMember3 = fragments.get(0).getView().findViewById(R.id.edit_team_member_3);
                editRouteSheetTeamMember4 = fragments.get(0).getView().findViewById(R.id.edit_team_member_4);
                editRouteSheetTeamMember5 = fragments.get(0).getView().findViewById(R.id.edit_team_member_5);

                //ROSOURCES FRAGMENT ELEMENTS
                editResourcesItem1 = fragments.get(1).getView().findViewById(R.id.edit_resources_1);
                editResourcesItem2 = fragments.get(1).getView().findViewById(R.id.edit_resources_2);
                editResourcesItem3 = fragments.get(1).getView().findViewById(R.id.edit_resources_3);
                editResourcesItem4 = fragments.get(1).getView().findViewById(R.id.edit_resources_4);
                editResourcesItem5 = fragments.get(1).getView().findViewById(R.id.edit_resources_5);
                editResourcesCondition1 = fragments.get(1).getView().findViewById(R.id.edit_other_conditions_1);
                editResourcesCondition2 = fragments.get(1).getView().findViewById(R.id.edit_other_conditions_2);
                editResourcesCondition3 = fragments.get(1).getView().findViewById(R.id.edit_other_conditions_3);
                editResourcesCondition4 = fragments.get(1).getView().findViewById(R.id.edit_other_conditions_4);
                editResourcesCondition5 = fragments.get(1).getView().findViewById(R.id.edit_other_conditions_5);

                //IMPLEMENTATION FRAGMENT ELEMENTS
                editDiffusionBeforeAudience = fragments.get(1).getView().findViewById(R.id.editText26);
                editDiffusionBeforeDifussionChannel = fragments.get(1).getView().findViewById(R.id.editText27);
                editDiffusionBeforeObjective = fragments.get(1).getView().findViewById(R.id.editText28);
                editDiffusionDuringAudience = fragments.get(1).getView().findViewById(R.id.editText29);
                editDiffusionDuringDifussionChannel = fragments.get(1).getView().findViewById(R.id.editText30);
                editDiffusionDuringObjective = fragments.get(1).getView().findViewById(R.id.editText31);
                editDiffusionAfterAudience = fragments.get(1).getView().findViewById(R.id.editText32);
                editDiffusionAfterDifussionChannel = fragments.get(1).getView().findViewById(R.id.editText33);
                editDiffusionAfterObjective = fragments.get(1).getView().findViewById(R.id.editText34);

                // TODO: 07-11-2017 REQUEST

                Log.i("aaa",editRouteSheetSelectedMethodology.getText().toString());
                Log.i("aaa",editResourcesItem1.getText().toString());
                Log.i("aaa",editDiffusionBeforeAudience.getText().toString());

                startActivity(StageImplementationActivity.getIntent(StagePlanificationActivity.this));

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
                    return new PlanificationRouteSheetFragment();
                case 1:
                    return new PlanificationResourcesFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hoja de Ruta";
                case 1:
                    return "Recursos y Difusión";
            }
            return null;
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,StagePlanificationActivity.class);
        return intent;
    }
}
