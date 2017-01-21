package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextstep();
    }

    public void nextstep(){
        //// TODO:Check if user have opened app for first time if yes 'rookie'=true else false
    boolean rookie=true;
        if(rookie){
            Intent ontent=new Intent(this,FirstActivity.class);
            startActivity(ontent);
        }
        else{
            Intent notent=new Intent(this,HomeActivity.class);
            startActivity(notent);
        }
    }
}
