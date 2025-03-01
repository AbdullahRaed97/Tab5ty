package com.example.tabty.home.presenter;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import com.example.tabty.home.view.HomeView;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.Meal;
import com.google.gson.Gson;
import java.time.LocalDate;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter  {
    private MealsRepository myRepo;
    private HomeView myView;
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
                .subscribe(item -> myView.showAllMealsByFirstLetter(item),
                        error-> Log.i("TAG", "getRemoteAllMealsByFirstLetter: "+error.getLocalizedMessage()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("CheckResult")
    private void getRemoteRandomMeal(SharedPreferences sharedPreferences){
        myRepo.getRemoteRandomMeal().subscribeOn(Schedulers.io())
                .map(item -> item.getMeals().get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> { myView.showMealOfTheDay(item);
                        saveMealOfTheDay(sharedPreferences,item,LocalDate.now().toString()); },
                        error-> Log.i("TAG", "getRemoteRandomMeal: "+error.getLocalizedMessage()));
    }

    private void saveMealOfTheDay(SharedPreferences sharedPreferences , Meal meal , String date){
        Gson json = new Gson();
        String mealString = json.toJson(meal);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString("Meal",mealString);
        myEditor.putString("Date",date);
        myEditor.apply();
    }
    private Meal getSavedMealOfTheDay(SharedPreferences sharedPreferences){
        Gson json = new Gson();
        String mealString = sharedPreferences.getString("Meal",null);
        Meal meal = json.fromJson(mealString,Meal.class);
        return meal;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getMealOfTheDay(SharedPreferences sharedPreferences){
        String date = sharedPreferences.getString("Date",null);
        if( date != null && date.equals(LocalDate.now().toString())){
           Meal mealOfTheDay = getSavedMealOfTheDay(sharedPreferences);
           myView.showMealOfTheDay(mealOfTheDay);
        }else{
            getRemoteRandomMeal(sharedPreferences);
        }
    }
}
