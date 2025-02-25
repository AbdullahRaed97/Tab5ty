package com.example.tabty.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealsDao {
    @Query("select * from Meals")
    Flowable<List<MealEntity>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(MealEntity meal);
    @Delete
    Completable deleteMeal(MealEntity meal);
}
