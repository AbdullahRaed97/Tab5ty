package com.example.tabty.model;

import androidx.lifecycle.LiveData;

import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;

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

    public LiveData<List<PlannedMeal>> getAllLocalPlannedMealByDate(Date date){
        return localDataSource.getAllPlannedMealByDate(date);
    }
}
