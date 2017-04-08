package com.ops.newsurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {

//private static int[] xdata={40,30,20,10} ;
    //private static String[] ydata={"A","B","C","D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        PieChart chart = (PieChart)findViewById(R.id.chart);

        chart.setRotationEnabled(true);
        chart.setDrawEntryLabels(true);

        addData(chart);

    }

    private void addData(PieChart chart) {
       /* ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<String>label=new ArrayList<>();

        for(int i=0;i<xdata.length;i++){
            entries.add(new PieEntry(xdata[i], i));
        }

        for (int i=1;i<ydata.length;i++){
            label.add(ydata[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(xdata,);*/
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();

    }

}
