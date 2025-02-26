package com.example.tabty.search.view;

import com.example.tabty.model.network.POJOs.Category;
import com.example.tabty.model.network.POJOs.Country;
import com.example.tabty.model.network.POJOs.Ingredient;

import java.util.List;

public interface SearchView {

    void showAllIngredients(List<Ingredient> ingredients);
    void showAllCountries(List<Country> countries);
    void showAllCategories(List<Category> categories);
}
