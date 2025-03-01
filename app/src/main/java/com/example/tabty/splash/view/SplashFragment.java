package com.example.tabty.splash.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tabty.R;
import com.example.tabty.splash.presenter.SplashPresenter;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment implements SplashView{
    private static final String TAG = "SplashFragment";
    private FirebaseUser user=null;
    private NavController navController;
    private SplashPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController=Navigation.findNavController(view);
        presenter = new SplashPresenter(this);
        presenter.navigate();
    }

    @Override
    public void navigateToSignUpFragment() {
        navController.navigate(R.id.action_splashFragment_to_signUpFragment);
    }

    @Override
    public void navigateToHomeFragment() {
        navController.navigate(R.id.action_splashFragment_to_homeFragment);
    }
}
