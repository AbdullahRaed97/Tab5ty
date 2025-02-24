package com.example.tabty.calendar.view;

import com.example.tabty.model.db.PlannedMeal;

import java.util.List;

public interface CalendarView {

    void onPlannedMealListSuccess(List<PlannedMeal> plannedMeals);
    void onPlannedMealFailure(String errorMessage);
}
