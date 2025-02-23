package com.example.tabty.calendar.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.PlannedMeal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CalendarPresenter {
    PlannedMealRepository myRepo;
    public CalendarPresenter(PlannedMealRepository myRepo){
        this.myRepo=myRepo;
    }
    public void deleteLocalMeal(PlannedMeal meal){
        myRepo.deletePlannedMeal(meal);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<List<PlannedMeal>> getAllPlannedMealsByDate(LocalDate date){
        return myRepo.getAllLocalPlannedMealByDate(date);
    }
}
