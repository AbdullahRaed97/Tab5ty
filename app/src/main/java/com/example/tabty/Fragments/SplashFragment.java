package com.example.tabty.Fragments;

import static android.content.Context.MODE_PRIVATE;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.tabty.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment {
    private static final String TAG = "SplashFragment";
    Handler myHandler;
    SharedPreferences myPref;
    FirebaseUser user=null;
    boolean isFirst=true;
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
        user= FirebaseAuth.getInstance().getCurrentUser();
        //Log.i(TAG, "onViewCreated: "+user.getEmail());
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                    if(user==null) {
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_signUpFragment);
                    } else{
                        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment);
                    }
                }
            }
        , 4000L);
    }
}
