package com.example.tabty.model.network;

import com.example.tabty.model.network.POJOs.CategoriesResponse;
import com.example.tabty.model.network.POJOs.CountriesResponse;
import com.example.tabty.model.network.POJOs.IngredientResponse;
import com.example.tabty.model.network.POJOs.MealResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMeals {
        @GET("random.php")
        Single<MealResponse> getRandomMeal();//done
        //return all details of random meal in ResponseMeal obj contain List(meals) having one meal only

        @GET("search.php")
        Single<MealResponse> getMealsByFirstLetter(@Query("f") String firstLetter);//done
        // Return MealResponse obj contain list(meals) of meals according to the similarity of first letter

        @GET("categories.php")
        Single<CategoriesResponse> getAllCategories();
        //return CategoriesResponse obj contain List(categories) of all categories

        @GET("list.php?i=list")
        Single<IngredientResponse> getAllIngredients();
        //return IngredientResponse obj contain List(meals) of all ingredient

        @GET("list.php?a=list")
        Single<CountriesResponse> getAllCountries();
        //return CountriesResponse obj contain List(meals) of all countries name

        @GET("lookup.php")
        Single<MealResponse> getFullDetailedMealByID(@Query("i") String id );
        //return all details of specific meal by its id in ResponseMeal obj contain List(meals) having one meal only

        @GET("filter.php")
        Single<MealResponse> getAllMealsByCategory(@Query("c") String category);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific category

        @GET("filter.php")
        Single<MealResponse> getAllMealsByArea(@Query("a") String country);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific country

        @GET("filter.php")
        Single<MealResponse>  getAllMealsByIngredient(@Query("i") String ingredient);
        //return MealResponse obj contains a List(meals) of all meals(id,name and photo only) of specific main ingredient
}

