package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myplanner.databinding.ActivityLoginBinding;
import com.example.myplanner.databinding.ActivityRegisterBinding;

public class RegisterActivity extends MyPlannerActivity {

    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });

        binding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.tryRegister();
            }
        });
    }

    private void tryRegister(){
        String username = binding.textUsername.getText().toString();
        String email = binding.textEmail.getText().toString();
        String password = binding.textPassword.getText().toString();
        String password2 = binding.textPassword2.getText().toString();

        RegisterController controller = new RegisterController(this);
        controller.register(username, email, password, password2);
    }

}