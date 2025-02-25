package com.example.tabty.model;

import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.model.network.POJOs.IngredientResponse;
import com.example.tabty.model.network.POJOs.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
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
    public Flowable<List<MealEntity>> getAllLocalMeals(){
        return localDataSource.getAllMeals();
    }
    public Single<MealResponse> getAllRemoteMealByFirstLetter( String firstLetter){
        return remoteDataSource.callMealsByFirstLetter(firstLetter);
    }

    public Single<MealResponse> getRemoteRandomMeal(){
       return remoteDataSource.callRandomMeal();
    }

    public Single<IngredientResponse> getAllIngredients(){
        return remoteDataSource.callIngredientResponse();
    }
    public Single<MealResponse> getMealByID(String id){
       return remoteDataSource.callMealByID(id);
    }
    //To-Do complete all other calls
}
