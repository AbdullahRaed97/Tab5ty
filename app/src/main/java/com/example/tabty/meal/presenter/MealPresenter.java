package com.example.tabty.meal.presenter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;

import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.PlannedMeal;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import kotlin.text.MatchResult;
import kotlin.text.Regex;

public class MealPresenter {
    MealsRepository myRepo;
    PlannedMealRepository plannedMealRepo;
   public MealPresenter(MealsRepository myRepo,PlannedMealRepository plannedMealRepo){
        this.myRepo = myRepo;
        this.plannedMealRepo = plannedMealRepo;
    }
    public void insertLocalMeal(MealEntity meal){
       myRepo.insertLocalMeal(meal);
    }

    public String getVideoKey(String url){
        MatchResult matchResult = new Regex("v=([^&]+)").find(url, 0);
        if (matchResult != null) {
            return matchResult.getGroups().get(1).getValue();
        } else {
            return null;
        }
    }
    public void insertLocalPlannedMeal(Meal meal , Context context){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Handle the selected date
                    Calendar selectedDate = Calendar.getInstance();
                    LocalDate date = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = LocalDate.of(selectedYear,selectedMonth,selectedDay);
                    }
                    plannedMealRepo.insertLocalPlannedMeal(new PlannedMeal(meal,date));
                },year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();
    }

}
