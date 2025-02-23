package com.example.tabty.model.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

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