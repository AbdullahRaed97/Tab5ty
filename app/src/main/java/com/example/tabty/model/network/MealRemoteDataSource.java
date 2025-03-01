package com.example.tabty.model.network;

import com.example.tabty.model.db.Meal;
import com.example.tabty.model.network.POJOs.CategoriesResponse;
import com.example.tabty.model.network.POJOs.CountriesResponse;
import com.example.tabty.model.network.POJOs.Ingredient;
import com.example.tabty.model.network.POJOs.IngredientResponse;
import com.example.tabty.model.network.POJOs.MealResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private ApiMeals service;
    private static MealRemoteDataSource remoteDataSource;

    private MealRemoteDataSource() {
        Retrofit myRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        service = myRetrofit.create(ApiMeals.class);
    }
    public static MealRemoteDataSource getInstance(){
            if(remoteDataSource==null)
                remoteDataSource=new MealRemoteDataSource();
            return remoteDataSource;
        }
    public Single<MealResponse> callMealsByFirstLetter(String firstLetter) {
        return service.getMealsByFirstLetter(firstLetter);
    }
    public Single<MealResponse> callRandomMeal(){
        return service.getRandomMeal();
    }
    public Single<IngredientResponse> callIngredientResponse(){
        return service.getAllIngredients();
    }
    public Single<MealResponse> callMealByID(String ID){
        return service.getFullDetailedMealByID(ID);
    }
    public Single<CountriesResponse> getAllCountryResponse(){
        return service.getAllCountries();
    }
    public Single<CategoriesResponse> getAllCategoriesResponse(){
        return service.getAllCategories();
    }

    public Single<MealResponse> getAllMealsByCategory(String category){
        return service.getAllMealsByCategory(category);
    }
    public Single<MealResponse> getAllMealsByArea(String area){
        return service.getAllMealsByArea(area);
    }
    public Single<MealResponse> getAllMealsByIngredient(String ingredient){
        return service.getAllMealsByIngredient(ingredient);
    }
    //To-Do make all other calls for ApiMeals
}

