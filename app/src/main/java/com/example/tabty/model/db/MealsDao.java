package com.example.tabty.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface MealsDao {
    @Query("select * from Meals")
    LiveData<List<MealEntity>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(MealEntity meal);
    @Delete
    void deleteMeal(MealEntity meal);
}
