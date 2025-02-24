package com.example.tabty.calendar.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.tabty.calendar.view.CalendarView;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.PlannedMeal;

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
                .subscribe();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getAllPlannedMealsByDate(LocalDate date){
         myRepo.getAllLocalPlannedMealByDate(date).subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new SingleObserver<List<PlannedMeal>>() {
                     @Override
                     public void onSubscribe(@NonNull Disposable d) {
                         //do-nothing
                     }

                     @Override
                     public void onSuccess(@NonNull List<PlannedMeal> plannedMeals) {
                         myView.onPlannedMealListSuccess(plannedMeals);
                     }

                     @Override
                     public void onError(@NonNull Throwable e) {
                        myView.onPlannedMealFailure(e.getLocalizedMessage());
                     }
                 });
    }
}
