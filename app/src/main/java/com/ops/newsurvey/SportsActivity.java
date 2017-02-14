package com.ops.newsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);


        final ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> opt = new ArrayList<String>();
        opt.add("Yes");
        opt.add("No");
        opt.add("Can't Say");
        questions.add(new Question("Athletic Question 1",opt));
        questions.add(new Question("Athletic Question 2",opt));
        questions.add(new Question("Athletic Question 3",opt));
        questions.add(new Question("Athletic Question 4",opt));
        questions.add(new Question("Athletic Question 5",opt));
        questions.add(new Question("Athletic Question 6",opt));
        questions.add(new Question("Athletic Question 7",opt));
        questions.add(new Question("Athletic Question 8",opt));
        questions.add(new Question("Athletic Question 9",opt));
        questions.add(new Question("Athletic Question 10",opt));

        QuestionAdapter Adapter=new QuestionAdapter(this,questions);
        ListView listView=(ListView) findViewById(R.id.spoList);
        listView.setAdapter(Adapter);
    }
}
