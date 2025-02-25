package com.example.tabty.login.presenter;

import android.util.Patterns;

import com.example.tabty.login.view.LoginView;
import com.example.tabty.utilities.FirebaseCallback;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter  {
    private LoginView myView;
    private final FirebaseAuth myFirebase;
    private final FirebaseUser user;
    public LoginPresenter(LoginView myView){
        myFirebase = FirebaseAuth.getInstance();
        user = myFirebase.getCurrentUser();
        this.myView = myView;
    }
    public void loginAction(String name , String password){
        if(dataValidation(name,password)){
            FirebaseManagement.loginIntoFirebase(name, password, new FirebaseCallback() {
                @Override
                public void onFirebaseSuccess(String message) {
                    myView.onLoginSuccess(message);
                }
                @Override
                public void onFirebaseFailure(String errorMessage) {
                    myView.onLoginFailure(errorMessage);
                }
            });
        }
    }
     private boolean dataValidation(String email,String pass) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           myView.onEmailInvalid("Invalid Email");
            return false;
        }
        if(pass.length()<6){
           myView.onPasswordLengthShort("Password must be more than 5 characters");
            return false;
        }
        return true;
    }
}
