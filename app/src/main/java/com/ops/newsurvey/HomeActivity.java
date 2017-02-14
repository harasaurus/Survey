package com.ops.newsurvey;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import static android.R.attr.onClick;

public class HomeActivity extends AppCompatActivity {

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
    }
}
