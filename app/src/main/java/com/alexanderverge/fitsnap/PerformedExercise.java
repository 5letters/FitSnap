package com.alexanderverge.fitsnap;

import android.text.format.Time;

/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class PerformedExercise{
    private int peid; //unique performed exercise id
    private int pwid; //workout id reference
    private Exercise refEx; //exercise reference
    private int reps;//reps
    private int sets;//sets
    private double count;//count
    Long duration;//duration

    public PerformedExercise(int peid, int pwid, Exercise ex, int reps, int sets, double count, Long duration){
        this.setWid(pwid);
        this.setRefEx(ex);
        this.setReps(reps);
        this.setSets(sets);
        this.setCount(count);
        this.setDuration(duration);
        this.setPeid(peid);
    }

    public String getName() { return refEx.getName(); }

    public int getPeid() {
        return peid;
    }

    public void setPeid(int peid) {
        this.peid = peid;
    }

    public int getWid() {
        return pwid;
    }

    public void setWid(int wid) {
        this.pwid = wid;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Exercise getRefEx() {
        return refEx;
    }

    public void setRefEx(Exercise refEx) {
        this.refEx = refEx;
    }
}
