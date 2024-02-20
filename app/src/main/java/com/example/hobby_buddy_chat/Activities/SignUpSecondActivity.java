package com.example.hobby_buddy_chat.Activities;

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
import android.widget.Toast;

import com.example.hobby_buddy_chat.R;
import com.example.hobby_buddy_chat.databinding.ActivitySignupSecondBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpSecondActivity extends AppCompatActivity {

    ActivitySignupSecondBinding binding;
    FirebaseAuth mAuth;
    Uri selectedImageUri;

    // String name,username,email,age,password;
    String gender;

    DatabaseReference databaseReference;
    Dialog customeDialog;
    String[] hobbiesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySignupSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hobbiesArray = getResources().getStringArray(R.array.spinner_items);
        Spinner spinner = findViewById(R.id.selectHobby);

        // Fetch the string array from strings.xml

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hobbiesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        LinearLayout container = findViewById(R.id.jobTitleContainer);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = parent.getItemAtPosition(position).toString();
                TextView textView = new TextView(SignUpSecondActivity.this);
                textView.setText(selectedItem);

//                container.removeAllViews(); // Remove any previous views
                container.addView(textView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        mAuth = FirebaseAuth.getInstance();

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

    }


    // Send data SignUpSecondActivity to SignUpThirdActivity
    public void sendData()
    {
//        customeDialog = new Dialog(getApplicationContext());
//        customeDialog.setContentView(R.layout.process);
//        customeDialog .setCancelable(false);
//        customeDialog.show();

        // Create Intent for getdata from SignUpActivity
        Intent i=getIntent();

        if(binding.radioButtonMale.isChecked())
            gender="Male";
        else
            gender="Female";
        // create a second intent for send data to next page SignUpThirdActivity
        Intent nextIntent=new Intent(SignUpSecondActivity.this, SignUpThirdActivity.class);


        nextIntent.putExtra("name",i.getStringExtra("name"));
        nextIntent.putExtra("username",i.getStringExtra("username"));
        nextIntent.putExtra("email",i.getStringExtra("email"));
        nextIntent.putExtra("age",i.getStringExtra("age"));
        nextIntent.putExtra("password",i.getStringExtra("password"));
        nextIntent.putExtra("gender",gender);
        startActivity(nextIntent);

    }
}
