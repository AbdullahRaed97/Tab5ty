package com.example.tabty.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PlannedMealDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
     Completable insertPlannedMeal(PlannedMeal meal);
    @Query("select * from PlannedMeal where mealDate = :date")
    Flowable<List<PlannedMeal>> getAllPlannedMeal(LocalDate date);
    @Delete
     Completable deletePlannedMeal(PlannedMeal meal);
}
