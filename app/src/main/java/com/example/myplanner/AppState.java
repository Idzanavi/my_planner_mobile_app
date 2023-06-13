package com.example.myplanner;

import java.util.List;

public class AppState {
    private static  AppState instance;

    private AuthController authController;
    private PlannerPos plannerPos;

    ItemData[][] items;

    private AppState(){

        authController = new AuthController();
        plannerPos = null;
        items = null;
        createItems();
    }

    public AuthController getAuthController(){
        return authController;
    }

    public static AppState getInstance(){
        if(instance == null){
            instance = new AppState();
        }

        return instance;
    }

    public PlannerPos getPlannerPos(){
        return plannerPos;
    }

    public void setPlannerPos(PlannerPos plannerPos) {
        this.plannerPos = plannerPos;
    }

    public ItemData getItem(int day, int slot) {
        return this.items[day][slot];
    }

    public void setItem(int day, int slot, ItemData data) {
        this.items[day][slot] = data;
    }

    public void setItems(List<ItemData> items) {
        clearItems();
        if(items != null){
            for(ItemData item: items){
                this.items[item.day_no][item.slot_no] = item;
            }
        }
    }

    private void createItems(){
        items = new ItemData[7][];
        for(int i = 0; i < 7; ++i){
            items[i] = new ItemData[16];
            clearItems(i);
        }
    }

    private void clearItems(){
        for(int i = 0; i < 7; ++i){
            clearItems(i);
        }
    }

    private void clearItems(int day){
        for(int i = 0; i < 16; ++i){
            items[day][i] = null;
        }
    }
}
