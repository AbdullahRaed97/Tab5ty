package com.example.tabty.Home.presenter;

import com.example.tabty.Home.view.HomeView;
import com.example.tabty.Model.DB.Meal;
import com.example.tabty.Model.MealsRepository;
import com.example.tabty.Model.Network.NetworkCallback;

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
