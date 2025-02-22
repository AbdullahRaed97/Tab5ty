package com.example.tabty.Meal.view;

import com.example.tabty.Model.DB.Meal;
import com.example.tabty.Model.DB.MealEntity;
import com.example.tabty.Model.Network.POJOs.Ingredient;

import java.util.List;

public interface MealView {
    public void onFavouriteButtonClicked(MealEntity meal);
}
