package com.alexanderverge.fitsnap;

import android.text.format.Time;

import java.util.Date;


/**
 * Created by Wanjiakun on 2/21/2015.
 */
public class PerformedWorkout {
    private int pwid; //unique performedworkout id
    private Workout refWork; //reference workout
    private Long startTime;

    public PerformedWorkout(int pwid, Workout wo, Long time){
        this.setRefWork(wo);
        this.setStartTime(time);
        this.setPwid(pwid);
    }

    public String getName() { return ""+pwid; }

    public int getPwid() {
        return pwid;
    }

    public void setPwid(int pwid) {
        this.pwid = pwid;
    }

    public Workout getRefWork() {
        return refWork;
    }

    public void setRefWork(Workout refWork) {
        this.refWork = refWork;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
