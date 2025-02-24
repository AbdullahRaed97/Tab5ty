package com.example.tabty.favourite.view;

import com.example.tabty.model.db.MealEntity;

import java.util.List;

public interface FavouriteView {
    void onFavouriteMealListSuccess(List<MealEntity> mealList);
    void onFavouriteMealListFailure(String errorMessage);
    void onDeleteMealSuccess(String success);
    void onDeleteMealFailure(String fail);
}
