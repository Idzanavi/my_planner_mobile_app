package com.example.myplanner;

public class ItemData {

    int week_no;
    int day_no;
    int slot_no;
    String title;
    String text;
    String color;

    public int getWeek_no() {
        return week_no;
    }

    public int getDay_no() {
        return day_no;
    }

    public int getSlot_no() {
        return slot_no;
    }

    public String getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDay_no(int day_no) {
        this.day_no = day_no;
    }

    public void setSlot_no(int slot_no) {
        this.slot_no = slot_no;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeek_no(int week_no) {
        this.week_no = week_no;
    }
}
