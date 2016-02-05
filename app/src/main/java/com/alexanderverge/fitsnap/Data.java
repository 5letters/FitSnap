package com.alexanderverge.fitsnap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by alexanderverge on 15-02-21.
 */
public class Data
{

    private static Data instance = new Data();
    private ArrayList<Exercise> aExercises = new ArrayList<>();
    private ArrayList<Workout> aWorkouts = new ArrayList<>();
    private ArrayList<PerformedWorkout> aPerformedWorkouts = new ArrayList<>();
    private ArrayList<PerformedExercise> aPerformedExercises = new ArrayList<>();

    private Data(){}

    public static Data getInstance()
    {
        return instance;
    }

    public void setExercises(ArrayList<Exercise> pExercises)
    {
        aExercises = pExercises;
    }

    public void setWorkouts(ArrayList<Workout> pWorkouts)
    {
        aWorkouts = pWorkouts;
    }

    public void setPerformedWorkouts(ArrayList<PerformedWorkout> pPerformedWorkouts) {aPerformedWorkouts = pPerformedWorkouts; }

    public void setPerformedExercises(ArrayList<PerformedExercise> pPerformedExercises) {aPerformedExercises = pPerformedExercises; }

    // Find an exercise by Id
    public Exercise getExerciseById(int id)
    {
        for (Exercise e : aExercises)
        {
            if (e.getEid() == id)
            {
                return e;
            }
        }
        return null;
    }

    public Workout getWorkoutById(int wid)
    {
        for (Workout w : aWorkouts)
        {
            if (w.getWid() == wid)
            {
                return w;
            }
        }
        return null;
    }

    public ArrayList<Exercise> getExercises()
    {
        return aExercises;
    }

    public ArrayList<Workout> getWorkouts()
    {
        return aWorkouts;
    }

    public ArrayList<PerformedWorkout> getPerformedWorkouts() { return aPerformedWorkouts; }

    public ArrayList<PerformedExercise> getPerformedExercises() { return aPerformedExercises; }
}
