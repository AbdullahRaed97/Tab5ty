package com.example.tabty.model;

import androidx.lifecycle.LiveData;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.model.network.NetworkCallback;
import com.example.tabty.model.network.POJOs.Ingredient;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

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
    public Completable insertLocalMeal(MealEntity meal){
        return localDataSource.insertMeal(meal);
    }
    public Completable deleteLocalMeal(MealEntity meal){
        return localDataSource.deleteMeal(meal);
    }
    public Single<List<MealEntity>> getAllLocalMeals(){
        return localDataSource.getAllMeals();
    }
    public void getAllRemoteMealByFirstLetter(NetworkCallback<List<Meal>> networkCallback,String firstLetter){
        remoteDataSource.callMealsByFirstLetter(networkCallback,firstLetter);
    }

    public void getRemoteRandomMeal(NetworkCallback<List<Meal>> networkCallback){
        remoteDataSource.callRandomMeal(networkCallback);
    }

    public void getAllIngredients(NetworkCallback<List<Ingredient>> networkCallback){
        remoteDataSource.callIngredientResponse(networkCallback);
    }
    public void getMealByID(NetworkCallback<Meal> networkCallback,String id){
        remoteDataSource.callMealByID(networkCallback,id);
    }
    //To-Do complete all other calls
}
