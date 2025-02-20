package com.example.tabty.Home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabty.Model.DB.Meal;
import com.example.tabty.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private final Context context;
    List<Meal> meals;
    OnImageClickedListener imglistner;
    public HomeAdapter(Context context, OnImageClickedListener imglistner, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
        this.imglistner = imglistner;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        //create a new view for each row
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        //inflates the the row on the sent context
        View rowView = inflater.inflate(R.layout.grid_meal_items, recycleView, false);
        //create a ViewHolder to sent him the rowView
        ViewHolder gridMealHolder = new ViewHolder(rowView);
        return gridMealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.home_mealTitle.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(500,500)).into(holder.home_mealImage);
        holder.home_mealImage.setOnClickListener( r->{
            imglistner.imageClickedAction(meals.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setData(List<Meal> meals)
    {
        this.meals=meals;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView home_mealImage;
        TextView home_mealTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_mealImage=itemView.findViewById(R.id.home_mealImage);
            home_mealTitle=itemView.findViewById(R.id.home_mealTitle);
        }
    }
}
