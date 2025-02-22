package com.example.tabty.Model.Network;

import com.example.tabty.Model.DB.Meal;
import com.example.tabty.Model.Network.POJOs.Ingredient;
import com.example.tabty.Model.Network.POJOs.IngredientResponse;
import com.example.tabty.Model.Network.POJOs.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    ApiMeals service;
    private static MealRemoteDataSource remoteDataSource;

    private MealRemoteDataSource() {
        Retrofit myRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = myRetrofit.create(ApiMeals.class);
    }
    public static MealRemoteDataSource getInstance(){
            if(remoteDataSource==null)
                remoteDataSource=new MealRemoteDataSource();
            return remoteDataSource;
        }
    public void callMealsByFirstLetter(NetworkCallback<List<Meal>> networkCallback,String firstLetter) {
        Call<MealResponse> call = service.getMealsByFirstLetter(firstLetter);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    networkCallback.onSuccess(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getMessage());
                throwable.printStackTrace();
            }
        });
    }
    public void callRandomMeal(NetworkCallback<List<Meal>> networkCallback ){
            Call<MealResponse> call = service.getRandomMeal();
            call.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    if(response.isSuccessful())
                        networkCallback.onSuccess(response.body().meals);
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable throwable) {
                    networkCallback.onFailure(throwable.getLocalizedMessage());
                    throwable.printStackTrace();
                }
            });
    }
    public void callIngredientResponse(NetworkCallback<List<Ingredient>> networkCallback){
        Call<IngredientResponse> call = service.getAllIngredients();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                networkCallback.onSuccess(response.body().meals);
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                networkCallback.onFailure(throwable.getLocalizedMessage());
            }
        });
    }
    public void callMealByID(NetworkCallback<Meal> networkCallback,String ID){
        Call<MealResponse> call = service.getFullDetailedMeal(ID);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onSuccess(response.body().meals.get(0));
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                    networkCallback.onFailure(throwable.getLocalizedMessage());
            }
        });
    }
    //To-Do make all other calls for ApiMeals
}

