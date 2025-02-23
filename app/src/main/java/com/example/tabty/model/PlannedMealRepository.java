package com.example.tabty.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PlannedMealRepository {
    PlannedMealLocalDataSource localDataSource;
    private static PlannedMealRepository instance = null;

    private PlannedMealRepository(PlannedMealLocalDataSource localDataSource){
        this.localDataSource = localDataSource;
    }

    public static PlannedMealRepository getInstance(PlannedMealLocalDataSource localDataSource){
        if(instance == null)
            instance = new PlannedMealRepository(localDataSource);
        return instance;
    }

    public void insertLocalPlannedMeal(PlannedMeal plannedMeal){
        localDataSource.insertPlannedMeal(plannedMeal);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<List<PlannedMeal>> getAllLocalPlannedMealByDate(LocalDate date){
        return localDataSource.getAllPlannedMealByDate(date);
    }
    public void deletePlannedMeal(PlannedMeal meal){
        localDataSource.deletePlannedMeal(meal);
    }
}
