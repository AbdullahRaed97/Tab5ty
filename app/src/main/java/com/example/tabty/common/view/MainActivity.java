package com.example.tabty.common.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.tabty.R;
import com.example.tabty.common.presenter.MainPresenter;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements MainView{
    NavigationView navigationView;
    DrawerLayout mainDrawer;
    NavController navController;
    MainPresenter presenter;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDrawer = findViewById(R.id.mainDrawer);
        navigationView=findViewById(R.id.navView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);

        presenter= new MainPresenter(this);
        presenter.checkNetworkConnectivity(this);

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
                                FirebaseManagement.logoutFromFirebase();
                                navController.navigate(R.id.action_global_loginFragment);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {

                            })
                            .show();
                }else if(itemId == R.id.menuFavourite){
                    navController.navigate(R.id.action_global_favouriteFragment3);
                }else if(itemId == R.id.menuCalendar ){
                    navController.navigate(R.id.action_global_calendarFragment);
                }else if(itemId == R.id.menuProfile){
                    navController.navigate(R.id.action_global_profileFragment);
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