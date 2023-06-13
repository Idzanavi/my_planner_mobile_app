package com.example.myplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeekItemsController implements Callback<List<ItemData>> {

    PlannerActivity activity;

    public WeekItemsController(PlannerActivity activity){
        this.activity = activity;
    }

    public void get(){
        if(AppState.getInstance().getAuthController().isConnected()){
            int week_no = AppState.getInstance().getPlannerPos().getWeek();
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
            PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
            Call<List<ItemData>> call = plannerAPI.week(week_no,
                    AppState.getInstance().getAuthController().getAuthHeader());
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<List<ItemData>> call, Response<List<ItemData>> response){
        if(response.isSuccessful()) {
            List<ItemData> data = response.body();
            AppState.getInstance().setItems(data);
            activity.UpdateItems();
        }else{
            if(response.code() == 401){
                AppState.getInstance().getAuthController().refresh(()->{get();});
            }else {
                System.out.println(response.code());
                AppState.getInstance().setItems(null);
                activity.UpdateItems();
            }
        }
    }

    @Override
    public void onFailure(Call<List<ItemData>> call, Throwable t){
        t.printStackTrace();
        AppState.getInstance().setItems(null);
        activity.UpdateItems();
    }
}
