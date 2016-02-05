package com.alexanderverge.fitsnap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class ExerciseAdapter extends BaseAdapter {
    private ArrayList<Exercise> exercises;
    private LayoutInflater exerciseInflator;

    public ExerciseAdapter(Context c, ArrayList<Exercise> w){
        exercises = w;
        exerciseInflator = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout exerciseLayout = (LinearLayout) exerciseInflator.inflate(R.layout.exercise, parent, false);
        TextView nameView = (TextView)exerciseLayout.findViewById(R.id.exercise_name);

        Exercise currentExercise = exercises.get(position);

        if(currentExercise==null){
            nameView.setText("Add Exercise");
        }else{
            nameView.setText(currentExercise.getName());
        }

        exerciseLayout.setTag(position);
        return exerciseLayout;
    }
}