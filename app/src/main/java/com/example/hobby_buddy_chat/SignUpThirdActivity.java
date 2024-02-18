package com.example.hobby_buddy_chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hobby_buddy_chat.databinding.ActivitySignupThirdBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpThirdActivity extends AppCompatActivity {

    ActivitySignupThirdBinding binding;
    FirebaseAuth mAuth;
    Uri selectedImageUri;
    String profilePicture, userId,email,password,name,age,bio,gender,username;
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference databaseReference;
    Dialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_third);

        mAuth = FirebaseAuth.getInstance();

        activityResultLauncher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            selectedImageUri = data.getData();
                            binding.imageProfileInput.setImageURI(selectedImageUri);
                        }
                    }
                });

        binding.imageProfileInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpThirdActivity.this, "Image Clicked", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activityResultLauncher.launch(intent);
            }
        });


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog=new Dialog(getApplicationContext());
                customDialog.setContentView(R.layout.process);
                customDialog.setCancelable(false);
                customDialog.show();
                Intent i= getIntent();
                name=i.getStringExtra("name");
                username=i.getStringExtra("username");
                email=i.getStringExtra("email");
                age=i.getStringExtra("age");
                password=i.getStringExtra("password");

            }
        });

    }
}