package com.ops.newsurvey;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;
    private Button mBtnSkip, mBtnNext;
    private PrefManager mPrefManager;
    private SQLiteDatabase mDb;
    private DatabaseManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        mPrefManager = new PrefManager(this);
        if (!mPrefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);

        //Database Initialization
        mManager = new DatabaseManager(this);
        //adding User
        mManager.addUser(0,"HP","Harshit Pandey","admin1@pi.com","M","phi",R.drawable.ft);
        mManager.addUser(1,"SS","Sagnik Saha","admin2@pi.com","M","sigma",R.drawable.ft);
        mManager.addUser(2,"RR","Rishabh Rai","admin3@pi.com","M","ro",R.drawable.ft);

        //adding Questions
        addQuestions();

        // initialization of variables for slide show
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnNext = (Button) findViewById(R.id.btn_next);


        // mLayouts of all welcome sliders
        // add few more mLayouts if you want
        mLayouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        // adding bottom mDots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < mLayouts.length) {
                    // move to next screen
                    mViewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

    }

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInactive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0)
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    //GOTO Activity first
    private void launchHomeScreen() {
        mPrefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, FirstActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == mLayouts.length - 1) {
                // last page. make button text to GOT IT
                mBtnNext.setText(getString(R.string.start));
                mBtnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                mBtnNext.setText(getString(R.string.next));
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
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

            View view = layoutInflater.inflate(mLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
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

    private void addQuestions(){
        ArrayList<String> opts= new ArrayList<String>();
        opts.add("Yes");
        opts.add("No");
        opts.add("Can't Say");
        ArrayList<Integer> results = new ArrayList<Integer>();
        results.add(0);
        results.add(0);
        results.add(0);
        int responses =0;

        //political
        String cat = "POL";
        mManager.addQuestion(0,cat,"Do you care about indian politics ?",opts,results,responses);
        mManager.addQuestion(1,cat,"Do you think Indian politics is moving in right direction ?",opts,results,responses);
        mManager.addQuestion(2,cat,"Can India and Pakistan be ever friend ?",opts,results,responses);
        mManager.addQuestion(3,cat,"Should general and assembly election be conducted together in India ?",opts,results,responses);
        mManager.addQuestion(4,cat,"Is UN partial ro US ?",opts,results,responses);
        mManager.addQuestion(5,cat,"Do you think media is unbiased ?",opts,results,responses);
        mManager.addQuestion(6,cat,"Is the world politics progressing towards a peaceful and better future ? ",opts,results,responses);
        mManager.addQuestion(7,cat,"Will a country be better with isolation of student politics and civil polity ?",opts,results,responses);
        mManager.addQuestion(8,cat,"Is world without border possible in future ?",opts,results,responses);
        mManager.addQuestion(9,cat,"Should natural resources belong to community ?",opts,results,responses);

        //sports
        cat="SPO";
        mManager.addQuestion(10,cat,"Do you care about sports?",opts,results,responses);
        mManager.addQuestion(11,cat,"Should Major Dhyanchand awarded Bhartratn ?",opts,results,responses);
        mManager.addQuestion(12,cat,"Should cricket be considered as national game of India ?",opts,results,responses);
        mManager.addQuestion(13,cat,"Should more movies be made on sports ?",opts,results,responses);
        mManager.addQuestion(14,cat,"Does cricket negatively influencing other game ?",opts,results,responses);
        mManager.addQuestion(15,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(16,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(17,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(18,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(19,cat,"Question 1",opts,results,responses);

        //Travel
        cat="TRA";
        mManager.addQuestion(20,cat,"Do you like travling ?",opts,results,responses);
        mManager.addQuestion(21,cat,"Do you like to go for an unplaned trip ?",opts,results,responses);
        mManager.addQuestion(22,cat,"Do you like to travel with relatives ?",opts,results,responses);
        mManager.addQuestion(23,cat,"Do you like to travel a long journey alone ?",opts,results,responses);
        mManager.addQuestion(24,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(25,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(26,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(27,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(28,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(29,cat,"Question 1",opts,results,responses);

        //Lifestyle
        cat="LIF";
        mManager.addQuestion(30,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(31,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(32,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(33,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(34,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(35,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(36,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(37,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(38,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(39,cat,"Question 1",opts,results,responses);

        //pop
        cat="POP";
        mManager.addQuestion(40,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(41,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(42,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(43,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(44,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(45,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(46,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(47,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(48,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(49,cat,"Question 1",opts,results,responses);

        //misc
        cat="MIS";
        mManager.addQuestion(50,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(51,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(52,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(53,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(54,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(55,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(56,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(57,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(58,cat,"Question 1",opts,results,responses);
        mManager.addQuestion(59,cat,"Question 1",opts,results,responses);

    }
    }