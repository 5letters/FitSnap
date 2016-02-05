package com.alexanderverge.fitsnap;

import java.util.ArrayList;

/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class Workout {
    private ArrayList<Exercise> exercises;
    private int wid;
    private String name;

    public Workout(int wid, String name, ArrayList<Exercise> ex){
        this.setWid(wid);
        this.setName(name);
        this.setExercises(ex);
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
