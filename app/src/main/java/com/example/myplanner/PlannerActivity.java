package com.example.myplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myplanner.databinding.ActivityMainBinding;
import com.example.myplanner.databinding.ActivityPlannerBinding;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlannerActivity extends MyPlannerActivity {

    private ActivityPlannerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(AppState.getInstance().getPlannerPos() == null){
            AppState.getInstance().setPlannerPos(new PlannerPos());
        }
        AppState.getInstance().getPlannerPos().setActivity(this);

        binding.buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.finishAffinity();
                System.exit(0);
            }
        });
        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getAuthController().logout();
            }
        });
        binding.buttonPrevWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setWeek(
                        AppState.getInstance().getPlannerPos().getWeek() - 1);
            }
        });
        binding.buttonNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setWeek(
                        AppState.getInstance().getPlannerPos().getWeek() + 1);
            }
        });
        binding.buttonFirstWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirstWeekController controller = new FirstWeekController();
                controller.get();
            }
        });
        binding.buttonThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().reset();
                AppState.getInstance().getPlannerPos().setActivity(PlannerActivity.this);
            }
        });
        binding.buttonLastWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LastWeekController controller = new LastWeekController();
                controller.get();
            }
        });
        binding.buttonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(0);
            }
        });
        binding.buttonTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(1);
            }
        });
        binding.buttonWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(2);
            }
        });
        binding.buttonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(3);
            }
        });
        binding.buttonFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(4);
            }
        });
        binding.buttonSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(5);
            }
        });
        binding.buttonSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().getPlannerPos().setDay(6);
            }
        });
        binding.buttonSlot0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(0);
            }
        });
        binding.buttonSlot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(1);
            }
        });
        binding.buttonSlot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(2);
            }
        });
        binding.buttonSlot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(3);
            }
        });
        binding.buttonSlot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(4);
            }
        });
        binding.buttonSlot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(5);
            }
        });
        binding.buttonSlot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(6);
            }
        });
        binding.buttonSlot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(7);
            }
        });
        binding.buttonSlot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(8);
            }
        });
        binding.buttonSlot9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(9);
            }
        });
        binding.buttonSlot10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(10);
            }
        });
        binding.buttonSlot11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(11);
            }
        });
        binding.buttonSlot12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(12);
            }
        });
        binding.buttonSlot13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(13);
            }
        });
        binding.buttonSlot14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(14);
            }
        });
        binding.buttonSlot15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlannerActivity.this.ShowDetails(15);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateItems();
    }

    @Override
    protected void checkAuthStatus(){
        if(!AppState.getInstance().getAuthController().isConnected()){
            this.finish();
        }
    }


    private String MakeWeekLabel(int week){
        LocalDate base_date = LocalDate.parse("1999-12-27");
        LocalDate start_date = base_date.plusDays(week * 7);
        LocalDate end_date = start_date.plusDays(6);
        return start_date.toString() + " - " + end_date.toString();
    }

    private void UpdateDayButtons(int day){
        binding.buttonMon.setEnabled(day != 0);
        binding.buttonTue.setEnabled(day != 1);
        binding.buttonWed.setEnabled(day != 2);
        binding.buttonThu.setEnabled(day != 3);
        binding.buttonFri.setEnabled(day != 4);
        binding.buttonSat.setEnabled(day != 5);
        binding.buttonSun.setEnabled(day != 6);
    }

    public void UpdateDay(int day){
        UpdateDayButtons(day);
        UpdateItems();
    }

    private static String getItemTitle(int day, int slot){
        ItemData item = AppState.getInstance().getItem(day, slot);
        if(item != null){
            return item.title;
        }else{
            return "-";
        }
    }

    public void UpdateItems(){
        PlannerPos plannerPos = AppState.getInstance().getPlannerPos();
        int day = plannerPos.getDay();

        binding.buttonSlot0.setText(getItemTitle(day, 0));
        binding.buttonSlot1.setText(getItemTitle(day, 1));
        binding.buttonSlot2.setText(getItemTitle(day, 2));
        binding.buttonSlot3.setText(getItemTitle(day, 3));
        binding.buttonSlot4.setText(getItemTitle(day, 4));
        binding.buttonSlot5.setText(getItemTitle(day, 5));
        binding.buttonSlot6.setText(getItemTitle(day, 6));
        binding.buttonSlot7.setText(getItemTitle(day, 7));
        binding.buttonSlot8.setText(getItemTitle(day, 8));
        binding.buttonSlot9.setText(getItemTitle(day, 9));
        binding.buttonSlot10.setText(getItemTitle(day, 10));
        binding.buttonSlot11.setText(getItemTitle(day, 11));
        binding.buttonSlot12.setText(getItemTitle(day, 12));
        binding.buttonSlot13.setText(getItemTitle(day, 13));
        binding.buttonSlot14.setText(getItemTitle(day, 14));
        binding.buttonSlot15.setText(getItemTitle(day, 15));
    }

    public void UpdateWeek(int week){
        binding.labelWeek.setText(MakeWeekLabel(week));
        WeekItemsController controller = new WeekItemsController(this);
        controller.get();
    }

    public void ShowDetails(int slot){
        Intent intent = new Intent(PlannerActivity.this, ItemActivity.class);
        intent.putExtra("slot", slot);
        PlannerActivity.this.startActivity(intent);
    }

}