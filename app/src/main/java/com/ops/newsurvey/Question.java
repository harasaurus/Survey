package com.ops.newsurvey;

import java.util.ArrayList;

/**
 * Created by harasar on 1/2/17.
 */

public class Question {
    private String mText;
    private String []mOpts = new String[3];
    private Integer[]mOpinions = new Integer[3];
    private Integer mQid,mAttempts;
    private String[] mTags =new String[4];


    public void Question(String text, ArrayList<String> Opts)
    {
        mText= text;
        mOpts[0] = Opts.get(0);
        mOpts[1] = Opts.get(1);
        mOpts[2] = Opts.get(2);
        createQuestion();
    }

    private void createQuestion(){
        ////TODO call to server to store question in database and return a Integer for QID
    }

    public String getText(){
        return mText;
    }

    public String[] getOpts(){
        return mOpts;
    }


}
