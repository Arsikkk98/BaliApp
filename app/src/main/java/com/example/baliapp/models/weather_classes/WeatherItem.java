package com.example.baliapp.models.weather_classes;

public class WeatherItem {

    private String weekday;
    private int weatherRes;
    private int maxTemp;
    private int minTemp;

    public WeatherItem(String weekday, int weatherRes, int maxTemp, int minTemp){

        this.weekday = weekday;
        this.weatherRes = weatherRes;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getWeatherRes() {
        return weatherRes;
    }

    public void setWeatherRes(int weatherRes) {
        this.weatherRes = weatherRes;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }
}
