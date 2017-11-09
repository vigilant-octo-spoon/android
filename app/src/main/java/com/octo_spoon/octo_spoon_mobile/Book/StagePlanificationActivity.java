package com.octo_spoon.octo_spoon_mobile.Book;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
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
import android.widget.Toast;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.PostPlanningTask;
import com.octo_spoon.octo_spoon_mobile.Backend.PostRoles;
import com.octo_spoon.octo_spoon_mobile.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StagePlanificationActivity extends AppCompatActivity {

    //ROUTE SHEET FRAGMENT ELEMENTS
    private EditText editRouteSheetSelectedMethodology;
    private EditText editRouteSheetInitiative;
    private EditText editRouteSheetObjective;
    private EditText editRouteSheetImplementationPlace;
    private EditText editRouteSheetInitialDate;
    private EditText editRouteSheetFinishDate;
    private EditText editRouteSheetTeamMemberName1;
    private EditText editRouteSheetTeamMemberRol1;
    private EditText editRouteSheetTeamMemberName2;
    private EditText editRouteSheetTeamMemberRol2;
    private EditText editRouteSheetTeamMemberName3;
    private EditText editRouteSheetTeamMemberRol3;
    private EditText editRouteSheetTeamMemberName4;
    private EditText editRouteSheetTeamMemberRol4;
    private EditText editRouteSheetTeamMemberName5;
    private EditText editRouteSheetTeamMemberRol5;

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

    private DBHelper vosdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_planification);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Planificar");
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        vosdb = new DBHelper(this);

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
                editRouteSheetInitialDate = fragments.get(0).getView().findViewById(R.id.edit_initial_date);
                editRouteSheetFinishDate = fragments.get(0).getView().findViewById(R.id.edit_finish_date);
                editRouteSheetTeamMemberName1 = fragments.get(0).getView().findViewById(R.id.edit_member_name_1);
                editRouteSheetTeamMemberRol1 = fragments.get(0).getView().findViewById(R.id.edit_member_rol_1);
                editRouteSheetTeamMemberName2 = fragments.get(0).getView().findViewById(R.id.edit_member_name_2);
                editRouteSheetTeamMemberRol2 = fragments.get(0).getView().findViewById(R.id.edit_member_rol_2);
                editRouteSheetTeamMemberName3 = fragments.get(0).getView().findViewById(R.id.edit_member_name_3);
                editRouteSheetTeamMemberRol3 = fragments.get(0).getView().findViewById(R.id.edit_member_rol_3);
                editRouteSheetTeamMemberName4 = fragments.get(0).getView().findViewById(R.id.edit_member_name_4);
                editRouteSheetTeamMemberRol4 = fragments.get(0).getView().findViewById(R.id.edit_member_rol_4);
                editRouteSheetTeamMemberName5 = fragments.get(0).getView().findViewById(R.id.edit_member_name_5);
                editRouteSheetTeamMemberRol5 = fragments.get(0).getView().findViewById(R.id.edit_member_rol_5);

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

                try {
                    new PostPlanningTask(
                            vosdb,
                            editRouteSheetInitiative.getText().toString(),
                            editRouteSheetObjective.getText().toString(),
                            editRouteSheetImplementationPlace.getText().toString(),
                            editRouteSheetInitialDate.getText().toString(),
                            editRouteSheetFinishDate.getText().toString(),
                            StagePlanificationActivity.this
                    ).execute();
                } catch (Exception e) {
                    Log.i("error",e.toString());
                    Toast.makeText(StagePlanificationActivity.this, "No se logró grabar la hoja de ruta", Toast.LENGTH_SHORT).show();
                }

                ArrayList<EditText> arrayEditNames = new ArrayList<EditText>();
                arrayEditNames.add(editRouteSheetTeamMemberName1);
                arrayEditNames.add(editRouteSheetTeamMemberName2);
                arrayEditNames.add(editRouteSheetTeamMemberName3);
                arrayEditNames.add(editRouteSheetTeamMemberName4);
                arrayEditNames.add(editRouteSheetTeamMemberName5);

                ArrayList<EditText> arrayEditRoles = new ArrayList<EditText>();
                arrayEditNames.add(editRouteSheetTeamMemberRol1);
                arrayEditNames.add(editRouteSheetTeamMemberRol2);
                arrayEditNames.add(editRouteSheetTeamMemberRol3);
                arrayEditNames.add(editRouteSheetTeamMemberRol4);
                arrayEditNames.add(editRouteSheetTeamMemberRol5);

                for (int i = 0; i < arrayEditNames.size(); i++) {
                    if (!arrayEditNames.get(i).getText().toString().isEmpty()) {
                        try {
                            new PostRoles(
                                    vosdb,
                                    arrayEditNames.get(i).getText().toString(),
                                    arrayEditRoles.get(i).getText().toString(),
                                    StagePlanificationActivity.this
                            ).execute();
                        } catch (Exception e) {
                            Log.i("error",e.toString());
                            Toast.makeText(StagePlanificationActivity.this, "No se logró grabar a los usuarios en la hoja de ruta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

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