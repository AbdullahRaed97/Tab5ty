package com.example.tabty.Model;

import androidx.lifecycle.LiveData;

import com.example.tabty.Model.DB.Meal;
import com.example.tabty.Model.DB.MealEntity;
import com.example.tabty.Model.DB.MealsLocalDataSource;
import com.example.tabty.Model.Network.MealRemoteDataSource;
import com.example.tabty.Model.Network.NetworkCallback;

import java.util.List;

public class MealsRepository {
    MealRemoteDataSource remoteDataSource;
    MealsLocalDataSource localDataSource;
    private static MealsRepository instance = null ;

    private MealsRepository(MealRemoteDataSource remoteDataSource , MealsLocalDataSource localDataSource) {
        this.localDataSource=localDataSource;
        this.remoteDataSource=remoteDataSource;
    }
    public static MealsRepository getInstance(MealRemoteDataSource remoteDataSource , MealsLocalDataSource localDataSource){
        if(instance==null)
            instance = new MealsRepository(remoteDataSource,localDataSource);
        return instance;
    }
    public void insertLocalMeal(MealEntity meal){
        localDataSource.insertMeal(meal);
    }
    public void deleteLocalMeal(MealEntity meal){
        localDataSource.deleteMeal(meal);
    }
    public LiveData<List<MealEntity>> getAllLocalMeals(){
        return localDataSource.getAllMeals();
    }
    public void getAllRemoteMealByFirstLetter(NetworkCallback networkCallback,String firstLetter){
        remoteDataSource.callMealsByFirstLetter(networkCallback,firstLetter);
    }

    public void getRemoteRandomMeal(NetworkCallback networkCallback){
        remoteDataSource.callRandomMeal(networkCallback);
    }
    //To-Do complete all other calls
}
