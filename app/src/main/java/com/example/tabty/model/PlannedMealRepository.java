package com.example.tabty.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

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

    public Completable insertLocalPlannedMeal(PlannedMeal plannedMeal){
        return localDataSource.insertPlannedMeal(plannedMeal);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Flowable<List<PlannedMeal>> getAllLocalPlannedMealByDate(LocalDate date){
        return localDataSource.getAllPlannedMealByDate(date);
    }
    public Completable deletePlannedMeal(PlannedMeal meal){
        return localDataSource.deletePlannedMeal(meal);
    }
    public Flowable<List<PlannedMeal>> getAllPlannedMeal(){
        return localDataSource.getAllPlannedMeal();
    }
}
