package com.ops.newsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar bar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Profile");
    }
}
