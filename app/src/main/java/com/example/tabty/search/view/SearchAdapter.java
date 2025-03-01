package com.example.tabty.search.view;

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
import com.example.tabty.model.network.POJOs.Category;
import com.example.tabty.model.network.POJOs.Country;
import com.example.tabty.model.network.POJOs.Ingredient;
import com.example.tabty.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<Country> countries;
    private List<Ingredient> ingredients;
    private List<Category> categories;
    private String type;
    private OnImageClickListener listener;
    public SearchAdapter(Context context, List<Country> countries, List<Category> categories
            , List<Ingredient> ingredients, String type,OnImageClickListener listener) {
        this.context = context;
        this.countries = countries;
        this.ingredients=ingredients;
        this.type=type;
        this.categories=categories;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        //create a new view for each row
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        //inflates the the row on the sent context
        View rowView = inflater.inflate(R.layout.search_item, recycleView, false);
        //create a ViewHolder to sent him the rowView
       ViewHolder gridMealHolder = new ViewHolder(rowView);
        return gridMealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (type) {
            case "Country":
                holder.nameText.setText(countries.get(position).getStrArea());
                Glide.with(context).load("https://www.themealdb.com/images/icons/flags/big/64/"+ Utilities.getCountryNameCode(countries.get(position).getStrArea())+".png")
                        .apply(new RequestOptions().override(500, 500)).into(holder.image);
                holder.image.setOnClickListener(v->{
                    listener.onImageClickAction(countries.get(position).getStrArea());
                });
                break;
            case "Ingredient":
                holder.nameText.setText(ingredients.get(position).getStrIngredient());
                Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredients.get(position).getStrIngredient() + "-Small.png")
                        .apply(new RequestOptions().override(500, 500)).into(holder.image);
                holder.image.setOnClickListener(v->{
                    listener.onImageClickAction(ingredients.get(position).getStrIngredient());
                });
                break;
            case "Category":
                holder.nameText.setText(categories.get(position).getStrCategory());
                Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                        .apply(new RequestOptions().override(500, 500)).into(holder.image);
                holder.image.setOnClickListener(v->{
                    listener.onImageClickAction(categories.get(position).getStrCategory());
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (type){
            case "Country":
                return countries.size();
            case "Ingredient":
                return ingredients.size();
            case "Category" :
                return categories.size();
        }
        return 0;
    }
    public void setCountries(List<Country> countries){
        this.countries=countries;
        notifyDataSetChanged();
    }
    public void setIngredients(List<Ingredient> ingredients ){
        this.ingredients=ingredients;
        notifyDataSetChanged();
    }
    public void setCategories(List<Category> categories ){
        this.categories=categories;
        notifyDataSetChanged();
    }
   public void setType(String type){
        this.type=type;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView nameText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            nameText=itemView.findViewById(R.id.nameText);
        }
    }
}
