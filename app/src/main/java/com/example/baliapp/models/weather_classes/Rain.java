package com.example.baliapp.models.weather_classes;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    private String oneH;

    @SerializedName("3h")
    private String threeH;

    public String getOneH() {
        return oneH;
    }

    public void setOneH(String oneH) {
        this.oneH = oneH;
    }

    public String getThreeH() {
        return threeH;
    }

    public void setThreeH(String threeH) {
        this.threeH = threeH;
    }
}
