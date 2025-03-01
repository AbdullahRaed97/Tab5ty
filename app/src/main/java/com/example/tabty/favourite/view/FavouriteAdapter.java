package com.example.tabty.favourite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabty.R;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.utilities.Utilities;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private Context context;
    private List<MealEntity> meals;
    OnFavItemClickListener listener;

    public FavouriteAdapter(Context context, List<MealEntity> meals, OnFavItemClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflates the the row on the sent context
        View rowView = inflater.inflate(R.layout.fav_item, recycleView, false);
        //create a ViewHolder to sent him the rowView
        ViewHolder rowHolder = new ViewHolder(rowView);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHolder holder, int position) {
        holder.favMeal_tv.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(500,500)).into(holder.favMeal_iv);
        Glide.with(context).load("https://www.themealdb.com/images/icons/flags/big/64/"+ Utilities.getCountryNameCode(meals.get(position).getStrArea())+".png")
                .apply(new RequestOptions().override(500, 500)).into(holder.favMealFlag_iv);
        holder.favMealDeleteBtn.setOnClickListener(v->{
            listener.onDeleteClickAction(meals.get(position));
        });
        holder.favCardView.setOnClickListener(v->{
            listener.onCardClickAction(meals.get(position).getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setData(List<MealEntity> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView favMeal_iv;
        TextView favMeal_tv;
        ImageButton favMealDeleteBtn;
        ImageView favMealFlag_iv;
        CardView favCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favMeal_iv = itemView.findViewById(R.id.favMeal_iv);
            favMeal_tv = itemView.findViewById(R.id.favMealName_tv);
            favMealFlag_iv = itemView.findViewById(R.id.favMealFlag_iv);
            favMealDeleteBtn = itemView.findViewById(R.id.favMealDeleteBtn);
            favCardView = itemView.findViewById(R.id.favCardView);
        }
    }
}
