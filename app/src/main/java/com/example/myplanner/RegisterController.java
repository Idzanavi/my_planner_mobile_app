package com.example.myplanner;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterController implements Callback<RegisterData> {

    Activity activity;

    public RegisterController(Activity activity){
        this.activity = activity;
    }


    public void register(String username, String email, String password, String password2){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL.URL).
                addConverterFactory(GsonConverterFactory.create(gson)).build();
        PlannerAPI plannerAPI = retrofit.create(PlannerAPI.class);
        Call<RegisterData> call = plannerAPI.register(username, email, password, password2);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RegisterData> call, Response<RegisterData> response){
        if(response.isSuccessful()) {
            RegisterData data = response.body();
            String message = "Successfully registered\nusername: " +
                            data.getUsername() +
                            "\ne-mail: " +
                            data.getEmail() + "\n";
            showOkDialog(message);

        }else{
            System.out.println(response.code());
            showErrorDialog("Can't register");
        }
    }

    @Override
    public void onFailure(Call<RegisterData> call, Throwable t){
        t.printStackTrace();
        showErrorDialog("Can't register");
    }

    private void showOkDialog(String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle("Success").setMessage(message).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        activity.finish();
                    }
                }).create().show();
    }

    private void showErrorDialog(String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogBuilder.setTitle("Error").setMessage(message).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();
    }
}
