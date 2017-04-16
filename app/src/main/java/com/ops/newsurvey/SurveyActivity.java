package com.ops.newsurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {
    int mQid;
    DatabaseManager DBManager;
    RadioGroup choiceRadioGroup;
    ArrayList <String> opts;
    ArrayList <RadioButton> choices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check if user already answered the question
        Intent intent = getIntent();
        mQid = intent.getIntExtra("Qid",0);
        DBManager = new DatabaseManager(this);
        if(DBManager.checkAttempt(User.UID,mQid))
            //goto results activity
        {Intent ntent = new Intent(this,SurveyResultActivity.class);
        ntent.putExtra("Qid",mQid);
        startActivity(ntent);}

        // if not attempted show the activity
        setContentView(R.layout.activity_survey);
        //setting up layout
        TextView questionTextView = (TextView) findViewById(R.id.question);
        questionTextView.setText(DBManager.getQuestionText(mQid));

        choiceRadioGroup = (RadioGroup) findViewById(R.id.choices);
        opts = DBManager.getOpts(mQid);
        int length = opts.size();
        choices = new ArrayList<RadioButton>();

        for(int i=0;i<length;i++){
            RadioButton button = new RadioButton(this);
            button.setText(opts.get(i));
                choiceRadioGroup.addView(button);
            choices.add(button);
        }

        Button sub = (Button) findViewById(R.id.submitResponse);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Rid = choiceRadioGroup.getCheckedRadioButtonId();
                if(Rid!=-1){
                int index = choices.indexOf((RadioButton)findViewById(Rid));
                DBManager.updateQuestions(User.UID);
                DBManager.updateResponses(mQid);
                DBManager.updateResponse(User.UID,mQid);
                DBManager.updateResults(mQid,opts.get(index));
                Intent ntent = new Intent(SurveyActivity.this,SurveyResultActivity.class);
                ntent.putExtra("Qid",mQid);
                startActivity(ntent);}
            }
        });
    }
}
