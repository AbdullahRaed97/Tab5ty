package com.example.tabty.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.tabty.utilities.DatabaseConverters;

@Database(entities = {PlannedMeal.class}, version = 1)
@TypeConverters({DatabaseConverters.class})
public abstract  class PlannedMealDatabase extends RoomDatabase {
    private static PlannedMealDatabase instance = null;
    public abstract PlannedMealDao getPlannedMealDao();

    public static PlannedMealDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PlannedMealDatabase.class, "PlannedMeal").build();
        }
        return instance;
    }
}