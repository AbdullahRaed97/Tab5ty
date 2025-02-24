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
import io.reactivex.rxjava3.core.Single;

public class PlannedMealLocalDataSource {
    PlannedMealDao dao;

    public PlannedMealLocalDataSource(Context context) {
        PlannedMealDatabase plannedMealDatabase = PlannedMealDatabase.getInstance(context);
        dao = plannedMealDatabase.getPlannedMealDao();
    }

    public Completable insertPlannedMeal(PlannedMeal plannedMeal) {
        return dao.insertPlannedMeal(plannedMeal);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Single<List<PlannedMeal>> getAllPlannedMealByDate(LocalDate date) {
        return dao.getAllPlannedMeal(date);
    }

    public Completable deletePlannedMeal(PlannedMeal meal) {
        return dao.deletePlannedMeal(meal);
    }
}
