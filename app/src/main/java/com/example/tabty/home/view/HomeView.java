package com.example.tabty.home.view;

import com.example.tabty.model.db.Meal;

import java.util.List;

public interface HomeView {
    public void showAllMealsByFirstLetter(List<Meal> meals);
    public void showAllMealsByFirstLetterError(String errorMessage);
    public void showMealOfTheDay(Meal meal);
    public void showRandomMealError(String errorMessage);
}
