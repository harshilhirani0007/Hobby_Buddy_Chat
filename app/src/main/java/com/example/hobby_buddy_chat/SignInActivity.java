package com.example.hobby_buddy_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hobby_buddy_chat.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvSignUp.setOnClickListener(v ->
        {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        });

        binding.tvForgotPassword.setOnClickListener(v ->
        {
            startActivity(new Intent(SignInActivity.this,ForgotPasswordActivity.class));
        });

    }
}