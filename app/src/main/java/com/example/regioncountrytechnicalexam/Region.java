package com.example.regioncountrytechnicalexam;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Region {

    @Expose
    @SerializedName("region")
    private String region;

    public Region(String region) {
        this.region = region;
    }
}
