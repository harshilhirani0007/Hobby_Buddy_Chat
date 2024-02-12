package com.example.hobby_buddy_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hobby_buddy_chat.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}