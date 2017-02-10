package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.id;
import static android.R.attr.switchMinWidth;
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
            startSession(id);
        }else
            showWarning(R.id.loginWarning,R.string.loginError);
    }

    private boolean authenticate(String user, String pass){
       if(userExist(user)){
           int id =getId(user);
           if(checkPassword(id,pass)){
               return true;
           }else return false;
        }else
           return false;
    }

    private boolean userExist(String user){
        ////TODO check whether user exist in database or not
        if(user.equals("HP") || user.equals("RR") || user.equals("SS"))
            return true;
        else
            return false;
    }
    private int getId(String user){
        ////TODO this function will return Unique ID for the current user
        if(user.equals("HP"))
            return 1;
        if(user.equals("RR"))
            return 2;
        return 3;
    }
    private boolean checkPassword(int id, String pass){
     String Password = getPassword(id);
        if(Password.equals(pass))
            return true;
        else
            return false;

    }

    private String getPassword(int counter){
        switch(counter){
            case 1: return "PHI";
            case 2: return "RO";
            default: return "SIGMA";
        }
    }

    private void startSession(int ID){
        ////TODO- make an object of user
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    private void showWarning(int id, int warning){
        TextView warningView = (TextView) findViewById(id);
        warningView.setVisibility(View.VISIBLE);
        String warn = getString(warning);
        warningView.setText(warn);
    }

    public void goToSign(View view){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }


}
