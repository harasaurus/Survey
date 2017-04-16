package com.ops.newsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar bar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Profile");

        //setting profile pic
        DatabaseManager DBManager = new DatabaseManager(this);
        View navHead = findViewById(R.id.profile);
        ImageButton propic = (ImageButton) navHead.findViewById(R.id.profile_pic);
        propic.setImageResource(DBManager.getPicId(User.UID));
    }
}
