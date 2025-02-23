package com.example.tabty.model.network.POJOs;

import java.util.ArrayList;

public class CategoriesResponse {
    public CategoriesResponse() {
    }

    public ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CategoriesResponse{" +
                "categories=" + categories +
                '}';
    }
}
