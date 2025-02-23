package com.example.tabty.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tabty.R;
import com.example.tabty.utilities.FirebaseCallback;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.android.material.snackbar.Snackbar;


public class LoginFragment extends Fragment implements FirebaseCallback {

    EditText emailText;
    EditText passwordText;
    Button loginButton;
    TextView signupText;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton= view.findViewById(R.id.loginButton);
        emailText=view.findViewById(R.id.emailTextLogin);
        passwordText=view.findViewById(R.id.passwordTextLogin);
        signupText=view.findViewById(R.id.signupText);
        navController = Navigation.findNavController(view);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String pass = passwordText.getText().toString();
                boolean isValid =dataValidation(email,pass);
                if(isValid)
                    FirebaseManagement.loginIntoFirebase(email,pass,LoginFragment.this);
            }
        });

    }
    private boolean dataValidation(String email,String pass) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Email is invalid");
            return false;
        }
        if(pass.length()<6){
            passwordText.setError("invalid password length");
            return false;
        }
        return true;
    }
    @Override
    public void onFirebaseResponse(boolean success, String errorMessage) {
        if (success){
            Snackbar.make( loginButton.getRootView(),"success", Snackbar.LENGTH_SHORT).show();
           navController.navigate(R.id.action_loginFragment_to_homeFragment);
        }else {
            Snackbar.make( loginButton.getRootView(),errorMessage, Snackbar.LENGTH_SHORT).show();
        }
    }
}