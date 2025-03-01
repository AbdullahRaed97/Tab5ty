package com.example.tabty.signup.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tabty.R;
import com.example.tabty.signup.presenter.SignUpPresenter;
import com.google.android.material.snackbar.Snackbar;


public class SignUpFragment extends Fragment implements SignUpView {

    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private TextView loginText;
    private Button registerButton;
    private NavController navController;
    private SignUpPresenter presenter;
    private View myView;
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
        myView = view;
        presenter=new SignUpPresenter(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerAction(emailText.getText().toString(),passwordText.getText().toString(),confirmPasswordText.getText().toString());
                }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });
    }

    @Override
    public void onEmailInvalid(String errorMessage) {
        emailText.setError(errorMessage);
    }

    @Override
    public void onPasswordLengthShort(String errorMessage) {
        passwordText.setError(errorMessage);
    }

    @Override
    public void passwordUnMatched(String errorMessage) {
        confirmPasswordText.setError(errorMessage);
    }

    @Override
    public void onRegisterSuccess(String message) {
        Snackbar.make(myView,message,Snackbar.LENGTH_SHORT).show();
        navController.navigate(R.id.action_signUpFragment_to_homeFragment);
    }

    @Override
    public void onRegisterFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }

}