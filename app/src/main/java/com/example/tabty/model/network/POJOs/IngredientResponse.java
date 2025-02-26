package com.example.tabty.model.network.POJOs;

import java.util.ArrayList;

public class IngredientResponse {
    public ArrayList<Ingredient> meals;

    public IngredientResponse() {
    }

    public ArrayList<Ingredient> getIngredients() {
        return meals;
    }

    public void setIngredients(ArrayList<Ingredient> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "IngredientResponse{" +
                "meals=" + meals +
                '}';
    }
}
