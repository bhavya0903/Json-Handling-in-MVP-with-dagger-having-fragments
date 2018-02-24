package com.example.bhavya.heady.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skarim on 4/22/17.
 */

public class DayWiseDataEntity {

    @SerializedName("categoryList")
    public List<String> categoryList = new ArrayList<>();

    @SerializedName("productList")
    public List<String> productList = new ArrayList<>();






//    public String happy;
//    @SerializedName("code")
//    public String code;
//    @SerializedName("date")
//    public String date;
//    @SerializedName("day")
//    public String day;
//    @SerializedName("high")
//    public String high;
//    @SerializedName("low")
//    public String low;
//    @SerializedName("text")
//    public String text;

    /*{
        "code":"34",
            "date":"21 Apr 2017",
            "day":"Fri",
            "high":"38",
            "low":"26",
            "text":"Mostly Sunny"
    },*/

}
