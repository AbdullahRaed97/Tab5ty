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

   @RequiresApi(api = Build.VERSION_CODES.O)
   public LiveData<List<PlannedMeal>> getAllPlannedMealByDate(LocalDate date){
      return dao.getAllPlannedMeal(date);
   }
   public void deletePlannedMeal(PlannedMeal meal){
       new Thread(){
           @Override
           public void run() {
               super.run();
               dao.deletePlannedMeal(meal);
           }
       }.start();
   }
}
