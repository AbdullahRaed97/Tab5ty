package com.example.tabty.meal.presenter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.tabty.meal.view.MealView;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.utilities.FirestoreManagement;

import java.time.LocalDate;
import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.text.MatchResult;
import kotlin.text.Regex;

public class MealPresenter {
    MealsRepository myRepo;
    PlannedMealRepository plannedMealRepo;
    MealView myView;
   public MealPresenter(MealsRepository myRepo,PlannedMealRepository plannedMealRepo , MealView myView){
        this.myRepo = myRepo;
        this.plannedMealRepo = plannedMealRepo;
        this.myView=myView;
    }
    public void insertLocalMeal(MealEntity meal){
       myRepo.insertLocalMeal(meal)
               .subscribeOn(Schedulers.io())
               .subscribe(new CompletableObserver() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onComplete() {
                       Log.i("TAG", "onComplete: insertion success");
                       myView.onInsertMealSuccess("Meal is added to your favourites successfully");
                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       Log.i("TAG", "onError: "+e.getLocalizedMessage());
                       myView.onInsertMealFailure(e.getLocalizedMessage());
                   }
               });
        FirestoreManagement.insertFavMealInFirestore(meal);
    }

    public String getVideoKey(String url){
        MatchResult matchResult = new Regex("v=([^&]+)").find(url, 0);
        if (matchResult != null) {
            return matchResult.getGroups().get(1).getValue();
        } else {
            return "";
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    date = LocalDate.of(selectedYear,selectedMonth,selectedDay);
                    FirestoreManagement.insertPlannedMealInFirestore(new PlannedMeal(meal,date));
                    plannedMealRepo.insertLocalPlannedMeal(new PlannedMeal(meal,date))
                            .subscribeOn(Schedulers.io())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    myView.onInsertPlannedMealSuccess("Meal is added to your Calendar successfully");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    myView.OnInsertPlannedMealFailure(e.getLocalizedMessage());
                                }
                            });
                },year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+(1000*1*60*60*24*6));
        datePickerDialog.show();
    }
    public boolean isGuestMode(SharedPreferences sharedPreferences){
       return sharedPreferences.getBoolean("isGuest",false);
    }
    @SuppressLint("CheckResult")
    public void getMealByID(String ID){
       myRepo.getMealByID(ID).subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .map(item -> item.getMeals().get(0))
               .subscribe(item -> myView.showMealByID(item));
    }
}
