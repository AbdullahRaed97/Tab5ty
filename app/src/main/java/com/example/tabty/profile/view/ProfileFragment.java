package com.example.tabty.profile.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tabty.R;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.db.PlannedMealLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.profile.presenter.ProfilePresenter;
import com.example.tabty.utilities.FirebaseManagement;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;


public class ProfileFragment extends Fragment implements ProfileView {

    ImageButton menuButton;
    Button logoutButton;
    Button goToFavBtn;
    Button goToCalBtn;
    NavController navController;
    ProfilePresenter presenter;
    Button backUpBtn;
    View myView;
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
        backUpBtn = view.findViewById(R.id.backUpBtn);
        myView=view;
        navController = Navigation.findNavController(view);

        presenter = new ProfilePresenter(this,
                MealsRepository.getInstance(MealRemoteDataSource.getInstance()
                ,new MealsLocalDataSource(requireContext()))
        ,PlannedMealRepository.getInstance(new PlannedMealLocalDataSource(requireContext())));

        logoutButton.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        presenter.logout();
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

        backUpBtn.setOnClickListener(v->{
            presenter.dataBackup();
        });

        menuButton.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }

    @Override
    public void onSyncDataSuccess(String success) {
        Snackbar.make(myView,success,Snackbar.LENGTH_SHORT).show();
        Log.i("TAG", "onSyncDataSuccess: "+success);
    }

    @Override
    public void onSyncDataFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
        Log.i("TAG", "onSyncDataFailure: "+errorMessage);
    }

    @Override
    public void onBackUpSuccess(String success) {
        Snackbar.make(myView,success,Snackbar.LENGTH_SHORT).show();
        Log.i("TAG", "onBackUpSuccess: "+success);
    }

    @Override
    public void onBackUpFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
        Log.i("TAG", "onBackUpFailure: "+errorMessage);
    }
}