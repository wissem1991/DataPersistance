package com.example.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void redirectTo(View view){
        Intent intent;
        String tag = view.getTag().toString();
        switch (tag) {
            case "preference":
                intent = new Intent(this, SharedPreferences.class);
                break;
            case "file":
                intent = new Intent(this, FileStorage.class);
                break;
            case "database":
                intent = new Intent(this, DataBase.class);
                break;
            default:
                intent = new Intent();
        }
        startActivity(intent);
    }
}