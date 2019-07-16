package com.example.baliapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.baliapp.models.weather_classes.WeatherItemHourly;

import java.util.List;

public class WeatherHourlyListAdapter extends RecyclerView.Adapter<WeatherHourlyListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<WeatherItemHourly> hours;

    WeatherHourlyListAdapter(Context context, List<WeatherItemHourly> hours) {
        this.hours = hours;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public WeatherHourlyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.weather_hourly_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHourlyListAdapter.ViewHolder holder, int position) {
        WeatherItemHourly hour = hours.get(position);
        holder.timeView.setText(hour.getTime());
        holder.weatherResView.setImageResource(hour.getWeatherRes());
        holder.tempView.setText(hour.getTemp()+"°");
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView weatherResView;
        final TextView timeView, tempView;
        ViewHolder(View view){
            super(view);
            weatherResView = (ImageView)view.findViewById(R.id.weatherRes);
            timeView = (TextView) view.findViewById(R.id.time);
            tempView = (TextView) view.findViewById(R.id.temp);
        }
    }
}
