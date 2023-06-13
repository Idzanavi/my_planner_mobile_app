package com.example.myplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthController implements Callback<AuthData> {

    public interface RefreshUpdate{
        public void op();
    }

    private AuthData authData = null;

    RefreshUpdate update = null;

    public void login(String username, String password){
        update = null;
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
        Call<AuthData> call = plannerAPI.login(username, password);
        call.enqueue(this);
    }

    public void refresh(RefreshUpdate update){
        if(isConnected()){
            this.update = update;
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).build();
            PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
            Call<AuthData> call = plannerAPI.refresh(authData.refresh);
            call.enqueue(this);
        }
    }

    public boolean isConnected(){
        return authData != null;
    }

    public String getAuthHeader(){
        if(isConnected()) {
            return "Bearer " + authData.getAccess();
        }else{
            return "";
        }
    }

    @Override
    public void onResponse(Call<AuthData> call, Response<AuthData> response){
         if(response.isSuccessful()) {
             AuthData data = response.body();
             authData = data;
             updateActivityAuthStatus(true);
             if(this.update != null){
                 this.update.op();
             }
         }else{
             System.out.println(response.code());
             updateActivityAuthStatus(false);
         }
    }

    @Override
    public void onFailure(Call<AuthData> call, Throwable t){
        t.printStackTrace();
        updateActivityAuthStatus(false);
    }

    public void logout(){
        authData = null;
        updateActivityAuthStatus(false);
    }

    private void updateActivityAuthStatus(boolean connected){
        MyPlannerActivity activity = ActivityManager.getInstance().getActivity();
        if(activity != null){
            activity.updateAuthStatus(connected);
        }
    }
}
