package com.example.baliapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.baliapp.models.Category;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Настройки интерфейса

        // Изменение цвета текста в статус баре на темный,
        //проверка на версию api 23, так как работает только для api23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        //Настройка текста ActionBar
        ActionBar actionBar = getSupportActionBar();
        TextView tv = new TextView(getApplicationContext());
        Typeface typeface = ResourcesCompat.getFont(this, R.font.champagne_and_limousines);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(getResources().getString(R.string.main_title));
        tv.setTextSize(20);
        tv.setTextColor(Color.BLACK);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setTypeface(typeface, typeface.NORMAL);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(tv);

        //endregion

        setInitialData();
        RecyclerView categoriesList = (RecyclerView) findViewById(R.id.categories_list);
        DataAdapterCategories adapter = new DataAdapterCategories(this, categories);
        categoriesList.setAdapter(adapter);

    }

    private void setInitialData(){
        categories.add(new Category(SplashActivity.class, R.drawable.item1));
        categories.add(new Category(SplashActivity.class, R.drawable.item2));
        categories.add(new Category(SplashActivity.class, R.drawable.item3));
        categories.add(new Category(SplashActivity.class, R.drawable.item1));
    }

}
