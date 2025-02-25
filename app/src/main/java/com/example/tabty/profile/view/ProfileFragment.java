package com.example.tabty.profile.view;

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
import android.widget.ImageButton;

import com.example.tabty.R;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class ProfileFragment extends Fragment {

    ImageButton menuButton;
    Button logoutButton;
    Button goToFavBtn;
    Button goToCalBtn;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuButton = view.findViewById(R.id.profileMenuBtn);
        logoutButton = view.findViewById(R.id.logoutBtn);
        goToFavBtn = view.findViewById(R.id.goToFavBtn);
        goToCalBtn = view.findViewById(R.id.goToCalBtn);
        navController = Navigation.findNavController(view);

        logoutButton.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        FirebaseManagement.logoutFromFirebase();
                        navController.navigate(R.id.action_global_loginFragment);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {

                    })
                    .show();
        });

        goToFavBtn.setOnClickListener(v->{
            navController.navigate(R.id.action_global_favouriteFragment3);
        });

        goToCalBtn.setOnClickListener(v->{
            navController.navigate(R.id.action_global_calendarFragment);
        });
    }
}