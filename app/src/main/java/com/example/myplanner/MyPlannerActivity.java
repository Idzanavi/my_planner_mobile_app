package com.example.myplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class MyPlannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().setActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityManager.getInstance().setActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAuthStatus();
        ActivityManager.getInstance().setActivity(this);
    }

    public void updateAuthStatus(boolean status){
        checkAuthStatus();
    }

    protected void checkAuthStatus(){
    }

}
