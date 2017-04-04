package com.ops.newsurvey;

/**
 * Created by Sagnik on 23-02-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "opinio-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public static final String USER = "user";

    public static final String PASS = "pass";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void initialize(){
        editor.putString(USER,"0");
        editor.putString(PASS,"0");
        editor.commit();
    }

    public void startSession(String user, String pass){
        editor.putString(USER,user);
        editor.putString(PASS,pass);
        editor.commit();
    }

    public String getUser(){
        String string = pref.getString(USER,"0");
        if(string.equals("0")){
            initialize();
            }
        return string;
    }

    public String getPassword(){
        String string = pref.getString(PASS,"0");
        if (string.equals("0")){
            initialize();
        }
        return string;
    }

    public void endSession(){
        initialize();
    }
}