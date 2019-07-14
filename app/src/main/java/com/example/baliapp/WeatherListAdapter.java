package com.example.baliapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baliapp.models.weather_classes.WeatherItem;

import java.util.List;

public class WeatherListAdapter extends ArrayAdapter<WeatherItem> {

    private LayoutInflater inflater;
    private int layout;
    private List<WeatherItem> days;

    public WeatherListAdapter(Context context, int resource, List<WeatherItem> days) {
        super(context, resource, days);
        this.days = days;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView weekdayView = (TextView) view.findViewById(R.id.weekday);
        ImageView weatherResView = (ImageView) view.findViewById(R.id.weatherRes);
        TextView maxTempView = (TextView) view.findViewById(R.id.maxTemp);
        TextView minTempView = (TextView) view.findViewById(R.id.minTemp);

        WeatherItem day = days.get(position);

        weatherResView.setImageResource(day.getWeatherRes());
        weekdayView.setText(day.getWeekday());
        maxTempView.setText(day.getMaxTemp()+"°");
        minTempView.setText(day.getMinTemp()+"°");

        return view;
    }
}