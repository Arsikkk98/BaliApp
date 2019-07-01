package com.example.baliapp.models;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Category {

    private Intent categoryPage;
    private int image;

    public Category(Intent intent, int image){
        this.categoryPage = intent;
        this.image = image;
    }

    public Intent getCategoryPage() {
        return this.categoryPage;
    }

    public void setCategoryPage(Intent intent) {
        this.categoryPage = intent;
    }


    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
