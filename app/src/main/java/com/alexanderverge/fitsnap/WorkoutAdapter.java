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
public class WorkoutAdapter extends BaseAdapter {
    private ArrayList<Workout> workouts;
    private LayoutInflater workoutInf;

    public WorkoutAdapter(Context c, ArrayList<Workout> w){
        workouts = w;
        workoutInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return workouts.size();
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
        LinearLayout workoutLayout = (LinearLayout) workoutInf.inflate(R.layout.workout, parent, false);
        TextView nameView = (TextView)workoutLayout.findViewById(R.id.workout_name);

        Workout currentWorkout = workouts.get(position);

        nameView.setText(currentWorkout.getName());

        workoutLayout.setTag(position);
        return workoutLayout;
    }
}
