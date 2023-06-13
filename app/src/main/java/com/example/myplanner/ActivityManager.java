package com.example.myplanner;

public class ActivityManager {

    private static ActivityManager instance;
    private MyPlannerActivity activity;

    private ActivityManager(){
        this.activity = null;
    }

    public static ActivityManager getInstance(){
        if(instance == null){
            instance = new ActivityManager();
        }
        return instance;
    }

    public void setActivity(MyPlannerActivity activity){
        this.activity = activity;
    }

    public MyPlannerActivity getActivity(){
        return activity;
    }
}
