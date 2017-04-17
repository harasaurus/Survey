package com.ops.newsurvey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText name = (EditText) findViewById(R.id.name);
        final EditText user = (EditText) findViewById(R.id.username);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText repass = (EditText) findViewById(R.id.repassword);
        final RadioGroup gender = (RadioGroup) findViewById(R.id.gender);

        //to remove warnings
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                if(actionId ==0||actionId== EditorInfo.IME_ACTION_NEXT)
                {
                    user.requestFocus();
                }
                return false;
            }
        });

        user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    email.requestFocus();
                }
                return false;
            }
        });

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    pass.requestFocus();
                }
                return false;
            }
        });

        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    repass.requestFocus();
                }
                return false;
            }
        });

        repass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                if(actionId ==0||actionId==EditorInfo.IME_ACTION_NEXT)
                {
                    gender.requestFocus();
                }
                return false;

            }
        });
    }

    private void removeWarning(int id){
        TextView view = (TextView) findViewById(id);
        view.setError(null);
    }

    public void signup(View view){
        //removeWarnings();
        CheckBox terms = (CheckBox) findViewById(R.id.terms);
        if(terms.isChecked()) {
            RadioGroup genderRadioButton = (RadioGroup) findViewById(R.id.gender);
            int checkedId = genderRadioButton.getCheckedRadioButtonId();
            String gender;
            switch(checkedId){
                case R.id.genderM:
                    gender="M";
                    break;
                case R.id.genderF:
                    gender="F";
                    break;
                default:
                    gender="O";
            }

            String name = getItem(R.id.name);
            String email = getItem(R.id.email);
            String username = getItem(R.id.username);
            String pass1 = getItem(R.id.password);
            String pass2 = getItem(R.id.repassword);
                mUser = new User(this);
            if(authenticated(name, email, username, pass1, pass2)){
                mUser.register(username,name,email,gender,pass1);
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    private boolean authenticated(String name, String email, String username, String pass1, String pass2) {
        if (!(name.isEmpty() || username.isEmpty() || pass1.isEmpty() || pass2.isEmpty())) {
            if (nameIsAcceptable(name) && usernameIsUnique(username) && emailIsUnique(email) && emailIsValid(email)
                    && passwordMatching(pass1, pass2) && passwordIsAcceptable(pass1)) {
                return true;
            } else {
                return false;
            }
        }else {
            emptyFieldHandler();
            return false;
        }
    }
    private boolean usernameIsUnique(String username){

        if(!(mUser.exist(username)))
        {return true;}
        else
        {showWarning(R.id.username, R.string.usernameNotUnique);
         return false;}
    }

    private boolean nameIsAcceptable(String name){
        ////TODO check if name follows standards i.e- only alphabaets and space is allowed
    if(true)
    {return true;}
    else
    {showWarning(R.id.name, R.string.nameNotAcceptable);
    return false;}
    }


    private boolean emailIsUnique(String email){
        if(!(mUser.emailExist(email)))
    {return true;}
    else
    {showWarning(R.id.email, R.string.emailNotUnique);
    return false;}
    }

    private boolean emailIsValid(String email){
    ////TODO check if email is valid
        if (true)
    {return true;}
    else
    {showWarning(R.id.email, R.string.emailNotValid);
    return false;}
    }

    private boolean isAdult(String DOB){
    ////TODO check if user is of minimum age to use the app
        if(true)
    {return true;}
    else
    {showWarning(R.id.gender, R.string.notAdult);
    return false;}
    }

    private boolean passwordMatching(String pI,String pII){
    if(pI.equals(pII))
    {return true;}
    else
    {showWarning(R.id.repassword,R.string.passwordNotMatching);
     showWarning(R.id.password,R.string.passwordNotMatching);
     return false;}
    }

    private boolean passwordIsAcceptable(String pass){
    ////TODO check if password follows the standards i.e.-1 smallcase char,1 caps,1 number, 1 special character
        if(true)
    {return true;}
    else
    {showWarning(R.id.repassword,R.string.passwordNotAcceptable);
    showWarning(R.id.password,R.string.passwordNotAcceptable);
    return false;
        }
    }

    private void showWarning(int id,int warning){
        TextView textView = (TextView) findViewById(id);
        String warn = getString(warning);
        textView.setError(warn);
    }

    private void emptyFieldHandler(){
        checkEmpty(R.id.name);
        checkEmpty(R.id.username);
        checkEmpty(R.id.email);
        checkEmpty(R.id.password);
        checkEmpty(R.id.repassword);
    }

    private void checkEmpty(int id){
        EditText editText = (EditText) findViewById(id);
        String field = editText.getText().toString();
        if(field.isEmpty())
        {
            showWarning(id,R.string.fieldIsEmpty);}
    }

    private String getItem(int id){
        EditText editText =(EditText) findViewById(id);
        String Item = editText.getText().toString();
        return Item;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
    }
}

