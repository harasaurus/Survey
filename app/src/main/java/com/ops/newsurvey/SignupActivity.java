package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText name = (EditText) findViewById(R.id.name);
        EditText user = (EditText) findViewById(R.id.username);
        EditText dob = (EditText) findViewById(R.id.DOB);
        EditText email = (EditText) findViewById(R.id.email);
        EditText pass = (EditText) findViewById(R.id.password);
        EditText repass = (EditText) findViewById(R.id.repassword);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
            }
        });

        user.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
            }
        });

        dob.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
            }
        });

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
            }
        });

        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
            }
        });

        repass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                removeWarning(v.getId());
                return true;
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
            String name = getItem(R.id.name);
            String email = getItem(R.id.email);
            String DOB = getItem(R.id.DOB);
            String username = getItem(R.id.username);
            String pass1 = getItem(R.id.password);
            String pass2 = getItem(R.id.repassword);

            if(authenticated(name, email, DOB, username, pass1, pass2)){
                register();
            }

        }
    }

    private boolean authenticated(String name, String email, String DOB, String username, String pass1, String pass2) {
        if (!(name.isEmpty() || DOB.isEmpty() || username.isEmpty() || pass1.isEmpty() || pass2.isEmpty())) {
            if (nameIsAcceptable(name) && usernameIsUnique(username) && emailIsUnique(email) && emailIsValid(email)
                    && isAdult(DOB) && passwordMatching(pass1, pass2) && passwordIsAcceptable(pass1)) {
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
        ////TODO check if username is unique
        if(true)
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
        ////TODO check if email is unique
        if(true)
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
    {showWarning(R.id.DOB, R.string.notAdult);
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
        checkEmpty(R.id.DOB);
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
    private void register(){
        ////TODO register user in DB
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    private String getItem(int id){
        EditText editText =(EditText) findViewById(id);
        String Item = editText.getText().toString();
        return Item;
    }

   /* private boolean haveSmallAlpha(String password){
        if(password.matches(".* + [a-z] + .*")){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean haveCaps(String password){
        if(password.matches(".* + [A-Z] + .*")){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean haveSpclChar(String password){
    if(password.matches(".* + [!@#$%^&*] + .*")){
        return true;
    }else{
        return false;
    }
    }

    private boolean haveNumber(String password){
        if(password.matches(".* + [0-9] + .*")){
            return true;
        }else{
            return false;
        }
    }*/
}

