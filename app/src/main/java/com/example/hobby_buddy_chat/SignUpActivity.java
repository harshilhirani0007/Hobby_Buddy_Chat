package com.example.hobby_buddy_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hobby_buddy_chat.databinding.ActivitySignUpBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

        binding.btnSignUpWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    void sendData()
    {
        String name=binding.edtName.getText().toString();
        String username=binding.edtUserName.getText().toString();
        String email=binding.edtEmail.getText().toString();
        String age=binding.edtAge.getText().toString();
        String password=binding.edtpassword.getText().toString();

        if(username.contains(" "))
        {
            Toast.makeText(this, "Username Cannot contain whitespace", Toast.LENGTH_SHORT).show();
        }
        else if(password.length() < 8)
        {
            Toast.makeText(this, "Password must contain 8 or more letters", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i=new Intent(SignUpActivity.this,SignUpSecondActivity.class);
            i.putExtra("name",name);
            i.putExtra("username",username);
            i.putExtra("email",email);
            i.putExtra("age",age);
            i.putExtra("password",password);

            startActivity(i);

        }
    }
}