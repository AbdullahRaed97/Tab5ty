package com.example.tabty.model.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class PlannedMealLocalDataSource {
    PlannedMealDao dao;

   public PlannedMealLocalDataSource(Context context){
       PlannedMealDatabase plannedMealDatabase =PlannedMealDatabase.getInstance(context);
       dao = plannedMealDatabase.getPlannedMealDao();
   }

   public void insertPlannedMeal(PlannedMeal plannedMeal){
       new Thread() {
           @Override
           public void run() {
               super.run();
               dao.insertPlannedMeal(plannedMeal);
           }
       }.start();
   }

   public LiveData<List<PlannedMeal>> getAllPlannedMealByDate(Date date){
       return dao.getAllPlannedMeal(date);
   }
}
