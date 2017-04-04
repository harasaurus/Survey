package com.ops.newsurvey;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import static android.R.attr.onClick;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton political =(ImageButton) findViewById(R.id.polIcon);
        ImageButton athletic =(ImageButton) findViewById(R.id.spoIcon);
        ImageButton traveller =(ImageButton) findViewById(R.id.traIcon);
        ImageButton celebrity =(ImageButton) findViewById(R.id.popIcon);
        ImageButton casual =(ImageButton) findViewById(R.id.lifIcon);
        ImageButton random =(ImageButton) findViewById(R.id.misIcon);

        political.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,PoliticsActivity.class);
                startActivity(intent);
            }
        });

        athletic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SportsActivity.class);
                startActivity(intent);
            }
        });

        traveller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TravelActivity.class);
                startActivity(intent);
            }
        });

        celebrity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,PopActivity.class);
                startActivity(intent);
            }
        });

        casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LifestyleActivity.class);
                startActivity(intent);
            }
        });

        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,MiscActivity.class);
                startActivity(intent);
            }
        });

        //Toolbar
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);
        Toolbar bar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Home");


        //Navigation Bar
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,bar,R.string.drawer_open,R.string.drawer_close){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)){return true;}
                return super.onOptionsItemSelected(item);
    }
}
