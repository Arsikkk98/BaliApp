package com.example.baliapp.models.сonverter_classes;

public class Currency {
    private String type;
    private String code;
    private int img;
    private double value;

    public Currency(String type, String code, int img, double value) {
        this.type = type;
        this.code = code;
        this.img = img;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
