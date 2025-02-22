package com.example.tabty.Model.Network;

import com.example.tabty.Model.Network.POJOs.CategoriesResponse;
import com.example.tabty.Model.Network.POJOs.CountriesResponse;
import com.example.tabty.Model.Network.POJOs.IngredientResponse;
import com.example.tabty.Model.Network.POJOs.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMeals {
        @GET("random.php")
        Call<MealResponse> getRandomMeal();//done
        //return all details of random meal in ResponseMeal obj contain List(meals) having one meal only
        @GET("search.php")
        Call<MealResponse> getMealsByFirstLetter(@Query("f") String firstLetter);//done
        // Return MealResponse obj contain list(meals) of meals according to the similarity of first letter
        @GET("categories.php")
        Call<CategoriesResponse> getAllCategories();
        //return CategoriesResponse obj contain List(categories) of all categories

        @GET("list.php?i=list")
        Call<IngredientResponse> getAllIngredients();
        //return IngredientResponse obj contain List(meals) of all ingredient
        @GET("list.php?a=list")
        Call<CountriesResponse> getAllCountries();
        //return CountriesResponse obj contain List(meals) of all countries name
        @GET("lookup.php")
        Call<MealResponse> getFullDetailedMeal(@Query("i") String id );
        //return all details of specific meal by its id in ResponseMeal obj contain List(meals) having one meal only
        @GET("filter.php")
        Call<MealResponse> getAllMealsByCategory(@Query("c") String category);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific category
        @GET("filter.php")
        Call<MealResponse> getAllMealsByArea(@Query("a") String country);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific country
        @GET("filter.php")
        Call<MealResponse>  getAllMealsByIngredient(@Query("i") String ingredient);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific main ingredient
}

