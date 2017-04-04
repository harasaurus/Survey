package com.ops.newsurvey;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class TravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ImageView banner = (ImageView) findViewById(R.id.banner);
        banner.setImageResource(R.drawable.politics_banner);

        final ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> opt = new ArrayList<String>();
        opt.add("Yes");
        opt.add("No");
        opt.add("Can't Say");
        questions.add(new Question("Travel Question 1",opt));
        questions.add(new Question("Travel Question 2",opt));
        questions.add(new Question("Travel Question 3",opt));
        questions.add(new Question("Travel Question 4",opt));
        questions.add(new Question("Travel Question 5",opt));
        questions.add(new Question("Travel Question 6",opt));
        questions.add(new Question("Travel Question 7",opt));
        questions.add(new Question("Travel Question 8",opt));
        questions.add(new Question("Travel Question 9",opt));
        questions.add(new Question("Travel Question 10",opt));

        QuestionAdapter Adapter=new QuestionAdapter(this,questions);
        ListView listView=(ListView) findViewById(R.id.List);
        listView.setAdapter(Adapter);

        Toolbar bar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Travel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
