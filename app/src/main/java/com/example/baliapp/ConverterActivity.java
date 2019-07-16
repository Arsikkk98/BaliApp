package com.example.baliapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.baliapp.models.сonverter_classes.Currency;
import com.example.baliapp.models.weather_classes.MyWeather;
import com.example.baliapp.models.сonverter_classes.ExchangeRates;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ConverterActivity extends AppCompatActivity {

    private List<Currency> currencies = new ArrayList();
    private Currency firstCurrency;
    private Currency secondCurrency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Изменение цвета statusbar во время заставки
        Window w = getWindow();
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //endregion

        setContentView(R.layout.converter_activity);

        //region Замена ActionBar на ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //endregion

        //Открытие клавиатуры
        EditText editText = (EditText) findViewById(R.id.first_currency_value);
        editText.requestFocus();

        new ProgressTask().execute("https://openexchangerates.org/api/latest.json?app_id=b6979116e78f479cba34909c6a6885bf");

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
            ExchangeRates ratesToday = gson.fromJson(content, ExchangeRates.class);

            currencies.add(new Currency("Российский рубли", "RUB  ", R.drawable.rub, ratesToday.getRates().getRUB() ));
            currencies.add(new Currency("Индонезийская рупия", "IDR  ", R.drawable.idr, ratesToday.getRates().getIDR() ));
            currencies.add(new Currency("Доллар", "USD  ", R.drawable.usd, ratesToday.getRates().getUSD() ));
            currencies.add(new Currency("Евро", "EUR  ", R.drawable.eur, ratesToday.getRates().getEUR() ));

            firstCurrency = currencies.get(0);
            changeFirstCurrency();
            secondCurrency = currencies.get(1);
            changeSecondCurrency();
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

    private void changeFirstCurrency(){
        TextView firstCurrencyCode = (TextView) findViewById(R.id.first_currency_code);
        firstCurrencyCode.setText(firstCurrency.getCode());
        ImageView firstCurrencyImg = (ImageView) findViewById(R.id.first_currency_img);
        firstCurrencyImg.setImageResource(firstCurrency.getImg());
    }

    private void changeSecondCurrency(){
        TextView secondCurrencyCode = (TextView) findViewById(R.id.second_currency_code);
        secondCurrencyCode.setText(secondCurrency.getCode());
        ImageView secondCurrencyImg = (ImageView) findViewById(R.id.second_currency_img);
        secondCurrencyImg.setImageResource(secondCurrency.getImg());
    }

    public void OnChangeFirstCurrency(View view) {
        int nowIndex = currencies.indexOf(firstCurrency);
        firstCurrency = currencies.get(++nowIndex%currencies.size());
        changeFirstCurrency();
    }

    public void OnChangeSecondCurrency(View view) {
        int nowIndex = currencies.indexOf(secondCurrency);
        secondCurrency = currencies.get(++nowIndex%currencies.size());
        changeSecondCurrency();
    }

    //Включение/Отключение нужного параметра
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
