package com.example.tabty.Home.view;

import com.example.tabty.Model.DB.Meal;

import java.util.List;

public interface HomeView {
    public void showAllMealsByFirstLetter(List<Meal> meals);
    public void showAllMealsByFirstLetterError(String errorMessage);
    public void showRandomMeal(List<Meal> meals);
    public void showRandomMealError(String errorMessage);
}
