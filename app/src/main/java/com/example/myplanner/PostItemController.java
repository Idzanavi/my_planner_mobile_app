package com.example.myplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostItemController implements Callback<ItemData> {

    ItemActivity activity;

    int week;
    int day;
    int slot;
    String title;
    String text;
    String color;

    public PostItemController(ItemActivity activity, int week, int day, int slot, String title, String text, String color){
        this.activity = activity;
        this.week = week;
        this.day = day;
        this.slot = slot;
        this.title = title;
        this.text = text;
        this.color = color;
    }

    public void post(){
        if(AppState.getInstance().getAuthController().isConnected()){
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
            PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
            Call<ItemData> call = plannerAPI.postItem(week, day, slot, title, text, color,
                    AppState.getInstance().getAuthController().getAuthHeader());
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ItemData> call, Response<ItemData> response){
        if(response.isSuccessful()) {
            ItemData data = response.body();
            if(AppState.getInstance().getPlannerPos().getWeek() == data.week_no){
                AppState.getInstance().setItem(data.day_no, data.slot_no, data);
                activity.finish();
            }
        }else{
            if(response.code() == 401){
                AppState.getInstance().getAuthController().refresh(()->{post();});
            }else {
                System.out.println(response.code());
                AppState.getInstance().setItem(day, slot, null);
            }
        }
    }

    @Override
    public void onFailure(Call<ItemData> call, Throwable t){
        t.printStackTrace();
        AppState.getInstance().setItem(day, slot, null);
    }
}

