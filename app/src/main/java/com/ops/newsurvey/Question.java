package com.ops.newsurvey;

import android.content.Context;
import android.provider.ContactsContract;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by harasar on 1/2/17.
 */

public class Question {
    private String mText,mCat;
    private Integer mQid,mresponses;
    private ArrayList<String> mOpts = new ArrayList<String>();
    private ArrayList<Integer> mResults = new ArrayList<Integer>();

    public String getText(){
        return mText;
    }
    public ArrayList<String> getOpts(){
        return mOpts;
    }

    public void setQid(int id){mQid=id;}
    public void setQText(String text){mText=text;}
    public void setCat(String cat){mCat=cat;}
    public void setResponses(int res){mresponses=res;}
    public void setOpts(ArrayList<String> opts){mOpts=opts;}
    public void setResults(ArrayList<Integer> results){mResults=results;}


}
