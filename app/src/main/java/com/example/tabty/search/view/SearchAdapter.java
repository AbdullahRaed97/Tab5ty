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

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    List<Country> countries;
    List<Ingredient> ingredients;
    List<Category> categories;
    String type;
    public SearchAdapter(Context context, List<Country> countries, List<Category> categories
            , List<Ingredient> ingredients, String type) {
        this.context = context;
        this.countries = countries;
        this.ingredients=ingredients;
        this.type=type;
        this.categories=categories;
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
                //Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ingredients.get(position)+"-Small.png")
                //.apply(new RequestOptions().override(500,500)).into(holder.image);}
                break;
            case "Ingredient":
                holder.nameText.setText(ingredients.get(position).getStrIngredient());
                Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredients.get(position).getStrIngredient() + "-Small.png")
                        .apply(new RequestOptions().override(500, 500)).into(holder.image);
                break;
            case "Category":
                holder.nameText.setText(categories.get(position).getStrCategory());
                Glide.with(context).load(categories.get(position).getStrCategoryThumb())
                        .apply(new RequestOptions().override(500, 500)).into(holder.image);
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
    private String getCountryNameCode(String countryName) {
        countryName = countryName.toLowerCase();

        switch (countryName) {
            case "american": return "us";
            case "british": return "gb";
            case "canadian": return "ca";
            case "chinese": return "cn";
            case "croatian": return "hr";
            case "dutch": return "nl";
            case "egyptian": return "eg";
            case "french": return "fr";
            case "greek": return "gr";
            case "indian": return "in";
            case "irish": return "ie";
            case "italian": return "it";
            case "jamaican": return "jm";
            case "japanese": return "jp";
            case "kenyan": return "ke";
            case "malaysian": return "my";
            case "mexican": return "mx";
            case "moroccan": return "ma";
            case "polish": return "pl";
            case "portuguese": return "pt";
            case "russian": return "ru";
            case "spanish": return "es";
            case "thai": return "th";
            case "tunisian": return "tn";
            case "turkish": return "tr";
            case "vietnamese": return "vn";
            case "filipino": return "ph";
            case "ukrainian": return "ua";
            case "uruguayan": return "uy";
            case "norwegian": return "no";
            default: return countryName;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nameText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            nameText=itemView.findViewById(R.id.nameText);
        }
    }
}
