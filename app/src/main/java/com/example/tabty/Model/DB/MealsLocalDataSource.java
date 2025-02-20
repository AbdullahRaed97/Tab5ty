package com.example.tabty.Model.DB;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MealsLocalDataSource {
    MealsDao dao;
    public  MealsLocalDataSource(Context context){
        MealsDataBase mealsDataBase = MealsDataBase.getInstance(context);
        dao = mealsDataBase.getMealsDao();
    }
    public void insertMeal(MealEntity meal){
        new Thread(){
            @Override
            public void run() {
                super.run();
                dao.insertMeal(meal);
            }
        }.start();
    }
    public void deleteMeal(MealEntity meal){
        new Thread(){
            @Override
            public void run() {
                super.run();
                dao.deleteMeal(meal);
            }
        }.start();
    }
    public LiveData<List<MealEntity>> getAllMeals(){
        return dao.getAllMeals();
    }
}
