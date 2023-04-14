package com.example.regioncountrytechnicalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView txtName, txtCountry, txtRegion;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = (TextView) findViewById(R.id.name);
        txtCountry = (TextView) findViewById(R.id.region);
        txtRegion = (TextView) findViewById(R.id.country);

        Intent intent = getIntent();

        String region = intent.getStringExtra("region");
        String country = intent.getStringExtra("country");
        String name = intent.getStringExtra("name");
        txtName.setText("Your Capital City is: "+region);
        txtCountry.setText("You are from "+country);
        txtRegion.setText("Hi "+name);


    }
}