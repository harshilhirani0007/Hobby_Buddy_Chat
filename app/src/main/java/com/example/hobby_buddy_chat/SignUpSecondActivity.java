package com.example.hobby_buddy_chat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hobby_buddy_chat.databinding.ActivitySignupSecondBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.net.URI;

public class SignUpSecondActivity extends AppCompatActivity {

    ActivitySignupSecondBinding binding;
    FirebaseAuth mAuth;
    Uri selectedImageUri;
    String profilePicture,name,username,email,age,password;
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference databaseReference;
    Dialog customeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignupSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner spinner = findViewById(R.id.selectHobby);

        // Fetch the string array from strings.xml
        String[] hobbiesArray = getResources().getStringArray(R.array.spinner_items);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hobbiesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        LinearLayout container = findViewById(R.id.jobTitleContainer);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the spinner
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Create a TextView to display the selected item
                TextView textView = new TextView(SignUpSecondActivity.this);
                textView.setText(selectedItem);

                // Add TextView to the container
//                container.removeAllViews(); // Remove any previous views
                container.addView(textView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}
