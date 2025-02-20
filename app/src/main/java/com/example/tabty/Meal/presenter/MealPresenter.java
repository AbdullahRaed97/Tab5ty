package com.example.tabty.Meal.presenter;

import com.example.tabty.Meal.view.MealView;
import com.example.tabty.Model.MealsRepository;

public class MealPresenter {
    MealsRepository myRepo;
    MealView myView;

   public MealPresenter(MealsRepository myRepo , MealView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
}
