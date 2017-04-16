package com.ops.newsurvey;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_CATEGORY;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_EMAIL;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_GENDER;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_NAME;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_OPTIONS;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_PASSWORD;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_PIC_ID;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_QID;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_QTEXT;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_QUESTIONS;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_RESPONSES;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_RESULTS;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_UID;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.KEY_USERNAME;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.TABLE_ACTIVITY;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.TABLE_OPT;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.TABLE_QS;
import static com.ops.newsurvey.SurveyContract.SurveyEntry.TABLE_USER;

/**
 * Created by harasar on 4/4/17.
 */

class DatabaseManager extends SQLiteOpenHelper {
    // All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NewSurveyDatabase.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CreateTable1 = "CREATE TABLE " + TABLE_USER + " (" + KEY_UID + " INTEGER PRIMARY KEY, " + KEY_USERNAME + " TEXT,"
                + KEY_NAME + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_GENDER + " TEXT, " + KEY_PASSWORD + " TEXT, " + KEY_PIC_ID
                + " INTEGER, " + KEY_QUESTIONS + " INTEGER)";
        db.execSQL(CreateTable1);

        String CreateTable2 = "CREATE TABLE " + TABLE_QS + " (" + KEY_QID + " INTEGER PRIMARY KEY, " + KEY_CATEGORY + " TEXT, "
                + KEY_QTEXT + " TEXT, " + KEY_RESPONSES + " NUMBER)";
        db.execSQL(CreateTable2);

        String CreateTable3 = "CREATE TABLE " + TABLE_ACTIVITY + " (" + KEY_UID + " INTEGER, " +KEY_QID+ " INTEGER)";
        db.execSQL(CreateTable3);

