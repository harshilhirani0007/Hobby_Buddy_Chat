package com.example.hobby_buddy_chat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hobby_buddy_chat.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    FirebaseAuth mAUth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAUth = FirebaseAuth.getInstance();
        
        binding.tvSignUp.setOnClickListener(v ->
        {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        });

        binding.tvForgotPassword.setOnClickListener(v ->
        {
            startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class));
        });

    }
}