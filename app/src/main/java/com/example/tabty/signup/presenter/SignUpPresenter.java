package com.example.tabty.signup.presenter;

import android.util.Patterns;

import com.example.tabty.signup.view.SignUpView;
import com.example.tabty.utilities.FirebaseCallback;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenter {
    private SignUpView myView;
    private final FirebaseAuth myFirebase;
    private final FirebaseUser user;

    public SignUpPresenter(SignUpView myView){
        this.myView=myView;
        this.myFirebase = FirebaseAuth.getInstance();
        this.user = myFirebase.getCurrentUser();
    }

    public void registerAction(String email , String password , String confirmPass){
        if(dataValidation(email,password,confirmPass)){
            FirebaseManagement.createFirebaseAccount(email, password, new FirebaseCallback() {
                @Override
                public void onFirebaseSuccess(String message) {
                    myView.onRegisterSuccess(message);
                }

                @Override
                public void onFirebaseFailure(String errorMessage) {
                    myView.onRegisterFailure(errorMessage);
                }
            });
        }
    }
    private boolean dataValidation(String email,String pass,String confirmPass) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            myView.onEmailInvalid("Invalid Email");
            return false;
        }
        if(pass.length()<6){
           myView.onPasswordLengthShort("Password must be more than 5 characters");
            return false;
        }
        if(!confirmPass.equals(pass)) {
            myView.passwordUnMatched("Un matched passwords");
            return false;
        }
        return true;
    }
}
