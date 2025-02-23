package com.example.tabty.home.view;

import com.example.tabty.model.db.Meal;

import java.util.List;

public interface HomeView {
    public void showAllMealsByFirstLetter(List<Meal> meals);
    public void showAllMealsByFirstLetterError(String errorMessage);
    public void showRandomMeal(List<Meal> meals);
    public void showRandomMealError(String errorMessage);
}
