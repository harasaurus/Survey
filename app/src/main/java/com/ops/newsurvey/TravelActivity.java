package com.ops.newsurvey;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

        DatabaseManager manager = new DatabaseManager(this);
        final ArrayList<Question> questions = manager.getAllQuestionsByCategory("TRA");


        QuestionAdapter Adapter=new QuestionAdapter(this,questions);
        ListView listView=(ListView) findViewById(R.id.List);
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question currentQuestion = questions.get(position);
                int ID = currentQuestion.getId();
                Intent intent = new Intent(TravelActivity.this,SurveyActivity.class);
                intent.putExtra("Qid",ID);
                startActivity(intent);
            }
        });

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
