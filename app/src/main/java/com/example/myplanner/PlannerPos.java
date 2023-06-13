package com.example.myplanner;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlannerPos {
    private int week;
    private  int day;

    PlannerActivity activity;

    public PlannerPos(){
        this.reset();
    }

    public void reset(){
        LocalDate cur_date = LocalDate.now();
        LocalDate base_date = LocalDate.parse("1999-12-27");
        long days = DAYS.between(base_date, cur_date);

        int week = (int)(days / 7);
        int day = (int)(days % 7);

        setWeek(week);
        setDay(day);
    }

    public int getDay() {
        return day;
    }

    public int getWeek() {
        return week;
    }

    public void setDay(int day) {
        this.day = day;
        if(activity!=null){
            activity.UpdateDay(day);
        }
    }

    public void setWeek(int week) {
        this.week = week;
        if(activity!=null) {
            activity.UpdateWeek(week);
        }
    }

    public void setActivity(PlannerActivity activity){
        this.activity = activity;
        activity.UpdateWeek(week);
        activity.UpdateDay(day);
    }
}
