package com.ops.newsurvey;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private int[] images;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //logout for navigation drawer
        TextView logout = (TextView) findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefmanager = new PrefManager(HomeActivity.this);
                prefmanager.endSession();

                Intent intent = new Intent(HomeActivity.this,FirstActivity.class);
               startActivity(intent);
            }
        });


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
        //home page slider
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        images = new int[]{R.layout.slide1,R.layout.slide2,R.layout.slide3};

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
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
// slider of home page
    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }
    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(images[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}