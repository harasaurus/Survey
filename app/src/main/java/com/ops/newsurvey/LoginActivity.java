package com.ops.newsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.ops.newsurvey.R.id.loginButton;

public class LoginActivity extends AppCompatActivity {
    User mUser;
    PrefManager mPrefManager;
    EditText mUserView;
    EditText mPasswordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initializing User object
        mUser = new User(this);

        mUserView =(EditText) findViewById(R.id.loginusername);
        mPasswordView =(EditText) findViewById(R.id.loginpassword);
        final Button login_button = (Button) findViewById(loginButton);

        //For Session Management
        mPrefManager =new PrefManager(this);
        String usr = mPrefManager.getUser();
        String psw = mPrefManager.getPassword();

        if(!(usr.equals("0")||psw.equals("0"))){
            mUserView.setText(usr);
            mPasswordView.setText(psw);
            login(login_button);
        }

        //TO remove errors from the views
       mUserView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    mPasswordView.requestFocus();
                }
                return false;
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                int id = v.getId();
                removeWarning(id);
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_DONE)
                {
                    login(login_button);
                }
                return false;

            }
        });


        //setting intent to go to signup if user don't have an account
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
                startSession(user,pass);
            }else{
                showWarning(R.id.loginusername,R.string.loginError);
                showWarning(R.id.loginpassword,R.string.loginError);
            }
        }

    public boolean authenticate(String user, String pass){
        if(mUser.exist(user)){
            if(mUser.passwordMatches(pass))
                return true;
            else
                return false;
        }
        else{
            return false;
        }
    }

    private void startSession(String user,String pass){
        mUser.initialize();
        mPrefManager.startSession(user, pass);
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

    private void removeWarning(int id)
    {
        TextView view = (TextView) findViewById(id);
        view.setError(null);
    }

}
