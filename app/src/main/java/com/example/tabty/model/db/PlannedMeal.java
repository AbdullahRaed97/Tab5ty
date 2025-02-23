package com.example.tabty.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "PlannedMeal")
public class PlannedMeal {
    @PrimaryKey
    @NonNull
    public String idMeal;
    public String strMeal;
    public String strCategory;
    public String strArea;
    public String strInstructions;
    public String strMealThumb;
    Date mealDate;
    public PlannedMeal(Meal meal){
        this.idMeal=meal.getIdMeal();
        this.strMeal=meal.getStrMeal();
        this.strArea=meal.getStrArea();
        this.strMealThumb=meal.getStrMealThumb();
        this.strCategory=meal.getStrCategory();
        this.strInstructions=meal.getStrInstructions();
    }
    public PlannedMeal(){

    }
    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }
}
