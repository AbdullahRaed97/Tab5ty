package com.example.tabty.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.tabty.utilities.DatabaseConverters;

@Database(entities = {MealEntity.class,PlannedMeal.class}, version = 1)
@TypeConverters({DatabaseConverters.class})
public abstract class MealsDataBase extends RoomDatabase{
    private static MealsDataBase db = null;
    public abstract MealsDao getMealsDao();
    public abstract PlannedMealDao getPlannedMealDao();
    public static MealsDataBase getInstance(Context context){
        if( db == null){
            db = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class,"Meals").build();
        }
        return db;
    }
}
