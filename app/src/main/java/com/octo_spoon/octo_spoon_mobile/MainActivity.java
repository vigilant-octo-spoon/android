package com.octo_spoon.octo_spoon_mobile;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.octo_spoon.octo_spoon_mobile.Backend.DBHelper;
import com.octo_spoon.octo_spoon_mobile.Backend.SessionManager;
import com.octo_spoon.octo_spoon_mobile.Book.BookStartActivity;
import com.octo_spoon.octo_spoon_mobile.Book.StageCommunicateActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MethodologyFragment.OnFragmentInteractionListener {

    private DBHelper vosdb;
    private NavigationView navigationView;
    private TextView mUserName, mUserMail;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vosdb = new DBHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(MainActivity.this);

        getSupportActionBar().setTitle(getString(R.string.my_methodologies_header));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.container, new MethodologyFragment());
        ft.commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Use this button to add new methodologies
                Snackbar.make(view, "A creación de metodología ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.main_username);
        mUserMail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.main_usermail);
        processUserData();
    }

    private void processUserData() {
        Cursor res = vosdb.getCurrentUser();
        mUserName.setText(res.getString(2) + " " + res.getString(3));
        mUserMail.setText(res.getString(4));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            sessionManager.eraseSharedPreferences();
            vosdb.clearEntireDB();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_methodologies) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.container, new MethodologyFragment());
            ft.commit();
        } else if (id == R.id.all_methodologies) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.container, new AllMethodologiesFragment());
            ft.commit();

        } else if (id == R.id.to_implementation_book) {
            startActivity(BookStartActivity.getIntent(MainActivity.this));

        } else if (id == R.id.logout) {
            sessionManager.eraseSharedPreferences();
            vosdb.clearEntireDB();
            this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
    }



}
