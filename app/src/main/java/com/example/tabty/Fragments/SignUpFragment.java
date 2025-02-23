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


public class SignUpFragment extends Fragment implements FirebaseCallback {

    EditText emailText;
    EditText passwordText;
    EditText confirmPasswordText;
    TextView loginText;
    Button registerButton;
    NavController navController;
    private static final String TAG = "SignUpFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailText=view.findViewById(R.id.emailTextRegister);
        passwordText=view.findViewById(R.id.passwordTextRegister);
        confirmPasswordText=view.findViewById(R.id.confirmPasswordText);
        registerButton=view.findViewById(R.id.registerButton);
        loginText=view.findViewById(R.id.loginText);
        navController=Navigation.findNavController(view);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String pass = passwordText.getText().toString();
                String confirmPass = confirmPasswordText.getText().toString();
                boolean isValid =dataValidation(email,pass,confirmPass);
                if(isValid)
                    FirebaseManagement.createFirebaseAccount(email,pass,SignUpFragment.this);

                }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });
    }
    private boolean dataValidation(String email,String pass,String confirmPass) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Email is invalid");
            return false;
        }
        if(pass.length()<6){
            passwordText.setError("invalid password length");
            return false;
        }
        if(!confirmPass.equals(pass))
        {
            confirmPasswordText.setError("Un matched passwords");
            return false;
        }
        return true;
    }

    @Override
    public void onFirebaseResponse(boolean success, String errorMessage) {
        if (success){
            Snackbar.make(registerButton.getRootView(),"Registeration Success",Snackbar.LENGTH_SHORT).show();
            navController.navigate(R.id.action_signUpFragment_to_homeFragment);
        }else{
            Snackbar.make(registerButton.getRootView(),errorMessage,Snackbar.LENGTH_SHORT).show();
        }
    }
}