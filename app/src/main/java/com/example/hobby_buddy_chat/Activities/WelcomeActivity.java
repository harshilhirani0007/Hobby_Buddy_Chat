package com.example.hobby_buddy_chat.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hobby_buddy_chat.databinding.ActivityWelcomePageBinding;

public class WelcomeActivity extends AppCompatActivity {

    ActivityWelcomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityWelcomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            binding.SignUp.setOnClickListener(v ->
            {
                startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
            });

            binding.SignIn.setOnClickListener(v ->
            {
                startActivity(new Intent(WelcomeActivity.this, SignInActivity.class));
            });

    }
}