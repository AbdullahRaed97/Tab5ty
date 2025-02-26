package com.example.tabty.login.view;

public interface LoginView {
    void onEmailInvalid(String errorMessage);
    void onPasswordLengthShort(String errorMessage);
    void onLoginSuccess(String message);
    void onLoginFailure(String errorMessage);
    void onSignUpWithGoogleSuccess(String message);
    void onSignUpWithGoogleFailure(String errorMessage);
}
