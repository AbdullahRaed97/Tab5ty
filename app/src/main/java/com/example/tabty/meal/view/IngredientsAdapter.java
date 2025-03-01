package com.example.tabty.meal.view;

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
import com.example.tabty.R;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> ingredient;
    private ArrayList<String> measures;
    IngredientsAdapter(Context context ,ArrayList<String> ingredient ,ArrayList<String> measures){
        this.ingredient=ingredient;
        this.context=context;
        this.measures=measures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        //create a new view for each row
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        //inflates the the row on the sent context
        View rowView = inflater.inflate(R.layout.ingredient_item, recycleView, false);
        //create a ViewHolder to sent him the rowView
        IngredientsAdapter.ViewHolder gridMealHolder = new IngredientsAdapter.ViewHolder(rowView);
        return gridMealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ingredientText.setText(ingredient.get(position));
        holder.measureText.setText(measures.get(position));
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredient.get(position)+"-Small.png")
                .apply(new RequestOptions().override(500,500)).into(holder.ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ingredientImage;
        TextView measureText;
        TextView ingredientText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.ingredient_iv);
            measureText=itemView.findViewById(R.id.measureText);
            ingredientText=itemView.findViewById(R.id.ingredientText);
        }
    }
}
