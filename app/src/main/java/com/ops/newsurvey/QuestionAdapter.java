package com.ops.newsurvey;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by harasar on 14/2/17.
 */

public class QuestionAdapter extends ArrayAdapter {
    QuestionAdapter(Activity context, ArrayList<Question> ques)
    {super(context,0,ques);}

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;

        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cat,parent,false);
        }

        Question currentQuestion = (Question) getItem(position);
        TextView questionText = (TextView) listItemView.findViewById(R.id.ques_text);
        questionText.setText(currentQuestion.getText());

        return listItemView;
    }
}
