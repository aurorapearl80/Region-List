package com.example.regioncountrytechnicalexam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubRegion {

    @Expose
    @SerializedName("region")
    private String region;

    public SubRegion(String region) {
        this.region = region;
    }
}
