package com.example.baliapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.baliapp.models.Category;

import java.util.List;

class DataAdapterCategories extends RecyclerView.Adapter<DataAdapterCategories.ViewHolder> {

    private LayoutInflater inflater;
    private List<Category> categories;

    DataAdapterCategories(Context context, List<Category> categories) {
        this.categories = categories;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapterCategories.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.category_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapterCategories.ViewHolder holder, int position) {
        final Category someCategory = categories.get(position);
        holder.imageView.setImageResource(someCategory.getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = someCategory.getCategoryPage();
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.categoryImage);
        }
    }
}