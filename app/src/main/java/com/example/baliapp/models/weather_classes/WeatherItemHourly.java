package com.example.baliapp.models.weather_classes;

public class WeatherItemHourly {

    private String time;
    private int weatherRes;
    private int temp;

    public WeatherItemHourly(String time, int weatherRes, int temp) {
        this.time = time;
        this.weatherRes = weatherRes;
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getWeatherRes() {
        return weatherRes;
    }

    public void setWeatherRes(int weatherRes) {
        this.weatherRes = weatherRes;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
