package com.example.tabty.model.db;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.tabty.utilities.DatabaseConverters;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class PlannedMealLocalDataSource {
    private PlannedMealDao dao;

    public PlannedMealLocalDataSource(Context context) {
        MealsDataBase plannedMealDatabase = MealsDataBase.getInstance(context);
        dao = plannedMealDatabase.getPlannedMealDao();
    }

    public Completable insertPlannedMeal(PlannedMeal plannedMeal) {
        return dao.insertPlannedMeal(plannedMeal);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Flowable<List<PlannedMeal>> getAllPlannedMealByDate(LocalDate date) {
        return dao.getAllPlannedMeal(date);
    }

    public Completable deletePlannedMeal(PlannedMeal meal) {
        return dao.deletePlannedMeal(meal);
    }
    public Flowable<List<PlannedMeal>> getAllPlannedMeal(){
        return dao.getAllPlannedMeal();
    }
    public Completable deleteAllPlannedMeal(){
        return dao.deleteAllPlannedMeal();
    }
}
