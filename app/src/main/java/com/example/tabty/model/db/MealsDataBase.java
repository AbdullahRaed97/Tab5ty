package com.example.tabty.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MealEntity.class}, version = 1)
public abstract class MealsDataBase extends RoomDatabase{
    private static MealsDataBase db = null;
    public abstract MealsDao getMealsDao();
    public static MealsDataBase getInstance(Context context){
        if( db == null){
            db = Room.databaseBuilder(context.getApplicationContext(), MealsDataBase.class,"Meals").build();
        }
        return db;
    }
}
