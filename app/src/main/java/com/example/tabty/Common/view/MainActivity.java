package com.example.tabty.Common.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.tabty.R;
import com.example.tabty.utilities.FirebaseManagement;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout mainDrawer;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDrawer = findViewById(R.id.mainDrawer);
        navigationView=findViewById(R.id.navView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
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
                Log.i("TAG", "onNavigationItemSelected: "+itemId);
                if(itemId == R.id.menuLogout){
                    FirebaseManagement.logoutFromFirebase();
                    navController.navigate(R.id.action_global_loginFragment);
                }else if(itemId == R.id.menuFavourite){
                    navController.navigate(R.id.action_global_favouriteFragment3);
                }else if(itemId== R.id.menuCalendar ){
                    navController.navigate(R.id.action_global_calendarFragment);
                }else if(itemId== R.id.menuProfile){
                    navController.navigate(R.id.action_global_profileFragment);
                }else if(itemId== R.id.menuHome){
                    navController.navigate(R.id.action_global_homeFragment);
                }
                mainDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        navigationView.bringToFront();
    }
}