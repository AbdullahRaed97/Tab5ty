package com.example.tabty.searchlist.view;

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
import com.example.tabty.model.db.Meal;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder> {

    private Context context;
    private List<Meal> meals;
    private OnImageClickListener listener;
    public SearchListAdapter(Context context, List<Meal> meals,OnImageClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recycleView, int viewType) {
        //create a new view for each row
        LayoutInflater inflater = LayoutInflater.from(recycleView.getContext());
        //inflates the the row on the sent context
        View rowView = inflater.inflate(R.layout.serach_list_item, recycleView, false);
        //create a ViewHolder to sent him the rowView
        ViewHolder mealHolder = new ViewHolder(rowView);
        return mealHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameText.setText(meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(500, 500)).into(holder.searchListImage);
        holder.searchListImage.setOnClickListener(v->{
            listener.onImageClickAction(meals.get(position).getIdMeal());
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    public void setData(List<Meal> meals){
        this.meals=meals;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;
        private ImageView searchListImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.searchLisNameText);
            searchListImage=itemView.findViewById(R.id.searchListImage);
        }
    }
}
