package com.example.myplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myplanner.databinding.ActivityLoginBinding;
import com.example.myplanner.databinding.ActivityMainBinding;

public class LoginActivity extends MyPlannerActivity {

    private ActivityLoginBinding binding;
    AlertDialog.Builder dialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogBuilder = new AlertDialog.Builder(this);

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = binding.textLogin.getText().toString();
                String password = binding.textPassword.getText().toString();
                if(login.isEmpty()){
                    showError("Login can't be empty");
                }else if(password.isEmpty()){
                    showError("Password can't be empty");
                }else {
                    LoginActivity.this.tryLogin(login, password);
                }
            }
        });
    }

    private void tryLogin(String login, String password){
        AppState.getInstance().getAuthController().login(login, password);
    }

    private void showError(String message){
        dialogBuilder.setTitle("Error").setMessage(message).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();
    }

    @Override
    protected void checkAuthStatus(){
        if(AppState.getInstance().getAuthController().isConnected()){
            this.finish();
        }
    }

}