        String CreateTable4 = "CREATE TABLE " + TABLE_OPT + " (" + KEY_QID + " INTEGER, " + KEY_OPTIONS + " TEXT, "
                + KEY_RESULTS + " INTEGER)";
        db.execSQL(CreateTable4);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPT);
        // Create tables again
        onCreate(db);
    }

    public int addUser( int Uid, String username, String name, String email, String gender, String password, int pic_id) {
        if(Uid==-1)
        {Uid=setUid();}

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UID,Uid);
        values.put(KEY_USERNAME,username);
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_GENDER,gender);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_PIC_ID,pic_id);
        values.put(KEY_QUESTIONS,0);
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        return Uid;
    }


    public String getName(Integer id){
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,new String[]{String.valueOf(KEY_NAME)},String.valueOf(KEY_UID)+"=?"
                ,new String[]{id.toString()},null,null,null);
        if(cursor.moveToFirst())
            return cursor.getString(0);
        else
            return null;}

    public String getPassword(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_PASSWORD }, "Uid =?",
                new String[] { id.toString() }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getString(0);
        else
            return null;}

    public String getUsername(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { "username" }, "Uid =?",
                new String[] {id.toString() }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getString(0);
        else
            return null;}

    public String getEmail(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { "email" }, "Uid =?",
                new String[] {id.toString() }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getString(0);
        else
            return null;
    }

     public int getPicId(Integer id){SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_USER, new String[] { "pic_id" }, "Uid =?",
                 new String[] {id.toString() }, null, null, null, null);
         if(cursor.moveToFirst())
            return cursor.getInt(0);
         else
            return -1;}

    public String getGender(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { "gender" }, "Uid =?",
                new String[] {id.toString() }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getString(0);
        else
            return null;
    }

    public int getQuestions(Integer id){SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { "questions" }, "Uid =?",
                new String[] {id.toString() }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return -1;}

    public int getUid(String Username){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_USER, new String[] { "Uid" }, "username =?",
                 new String[] {String.valueOf(Username)}, null, null, null, null);
         if(cursor.moveToFirst())
             return cursor.getInt(0);
         else
            return -1;
         }

    public int getUidForEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { "Uid" }, "email =?",
                new String[] {String.valueOf(email)}, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return -1;
    }

     public void addQuestion(int Qid,String cat,String Text, ArrayList<String> opts, ArrayList<Integer> results, int responses){
         if(Qid==-1)
         Qid = setQid();

         SQLiteDatabase db = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(KEY_QID , Qid);
         values.put(KEY_CATEGORY , cat);
         values.put(KEY_QTEXT , Text);
         values.put(KEY_RESPONSES , responses);
         db.insert(TABLE_QS , null , values);
         db.close();

         SQLiteDatabase db1 = getWritableDatabase();
         int length = opts.size();
         for(int i=0; i<length; i++){
             values.clear();
            values.put(KEY_QID , Qid);
            values.put(KEY_OPTIONS , opts.get(i));
            values.put(KEY_RESULTS , results.get(i));
             db1.insert(TABLE_OPT , null , values);
         }
        db1.close();
     }

     public ArrayList<Question> getAllQuestionsByCategory(String cat){
         ArrayList<Question> questions = new ArrayList<Question>();
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cur = db.query(TABLE_QS,null,"category = ?",new String[]{cat}
                 ,null,null,null);
         if(cur.moveToFirst()) {
             do {Question q = new Question();
                 Integer id = cur.getInt(0);
                 q.setQid(id);
                 q.setCat(cur.getString(1));
                 q.setQText(cur.getString(2));
                 q.setResponses(cur.getInt(3));
                 ArrayList<String> opt = new ArrayList<String>();
                 ArrayList<Integer> result = new ArrayList<Integer>();

                 SQLiteDatabase db1 = this.getReadableDatabase();
                 Cursor cursor = db1.query(TABLE_OPT,new String[]{"options","results"},"Qid = ?",new String[]{id.toString()}
                         ,null,null,null);
                 if(cursor.moveToFirst()){
                     do{
                        opt.add(cursor.getString(0));
                        result.add(cursor.getInt(1));
                     }while(cursor.moveToNext());
                 }
                 q.setOpts(opt);
                 q.setResults(result);
                 questions.add(q);
             }while (cur.moveToNext());
         }
         return questions;
     }

     public void updateResponse(int Uid, int Qid){
         SQLiteDatabase db = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("Uid",Uid);
         values.put("Qid",Qid);
         db.insert("activity_log",null,values);
         db.close();
     }

     public boolean checkAttempt(int Uid,int Qid){
         SQLiteDatabase db = getReadableDatabase();
         String query = "SELECT QID FROM " + TABLE_ACTIVITY + " WHERE " + KEY_UID + " = " + Uid + " AND " + KEY_QID + " = " + Qid;
         Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
            return true;
         else
             return false;
     }

     public int getQid(String Text){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_QS, new String[] { "Qid" }, "qtext =?",
                 new String[] { String.valueOf(Text)}, null, null, null, null);
         if(cursor != null)
             cursor.moveToFirst();

         return cursor.getInt(0);
     }

     public String getQuestionText(Integer id){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_QS, new String[] { "qText" }, "Qid =?",
                 new String[] {id.toString() }, null, null, null, null);
         if(cursor.moveToFirst())
             return cursor.getString(0);
         else
             return null;
     }

    public int getResultsforOpt(Integer Qid, String opt){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_OPT, new String[] { KEY_RESULTS }, "Qid =? AND options =?",
                new String[] {Qid.toString(),opt }, null, null, null, null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return -1;
    }

     public int getResponse(Integer id){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_QS, new String[] { "responses" }, "Qid =?",
                 new String[] {id.toString() }, null, null, null, null);
         if(cursor != null)
             cursor.moveToFirst();

         return cursor.getInt(0);}

     public ArrayList<String> getOpts(Integer id){
         ArrayList<String> opts = new ArrayList<String>();
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_OPT, new String[] { "options" }, "Qid =?",
                 new String[] {id.toString() }, null, null, null, null);
         if(cursor.moveToFirst()) {
             do {opts.add(cursor.getString(0));
             }while (cursor.moveToNext());
         }
         return opts;
     }

     public ArrayList<Integer> getResults(Integer id){
         ArrayList<Integer> opts = new ArrayList<Integer>();
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.query(TABLE_OPT, new String[] { "results" }, "Qid =?",
                 new String[] {id.toString() }, null, null, null, null);
         if(cursor.moveToFirst()) {
             do {opts.add(cursor.getInt(0));
             }while (cursor.moveToNext());
         }
         return opts;
     }

     public void updateQuestions(Integer Uid){
         SQLiteDatabase db = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(KEY_QUESTIONS,getQuestions(Uid)+1);
         db.update(TABLE_USER,values,"Uid=?",new String[]{Uid.toString()});
         db.close();
     }

     public void updateResponses(Integer Qid){
         SQLiteDatabase db = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(KEY_RESPONSES,getResponse(Qid)+1);
         db.update(TABLE_QS,values,"Qid=?",new String[]{Qid.toString()});
         db.close();
     }

     public void updateResults(Integer Qid,String opt){
         SQLiteDatabase db = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(KEY_RESULTS,getResultsforOpt(Qid,opt)+1);
         db.update(TABLE_OPT,values,"Qid=? AND options=?",new String[]{Qid.toString(),opt});
         db.close();
     }

    public void update(String tableName ,String whereClause, String attribute, String Value){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(String.valueOf(attribute),String.valueOf(Value));
        db.update(String.valueOf(tableName),values,String.valueOf(whereClause),null);
        db.close();
    }

     private int setUid(){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT Uid FROM user_info",null);
         if(cursor == null)
             return 0;
         else
             cursor.moveToLast();
             return (cursor.getInt(0)+1);
     }

     private int setQid(){
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT Qid FROM question_info",null);
         if(cursor == null)
             return 0;
         else
             cursor.moveToLast();
         return (cursor.getInt(0)+1);
     }
}

