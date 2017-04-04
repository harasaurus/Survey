package com.ops.newsurvey;

import android.app.Activity;
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

    PrefManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText user =(EditText) findViewById(R.id.loginusername);
        EditText password =(EditText) findViewById(R.id.loginpassword);

        mPrefManager =new PrefManager(this);
        String usr = mPrefManager.getUser();
        String psw = mPrefManager.getPassword();

        if(!(usr.equals("0")||psw.equals("0"))){
            user.setText(usr);
            password.setText(psw);
            login(findViewById(R.id.loginButton));
        }

        //TO remove errors from the views
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

        TextView noAccount = (TextView) findViewById(R.id.no_account);
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(intent);
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
            startSession(id,user,pass);
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
        {return true;}
        else
        {return false;}
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

    private void startSession(int id,String user,String pass){ // id variable here not used..
        ////TODO- make an object of user
        mPrefManager.startSession(user, pass);
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void showWarning(int id, int warning){
        TextView warningView = (TextView) findViewById(id);
        warningView.setVisibility(View.VISIBLE);
        String warn = getString(warning);
        warningView.setError(warn);
    }

    private void removeWarning(int id)
    {
        TextView view = (TextView) findViewById(id);
        view.setError(null);
    }

}
