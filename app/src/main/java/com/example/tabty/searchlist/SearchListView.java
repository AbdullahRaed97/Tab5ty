package com.example.tabty.searchlist;

import com.example.tabty.model.db.Meal;

import java.util.List;

public interface SearchListView {
    void showAllMealsByIngredient(List<Meal> meals);
    void showAllMealsByCategory(List<Meal> meals);
    void showAllMealsByCountry(List<Meal> meals);
}
