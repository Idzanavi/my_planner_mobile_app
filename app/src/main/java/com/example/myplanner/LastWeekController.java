package com.example.myplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LastWeekController implements Callback<WeekNoData> {

    public LastWeekController(){
    }

    public void get(){
        if(AppState.getInstance().getAuthController().isConnected()){
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
            PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
            Call<WeekNoData> call = plannerAPI.lastWeek(
                    AppState.getInstance().getAuthController().getAuthHeader());
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<WeekNoData> call, Response<WeekNoData> response){
        if(response.isSuccessful()) {
            WeekNoData data = response.body();
            AppState.getInstance().getPlannerPos().setWeek(data.getWeek_no());

        }else{
            if(response.code() == 401){
                AppState.getInstance().getAuthController().refresh(()->{get();});
            }else {
                System.out.println(response.code());
            }
        }
    }

    @Override
    public void onFailure(Call<WeekNoData> call, Throwable t){
        t.printStackTrace();
    }
}
