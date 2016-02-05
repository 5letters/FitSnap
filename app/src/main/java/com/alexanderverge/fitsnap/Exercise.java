package com.alexanderverge.fitsnap;

import android.text.format.Time;

/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class Exercise {
    private String name; //not null
    private int eid;
    private String type; //not null
    private boolean timed; //true = time, false = reps  //not null
    private boolean units; //true = metric, false = imperial
    private double weight;
    private double count; //tracks number of reps or time in seconds
    private long duration;


    public Exercise(int eid, String name, String type, boolean timed, boolean units, double weight, double count, long duration){
        this.setName(name);
        this.setType(type);
        this.setTimed(timed);
        this.setUnits(units);
        this.setWeight(weight);
        this.setCount(count);
        this.setDuration(duration);
        this.setEid(eid);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTimed() {
        return timed;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public boolean isUnits() {
        return units;
    }

    public void setUnits(boolean units) {
        this.units = units;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
