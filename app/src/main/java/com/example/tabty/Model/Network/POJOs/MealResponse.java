package com.example.tabty.Model.Network.POJOs;

import com.example.tabty.Model.DB.Meal;

import java.util.ArrayList;

public class MealResponse {
    public ArrayList<Meal> meals;
    public MealResponse()
    {

    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "MealResponse{" +
                "meals=" + meals +
                '}';
    }
}
