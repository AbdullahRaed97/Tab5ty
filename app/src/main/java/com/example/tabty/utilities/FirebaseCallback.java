package com.example.tabty.utilities;

public interface FirebaseCallback {
    public void onFirebaseSuccess(String message);
    public void onFirebaseFailure(String errorMessage);
}
