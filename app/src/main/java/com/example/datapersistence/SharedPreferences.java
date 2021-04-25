package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferences extends AppCompatActivity {

    private static final String PREFERENCE_NAME = "userProfile";
    private static final String PROFILE_NAME = "name";
    private static final String PROFILE_AGE = "age";
    EditText ageInput;
    EditText nameInput;
    TextView ageShow;
    TextView nameShow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        ageInput = findViewById(R.id.InputAgeInterne);
        ageShow = findViewById(R.id.showMessage);
        nameInput = findViewById(R.id.InputNameInterne);
        nameShow = findViewById(R.id.showName);

    }

    public void saveData(View view) {
        android.content.SharedPreferences.Editor editor = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(PROFILE_NAME, nameInput.getText().toString());
        editor.putInt(PROFILE_AGE, Integer.valueOf(ageInput.getText().toString()));
        editor.commit();
        Toast.makeText(this, "Data saved succefully", Toast.LENGTH_LONG).show();
    }

    public void getSavedData(View view){
        android.content.SharedPreferences result = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = result.getString(PROFILE_NAME, "No data found");
        int age = result.getInt(PROFILE_AGE, 0);

        nameShow.setText("Name: " +name);
        ageShow.setText("Age: " + age);
    }
}