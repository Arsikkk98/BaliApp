package com.example.baliapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baliapp.models.weather_classes.MyWeather;
import com.example.baliapp.models.weather_classes.WeatherItem;
import com.example.baliapp.models.weather_classes.WeatherItemHourly;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class WeatherFragment extends ListFragment {

    TextView todayDegreesTextView;
    TextView sunsetTextView;
    TextView weatherTypeTextView;
    TextView maxTempTextView;
    TextView minTempTextView;

    private List<WeatherItem> days = new ArrayList();
    private List<WeatherItemHourly> hours = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        todayDegreesTextView = (TextView) view.findViewById(R.id.todayDegreesTextView);
        sunsetTextView = (TextView) view.findViewById(R.id.sunsetTextView);
        weatherTypeTextView = (TextView) view.findViewById(R.id.weatherTypeTextView);
        maxTempTextView = (TextView) view.findViewById(R.id.maxTempTextView);
        minTempTextView = (TextView) view.findViewById(R.id.minTempTextView);

        new ProgressTask().execute("https://api.openweathermap.org/data/2.5/find?q=Denpasar&lang=ru&appid=cbc5d13097177f5161e51f6bbb5878bc");

        //region инициализация списка с днями
        days.add(new WeatherItem ("Понедельник", R.drawable.cloudy, 20, 15 ));
        days.add(new WeatherItem ("Вторник", R.drawable.cloudy, 21, 20 ));
        days.add(new WeatherItem ("Среда", R.drawable.cloudy, 20, 15 ));
        days.add(new WeatherItem ("Четверг", R.drawable.cloudy, 18, 18 ));
        days.add(new WeatherItem ("Пятница", R.drawable.cloudy, 25, 17 ));
        days.add(new WeatherItem ("Суббота", R.drawable.cloudy, 24, 18 ));
        days.add(new WeatherItem ("Воскресенье", R.drawable.cloudy, 24, 18 ));
        days.add(new WeatherItem ("Понедельник", R.drawable.cloudy, 24, 18 ));
        days.add(new WeatherItem ("Вторник", R.drawable.cloudy, 24, 18 ));
        days.add(new WeatherItem ("Среда", R.drawable.cloudy, 24, 18 ));

        // создаем адаптер
        WeatherListAdapter weatherAdapter = new WeatherListAdapter(getActivity(), R.layout.weather_list_item, days);

        setListAdapter(weatherAdapter);
        //endregion

        //region установка прокручивания LitView
        ListView listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow NestedScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow NestedScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        //endregion

        //region инициализация списка с часами
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));
        hours.add(new WeatherItemHourly ("22:00", R.drawable.cloudy, 20));

        // создаем адаптер
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.hourly_list);
        // создаем адаптер
        WeatherHourlyListAdapter adapter = new WeatherHourlyListAdapter(getActivity(), hours);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        //endregion

        return view;
    }


    private class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... path) {

            String content;
            try{
                content = getContent(path[0]);
            }
            catch (IOException ex){
                content = ex.getMessage();
            }

            return content;
        }
        @Override
        protected void onPostExecute(String content) {

            Gson gson = new Gson();
            MyWeather weatherToday = gson.fromJson(content, MyWeather.class);
            Double todayDegrees = Double.parseDouble(weatherToday.getList()[0].getMain().getTemp()) - 273.15;
            todayDegreesTextView.setText(todayDegrees.intValue()+"°");
            weatherToday.getList()[0].getSys().setSunset("21 : 15 WIB");
            sunsetTextView.setText(weatherToday.getList()[0].getSys().getSunset());
            String str = weatherToday.getList()[0].getWeather()[0].getDescription();
            String descr = str.substring(0, 1).toUpperCase() + str.substring(1);
            weatherTypeTextView.setText(descr);
            Double maxTemp = Double.parseDouble(weatherToday.getList()[0].getMain().getTemp_max()) - 273.15;
            maxTempTextView.setText(maxTemp.intValue()+"°");
            Double minTemp = Double.parseDouble(weatherToday.getList()[0].getMain().getTemp_min()) - 273.15;
            minTempTextView.setText(minTemp.intValue()+"°");

        }

        private String getContent(String path) throws IOException {
            BufferedReader reader=null;
            try {
                URL url=new URL(path);
                HttpsURLConnection c=(HttpsURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder buf=new StringBuilder();
                String line=null;
                while ((line=reader.readLine()) != null) {
                    buf.append(line + "\n");
                }
                return(buf.toString());
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
    }


}
