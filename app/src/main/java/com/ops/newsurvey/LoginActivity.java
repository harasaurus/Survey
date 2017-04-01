package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
        EditText user =(EditText) findViewById(R.id.loginusername);
        EditText password =(EditText) findViewById(R.id.loginpassword);

       user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                return true;
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                return true;
            }
        });

    }

    public void login(View view){
        EditText usernameView = (EditText) findViewById(R.id.loginusername);
        String user= usernameView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.loginpassword);
        String pass = passwordView.getText().toString();
        if(authenticate(user,pass))
        {
            startSession(id);
        }
          }

    private boolean authenticate(String user, String pass){
       if(userExist(user)){
           int id =getId(user);
           if(checkPassword(id,pass)){
               return true;
           }else return false;
        }else
            showWarning(R.id.loginusername,R.string.loginError);
            showWarning(R.id.loginpassword,R.string.loginError);

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
            case 3: return "SAB";
            default: return "NULL";
        }
    }

    private void startSession(int id){ // id variable here not used..
        ////TODO- make an object of user
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showWarning(int id, int warning){
        TextView warningView = (TextView) findViewById(id);
        warningView.setVisibility(View.VISIBLE);
        String warn = getString(warning);
        warningView.setError(warn);
    }

    private void goToSign(View view){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    private void removeWarning(int id)
    {
        TextView view = (TextView) findViewById(id);
        view.setError(null);
    }

}
