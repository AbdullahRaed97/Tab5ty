package com.example.tabty.favourite.view;

import com.example.tabty.model.db.MealEntity;

public interface OnFavItemClickListener {
    public void onDeleteClickAction(MealEntity meal);
    void onCardClickAction(String mealID);
}
