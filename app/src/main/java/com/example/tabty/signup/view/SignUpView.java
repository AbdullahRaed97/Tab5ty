package com.example.tabty.signup.view;

public interface SignUpView {
    void onEmailInvalid(String errorMessage);
    void onPasswordLengthShort(String errorMessage);
    void passwordUnMatched(String errorMessage);
    void onRegisterSuccess(String message);
    void onRegisterFailure(String errorMessage);
}
