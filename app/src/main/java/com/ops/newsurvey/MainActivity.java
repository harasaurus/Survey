package com.ops.newsurvey;

import android.app.Activity;
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

    boolean rookie=first_TF();
        if(rookie){
            Intent ontent=new Intent(this,FirstActivity.class);
            startActivity(ontent);
        }
        else{
            Intent notent=new Intent(this,LoginActivity.class);
            startActivity(notent);
        }
    }

    public boolean first_TF(){
        //// TODO:Check if user have opened app for first time if yes 'rookie'=true else false
        return true;
    }
}
