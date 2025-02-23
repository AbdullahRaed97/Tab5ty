package com.example.tabty.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.MealEntity;

import java.util.List;

public class FavouritePresenter {

    MealsRepository myRepo;
    public FavouritePresenter(MealsRepository myRepo){
        this.myRepo=myRepo;
    }
    public void deleteLocalMeal(MealEntity meal){
        myRepo.deleteLocalMeal(meal);
    }
    public LiveData<List<MealEntity>> getAllLocalMeals(){
        return myRepo.getAllLocalMeals();
    }
}
