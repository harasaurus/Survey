package com.ops.newsurvey;

import android.content.Context;

import static android.R.attr.id;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by harasar on 30/1/17.
 */

public class User {
    private static int UID;
    private String mName, mUserName, mGender;
    private int mPicId,mNubQues;
    private DatabaseManager DBManager;

    User(Context context){
        DBManager = new DatabaseManager(context) ;
        clear();
    }



    public void register(String username, String name, String email, String gender, String password){
        int picId = getLoginPic(gender);
        UID = DBManager.addUser(-1,username,name,email,gender,password,picId);
    }

    private int getLoginPic(String gender){
        switch (gender)
        {
            case "M":return R.drawable.ftd;
            case "F":return R.drawable.ftd;
            default:return R.drawable.ftd;
        }
    }

    public boolean exist(String username){
        UID = DBManager.getUid(username);
        if( UID==-1)
            return false;
        else
            return true;
    }

    public boolean passwordMatches(String password){
        String Password = DBManager.getPassword(UID);
        if(Password.equals(password))
            return true;
        else
            return false;
    }

    public void clearUID(){
        UID=-1;
    }
    private void clear(){
        mName=null;
        mUserName=null;
        UID=-1;
        mGender=null;
        mPicId=R.drawable.ftd;
        mNubQues=0;
        }

    public boolean emailExist(String email){
        if(DBManager.getUidForEmail(email)==-1)
            return false;
        else
            return true;
    }

    public void initialize(){
        mName = DBManager.getName(UID);
    }

    public int getId(){return UID;}
}