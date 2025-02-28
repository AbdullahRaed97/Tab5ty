package com.example.tabty.common.view;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.tabty.R;
import com.example.tabty.common.presenter.MainPresenter;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.db.PlannedMealLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MainView{
    private NavigationView navigationView;
    private DrawerLayout mainDrawer;
    private NavController navController;
    private MainPresenter presenter;
    private Dialog dialog;
    private final String SHARED_PPEF_NAME="ApplicationPreferences";
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDrawer = findViewById(R.id.mainDrawer);
        navigationView=findViewById(R.id.navView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);

        sharedPreferences=getSharedPreferences(SHARED_PPEF_NAME,MODE_PRIVATE);

        presenter= new MainPresenter(this
        , MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(this))
        , PlannedMealRepository.getInstance(new PlannedMealLocalDataSource(this)));

        presenter.checkNetworkConnectivity(this);
        presenter.setSharedPreferencesValues(sharedPreferences);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);

        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if (navController.getCurrentDestination().getId()==item.getItemId()){
                   mainDrawer.closeDrawer(GravityCompat.START);
                   return false;
               }
                int itemId = item.getItemId();
                if(itemId == R.id.menuLogout){
                    new MaterialAlertDialogBuilder(MainActivity.this)
                            .setTitle("Logout")
                            .setMessage("Are you sure you want to logout ?")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                presenter.logout();
                                navController.navigate(R.id.action_global_loginFragment);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {

                            })
                            .show();
                }else if(itemId == R.id.menuFavourite){
                    if(presenter.isGuestMode(MainActivity.sharedPreferences)){
                        new MaterialAlertDialogBuilder(MainActivity.this)
                                .setTitle("Authentication needed ! ")
                                .setMessage("Sorry you must login first to add meals to your Favourite list")
                                .setPositiveButton("Login", (dialog, which) -> {
                                    navController.navigate(R.id.action_global_loginFragment);
                                })
                                .setNegativeButton("Cancel", (dialog, which) -> {

                                })
                                .show();
                    }else {
                        navController.navigate(R.id.action_global_favouriteFragment3);
                    }
                }else if(itemId == R.id.menuCalendar ){
                    if(presenter.isGuestMode(MainActivity.sharedPreferences)){
                        new MaterialAlertDialogBuilder(MainActivity.this)
                                .setTitle("Authentication needed ! ")
                                .setMessage("Sorry you must login first to add meals to your Calendar list")
                                .setPositiveButton("Login", (dialog, which) -> {
                                    navController.navigate(R.id.action_global_loginFragment);
                                })
                                .setNegativeButton("Cancel", (dialog, which) -> {

                                })
                                .show();
                    }else {
                        navController.navigate(R.id.action_global_calendarFragment);
                    }
                }else if(itemId == R.id.menuProfile){
                    if(presenter.isGuestMode(MainActivity.sharedPreferences)){
                        new MaterialAlertDialogBuilder(MainActivity.this)
                                .setTitle("Authentication needed ! ")
                                .setMessage("Sorry you must login first to go to your profile")
                                .setPositiveButton("Login", (dialog, which) -> {
                                    navController.navigate(R.id.action_global_loginFragment);
                                })
                                .setNegativeButton("Cancel", (dialog, which) -> {

                                })
                                .show();
                    }else{
                        navController.navigate(R.id.action_global_profileFragment);
                    }
                }else if(itemId == R.id.menuHome){
                    navController.navigate(R.id.action_global_homeFragment);
                }else if(itemId == R.id.menuSearch){
                    navController.navigate(R.id.action_global_searchFragment);
                }
                mainDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        navigationView.bringToFront();
    }

    @Override
    public void onNetworkAvailable() {
        Log.i("TAG", "onNetworkAvailable: "+"Network is back");
        dialog.cancel();
    }

    @Override
    public void onNetworkLost() {
        Log.i("TAG", "onNetworkLost: "+ "Network lost please reconnect");
        dialog.show();
    }
}