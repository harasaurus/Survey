package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        EditText usernameView = (EditText) findViewById(R.id.loginusername);
        String user= usernameView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.loginpassword);
        String pass = passwordView.getText().toString();
        if(authenticate(user,pass))
        {
            startSession();
        }
    }

    private boolean authenticate(String user, String pass){
        if((user.equals("HP")&&pass.equals("phi")) || (user.equals("RR")&&pass.equals("ro")) || (user.equals("SS")&&pass.equals("psi"))){
            return true;
        }
        else{
            return false;
        }
    }

    private void startSession(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
