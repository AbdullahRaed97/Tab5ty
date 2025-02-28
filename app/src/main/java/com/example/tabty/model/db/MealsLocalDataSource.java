package com.example.tabty.model.db;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSource {
    MealsDao dao;
    public  MealsLocalDataSource(Context context){
        MealsDataBase mealsDataBase = MealsDataBase.getInstance(context);
        dao = mealsDataBase.getMealsDao();
    }
    public Completable insertMeal(MealEntity meal){
       return dao.insertMeal(meal);
    }
    public Completable deleteMeal(MealEntity meal){
       return dao.deleteMeal(meal);
    }
    public Flowable<List<MealEntity>> getAllMeals(){
        return dao.getAllMeals();
    }
    public Completable deleteAllMeals(){
        return dao.deleteAllMeals();
    }
}
