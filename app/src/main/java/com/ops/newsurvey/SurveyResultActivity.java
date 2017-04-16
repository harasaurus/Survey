package com.ops.newsurvey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SurveyResultActivity extends AppCompatActivity {
    private int mQid;
    private DatabaseManager DBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        Intent intent = getIntent();
        mQid = intent.getIntExtra("Qid",0);

        ArrayList<String> options = new ArrayList<String>();
        ArrayList<Integer> results = new ArrayList<Integer>();
        String question;

        DBManager = new DatabaseManager(this);
        options = DBManager.getOpts(mQid);
        results = DBManager.getResults(mQid);
        question = DBManager.getQuestionText(mQid);

        TextView questionDisplay = (TextView) findViewById(R.id.survey_quest_display);
        LinearLayout SurveyLayout = (LinearLayout) findViewById(R.id.survey_result_display);
        questionDisplay.setText(question);

        int length = options.size();
        for(int i =0;i<length;i++){
            TextView view = new TextView(this);
            view.setText(options.get(i) + " = " + results.get(i));
            SurveyLayout.addView(view);
        }
    }
}
