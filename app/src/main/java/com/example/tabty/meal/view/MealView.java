package com.example.tabty.meal.view;

import com.example.tabty.model.db.MealEntity;

public interface MealView {
    void onInsertMealSuccess(String success);
    void onInsertMealFailure(String errorMessage);
    void onInsertPlannedMealSuccess(String success);
    void OnInsertPlannedMealFailure(String errorMessage);
}
