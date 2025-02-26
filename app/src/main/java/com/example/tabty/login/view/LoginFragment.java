package com.example.tabty.login.view;

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
import com.example.tabty.login.presenter.LoginPresenter;
import com.example.tabty.utilities.GoogleHelper;
import com.google.android.material.snackbar.Snackbar;


public class LoginFragment extends Fragment implements LoginView {

    private EditText emailText, passwordText;
    private Button loginButton;
    private TextView signupText;
    private NavController navController;
    private LoginPresenter presenter;
    private View myView;
    private Button googleButton;
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
        googleButton=view.findViewById(R.id.googleBtn);
        navController = Navigation.findNavController(view);
        presenter = new LoginPresenter(this,new GoogleHelper(this,this));
        myView = view;
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginAction(emailText.getText().toString(),passwordText.getText().toString());
            }
        });
        googleButton.setOnClickListener(v->{
            presenter.signInWithGoogle();
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
    public void onLoginSuccess(String message) {
        Snackbar.make(myView,message,Snackbar.LENGTH_SHORT).show();
        navController.navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onLoginFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpWithGoogleSuccess(String message) {
        Snackbar.make(myView,message,Snackbar.LENGTH_SHORT).show();
        navController.navigate(R.id.action_loginFragment_to_homeFragment);
    }

    @Override
    public void onSignUpWithGoogleFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }
}