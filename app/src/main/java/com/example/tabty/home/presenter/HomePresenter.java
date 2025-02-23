package com.example.tabty.home.presenter;

import com.example.tabty.home.view.HomeView;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.network.NetworkCallback;

import java.util.List;

public class HomePresenter  {
    MealsRepository myRepo;
    HomeView myView;
    public HomePresenter(MealsRepository myRepo, HomeView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
    public void getRemoteAllMealsByFirstLetter(String firstLetter){
        myRepo.getAllRemoteMealByFirstLetter(new NetworkCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                myView.showAllMealsByFirstLetter(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                myView.showRandomMealError(errorMessage);
            }
        }, firstLetter);
    }

    public void getRemoteRandomMeal(){
        myRepo.getRemoteRandomMeal(new NetworkCallback<List<Meal>>(){

            @Override
            public void onSuccess(List<Meal> result) {
                myView.showRandomMeal(result);
            }
            @Override
            public void onFailure(String errorMessage) {
                myView.showRandomMealError(errorMessage);
            }
        });
    }

}
