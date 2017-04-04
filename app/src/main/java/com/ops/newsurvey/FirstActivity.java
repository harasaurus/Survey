package com.ops.newsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button log=(Button) findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Button sup=(Button) findViewById(R.id.signup);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        //to check if user have logged on previously
        PrefManager prefManager = new PrefManager(this);
        if (!(prefManager.getUser().equals("0")))
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }

}
