package com.example.tabty.calendar.view;

import com.example.tabty.model.db.PlannedMeal;

public interface OnCalendarItemClickListener {
    public void onDeleteClickAction(PlannedMeal meal);
    void onCalendarItemClickListener(String mealID);
}
