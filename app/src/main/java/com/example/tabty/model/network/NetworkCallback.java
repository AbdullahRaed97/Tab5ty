package com.example.tabty.model.network;

public interface NetworkCallback<T> {
    public  void onSuccess(T result);
    public void onFailure(String errorMessage);

}
