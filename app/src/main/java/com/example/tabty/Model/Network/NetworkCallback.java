package com.example.tabty.Model.Network;

import com.example.tabty.Model.DB.Meal;

import java.util.List;

public interface NetworkCallback {
    public  void onSuccess(List<Meal> meals);
    public void onFailure(String errorMessage);
    public void onRandomMealSuccess(List<Meal> meals);
    void onRandomMealFailure(String errorMessage);
}
