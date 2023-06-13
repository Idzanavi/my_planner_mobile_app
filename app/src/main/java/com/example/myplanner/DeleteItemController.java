package com.example.myplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteItemController implements Callback<Void> {

    ItemActivity activity;

    int week;
    int day;
    int slot;

    public DeleteItemController(ItemActivity activity, int week, int day, int slot){
        this.activity = activity;
        this.week = week;
        this.day = day;
        this.slot = slot;
    }

    public void delete(){
        if(AppState.getInstance().getAuthController().isConnected()){
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
            PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
            Call<Void> call = plannerAPI.deleteItem(week, day, slot,
                    AppState.getInstance().getAuthController().getAuthHeader());
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response){
        if(response.isSuccessful()) {
            AppState.getInstance().setItem(day, slot, null);
            activity.finish();
        }else{
            if(response.code() == 401){
                AppState.getInstance().getAuthController().refresh(()->{delete();});
            }else {
                System.out.println(response.code());
                AppState.getInstance().setItem(day, slot, null);
            }
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t){
        t.printStackTrace();
        AppState.getInstance().setItem(day, slot, null);
    }
}

