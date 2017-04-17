package com.ops.newsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar bar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Profile");

        //getting userdata
        DatabaseManager DBManager = new DatabaseManager(this);
        int PicId=DBManager.getPicId(User.UID);
        String Name = DBManager.getName(User.UID);
        String Username = DBManager.getUsername(User.UID);
        String Email = DBManager.getEmail(User.UID);
        String Gender = DBManager.getGender(User.UID);
        int NoOfQuestions = DBManager.getQuestions(User.UID);


        //setting userdat
        //Setting profile pic and Username
        View navHead = findViewById(R.id.profile);
        ImageButton propic = (ImageButton) navHead.findViewById(R.id.profile_pic);
        propic.setImageResource(PicId);
        TextView usernameText = (TextView) navHead.findViewById(R.id.nav_username_text);
        usernameText.setText(Username);
        //setting name and other details
        //creating views
        TextView NameTextView = (TextView) findViewById(R.id.profile_name);
        TextView EmailTextView = (TextView) findViewById(R.id.profile_email);
        TextView GenderTextView = (TextView) findViewById(R.id.profile_gender);
        TextView QuestsTextView = (TextView) findViewById(R.id.profile_quests);

        //setting texts of the views
        NameTextView.setText("Name:"+Name);
        EmailTextView.setText("Email:"+Email);
        GenderTextView.setText("Gender:"+Gender);
        QuestsTextView.setText("No. of Questions:"+NoOfQuestions);
    }
}
