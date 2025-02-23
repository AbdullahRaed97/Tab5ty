package com.example.tabty.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface PlannedMealDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    public void insertPlannedMeal(PlannedMeal meal);
    @Query("select * from PlannedMeal where mealDate = :date")
    public LiveData<List<PlannedMeal>>getAllPlannedMeal(Date date);
}
