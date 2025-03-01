package com.example.tabty.calendar.presenter;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.tabty.calendar.view.CalendarView;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.utilities.FirestoreManagement;

import java.time.LocalDate;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    PlannedMealRepository myRepo;
    CalendarView myView;
    public CalendarPresenter(PlannedMealRepository myRepo , CalendarView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
    public void deleteLocalMeal(PlannedMeal meal){
        myRepo.deletePlannedMeal(meal)
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        FirestoreManagement.deletePlannedMealFromFirestore(meal);
    }
    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAllPlannedMealsByDate(LocalDate date){
         myRepo.getAllLocalPlannedMealByDate(date).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(item ->myView.onPlannedMealListSuccess(item),
                        error->myView.onPlannedMealFailure(error.getLocalizedMessage()));
    }
}
