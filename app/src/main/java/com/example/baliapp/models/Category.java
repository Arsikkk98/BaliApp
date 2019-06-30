package com.example.baliapp.models;

import androidx.appcompat.app.AppCompatActivity;

public class Category {

    private Class categoryPage;
    private int image;

    public Category(Class cls, int image){
        this.categoryPage = cls;
        this.image = image;
    }

    public Class getCategoryPage() {
        return this.categoryPage;
    }

    public void setCategoryPage(Class cls) {
        this.categoryPage = cls;
    }


    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
