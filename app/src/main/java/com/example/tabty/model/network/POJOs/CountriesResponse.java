package com.example.tabty.model.network.POJOs;

import java.util.ArrayList;

public class CountriesResponse {
    private ArrayList<Country> meals;

    public CountriesResponse() {
    }

    public ArrayList<Country> getCountries() {
        return meals;
    }

    public void setCountries(ArrayList<Country> meals) {
        this.meals = meals;
    }
}
