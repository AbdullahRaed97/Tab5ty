package com.example.tabty.Model.Network;

import com.example.tabty.Model.DB.Meal;

import java.util.List;

public interface NetworkCallback<T> {
    public  void onSuccess(T result);
    public void onFailure(String errorMessage);

}
