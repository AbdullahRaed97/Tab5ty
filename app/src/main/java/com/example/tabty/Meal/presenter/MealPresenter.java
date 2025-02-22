package com.example.tabty.Meal.presenter;

import com.example.tabty.Meal.view.MealView;
import com.example.tabty.Model.DB.Meal;
import com.example.tabty.Model.DB.MealEntity;
import com.example.tabty.Model.MealsRepository;

import kotlin.text.MatchResult;
import kotlin.text.Regex;

public class MealPresenter {
    MealsRepository myRepo;
   public MealPresenter(MealsRepository myRepo){
        this.myRepo=myRepo;
    }
    public void insertLocalMeal(MealEntity meal){
       myRepo.insertLocalMeal(meal);
    }
    public void insertMealIntoCalendar(MealEntity meal){
       //To-Do
    }

    public String getVideoKey(String url){
        MatchResult matchResult = new Regex("v=([^&]+)").find(url, 0);
        if (matchResult != null) {
            return matchResult.getGroups().get(1).getValue();
        } else {
            return null;
        }
    }

}
