package com.example.regioncountrytechnicalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private List<Region> regionList = new ArrayList<Region>();;
    private Spinner regionSpinner;
    private Button btnSubmit, btnClear;
    private EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regionSpinner = findViewById(R.id.id_region);
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

            for(int i=0;i<result.length();i++){
                JSONObject jsonObj = (JSONObject) result.get(i);
                String name = jsonObj.getString("region"); // here is your data for a object
                regionList.add(new Region(name));
                theValue.add(name);
            }
            regionSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, theValue));

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
        }
        else {
            txtName.setText("");
        }
    }
}