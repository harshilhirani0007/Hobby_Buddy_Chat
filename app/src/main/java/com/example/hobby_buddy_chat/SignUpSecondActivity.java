package com.example.hobby_buddy_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SignUpSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_second);

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
