package com.example.regioncountrytechnicalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.regioncountrytechnicalexam.api.RegionApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RegionApi.OnProvinceCompleted, View.OnClickListener {

    private RegionApi regionApi;
    private List<Region> regionList = new ArrayList<Region>();
    private List<SubRegion> subRegions = new ArrayList<SubRegion>();
    private Spinner regionSpinner, countrySpinner;
    private Button btnSubmit, btnClear;
    private EditText txtName;
    String regionName = "";
    String countryName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regionSpinner = findViewById(R.id.id_region);
        countrySpinner = findViewById(R.id.id_country);
        btnSubmit = findViewById(R.id.submit);
        btnClear = findViewById(R.id.clear);
        txtName = findViewById(R.id.name);




        regionApi = new RegionApi(MainActivity.this, this);
        regionApi._getRegions();

        btnSubmit.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void taskProvinceCompleted(JSONArray result) {

        try {
            ArrayList<String> theValue = new ArrayList<>();
            theValue.add("-");

            ArrayList<String> subValue = new ArrayList<>();
            subValue.add("-");

            for(int i=0;i<result.length();i++){
                JSONObject jsonObj = (JSONObject) result.get(i);
                String name = jsonObj.getString("region"); // here is your data for a object
                regionList.add(new Region(name));
                theValue.add(name);

            }
            regionSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, theValue));
            regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                int counter = 0;

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (counter > 0) {
                        regionName = theValue.get(i);
                    }
                    counter++;

                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            for(int i=0;i<result.length();i++){
                JSONObject jsonObj = (JSONObject) result.get(i);
                String name = jsonObj.getString("region"); // here is your data for a object
                subValue.add(name);

            }
            countrySpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subValue));
            countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                int counter = 0;

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (counter > 0) {
                        countryName = subValue.get(i);
                    }
                    counter++;

                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.submit) {
            if(txtName.getText().toString().isEmpty()) {
                txtName.setError("Required name!");
            }
            else {
                Log.wtf("name", regionName);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("region", regionName);
                intent.putExtra("country", countryName);
                intent.putExtra("name", txtName.getText().toString());
                startActivity(intent);
            }
        }
        else {
            txtName.setText("");
        }
    }
}