package com.example.hobby_buddy_chat.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hobby_buddy_chat.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.edtLoginEmail.getText().toString();
                String password=binding.edtLoginPassword.getText().toString();

                if(password.length() < 8)
                {
                    Toast.makeText(SignInActivity.this, "Password contain 8 or more letters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAUth.signInWithEmailAndPassword(email,password).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                FirebaseUser user=mAUth.getCurrentUser();
                                Toast.makeText(SignInActivity.this, "" + user.getDisplayName(), Toast.LENGTH_SHORT).show();

                                Intent i=new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignInActivity.this, "Authentication failed..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}