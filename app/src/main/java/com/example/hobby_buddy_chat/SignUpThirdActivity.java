package com.example.hobby_buddy_chat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hobby_buddy_chat.Models.UserData;

import com.example.hobby_buddy_chat.databinding.ActivitySignupThirdBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignUpThirdActivity extends AppCompatActivity {

    ActivitySignupThirdBinding binding;
    FirebaseAuth mAuth;
    Uri selectedImageUri;
    String userId;
    String profilePicture,email,password,name,age,bio,gender,username;
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference databaseReference;
    Dialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Authenticate for Firebase
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
//                customDialog=new Dialog(getApplicationContext());
//                customDialog.setContentView(R.layout.process);
//                customDialog.setCancelable(false);
//                customDialog.show();

                // create intent for fetch data
                Intent i= getIntent();

                // SignUpSecond Page to fetch Data
                name=i.getStringExtra("name");
                username=i.getStringExtra("username");
                email=i.getStringExtra("email");
                age=i.getStringExtra("age");
                password=i.getStringExtra("password");
                gender=i.getStringExtra("gender");

                //  input bio form user
                bio=binding.enterBio.getText().toString();

                // create user to register for Application
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        // create an object from databse
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        // create a reference from databse
                        databaseReference=database.getReference("users");

                        if(task.isSuccessful())
                        {
                            userId=mAuth.getCurrentUser().getUid();
                            uploadImage();
                        }
                        else
                        {
                            Toast.makeText(SignUpThirdActivity.this, "xx Not done, Try Again.. xx", Toast.LENGTH_SHORT).show();
//                            customDialog.dismiss();
                        }
                    }
                });
            }

        });
    }
    public void updateProfilePictureInDatabase()
    {
        UserData newUserData=new UserData(userId,name,username,email,age,password,gender,bio,profilePicture);
        databaseReference.child(userId).setValue(newUserData);

        Intent i=new Intent(SignUpThirdActivity.this,MainActivity.class);
//        customDialog.dismiss();
        startActivity(i);

        finish();
        Toast.makeText(this, "Done..", Toast.LENGTH_SHORT).show();
    }
    public void uploadImage()
    {
        if(selectedImageUri != null)
        {
            StorageReference storageReference= FirebaseStorage.getInstance().getReference();
            StorageReference imageRef=storageReference.child("images/" + System.currentTimeMillis() + ".jpg");

            imageRef.putFile(selectedImageUri).addOnCompleteListener(this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful())
                    {
                        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful())
                                {
                                    Uri downloadUri=task.getResult();
                                    profilePicture=downloadUri.toString();

                                    // Update or Upload Profile Picture in Database
                                    updateProfilePictureInDatabase();
                                }
                                else
                                {
                                    Toast.makeText(SignUpThirdActivity.this, "Failed to get download URL", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else 
                    {
                        Toast.makeText(SignUpThirdActivity.this, "Failed to upload Image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this, "Please Select an Image.", Toast.LENGTH_SHORT).show();
        }
    }
}

