package com.ops.newsurvey;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class PoliticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ImageView banner = (ImageView) findViewById(R.id.banner);
        banner.setImageResource(R.drawable.politics_banner);
        //creating the arraylist
        DatabaseManager manager = new DatabaseManager(this);
        final ArrayList<Question> questions = manager.getAllQuestionsByCategory("POL");

        QuestionAdapter Adapter=new QuestionAdapter(this,questions);
        ListView listView=(ListView) findViewById(R.id.List);
        listView.setAdapter(Adapter);

        Toolbar bar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Politics");
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
