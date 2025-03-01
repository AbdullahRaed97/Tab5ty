package com.example.tabty.model.network.POJOs;

import com.example.tabty.model.db.Meal;

import java.util.ArrayList;

public class MealResponse {
    private ArrayList<Meal> meals;
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
