package com.ops.newsurvey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultActivity extends AppCompatActivity {
    private int mQid;
    private DatabaseManager DBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_result);

        Intent intent = getIntent();
        mQid = intent.getIntExtra("Qid",0);

        ArrayList<String> options = new ArrayList<>();
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

        //initializing PieChart
        PieChart chart = (PieChart)findViewById(R.id.chart);

        //some features of pieChart
        chart.setRotationEnabled(true);
        chart.setDrawEntryLabels(false);

        //Data array for the pieChart entry
        List<PieEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for(int i= 0;i<length;i++){
            entries.add(new PieEntry(results.get(i), options.get(i)));
            labels.add(options.get(i));
        }

        //setting up pieChart
        PieDataSet set = new PieDataSet(entries,"Opinion Poll");
        //colorScheme
        set.setColors(ColorTemplate.COLORFUL_COLORS);

        Description l = chart.getDescription();
        l.setEnabled(false);

        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();
    }
}
