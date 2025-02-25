package com.example.tabty.home.presenter;

import android.annotation.SuppressLint;
import com.example.tabty.home.view.HomeView;
import com.example.tabty.model.MealsRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter  {
    MealsRepository myRepo;
    HomeView myView;
    public HomePresenter(MealsRepository myRepo, HomeView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
    @SuppressLint("CheckResult")
    public void getRemoteAllMealsByFirstLetter(String firstLetter){
        myRepo.getAllRemoteMealByFirstLetter(firstLetter)
                .subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> myView.showAllMealsByFirstLetter(item));
    }

    @SuppressLint("CheckResult")
    public void getRemoteRandomMeal(){
        myRepo.getRemoteRandomMeal().subscribeOn(Schedulers.io())
                .map(item -> item.getMeals().get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> myView.showRandomMeal(item));
    }

}
