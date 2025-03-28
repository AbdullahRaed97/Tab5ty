package com.example.tabty.common.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import com.example.tabty.common.view.MainView;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.utilities.FirebaseManagement;
import com.example.tabty.utilities.NetworkConnectivityObserver;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {
    private MainView myView;
    private PlannedMealRepository plannedMealRepo;
    private MealsRepository mealsRepo;

    public MainPresenter(MainView myView ,MealsRepository mealsRepo , PlannedMealRepository plannedMealRepo){
        this.myView = myView;
        this.mealsRepo=mealsRepo;
        this.plannedMealRepo=plannedMealRepo;
    }

    @SuppressLint("CheckResult")
    public void checkNetworkConnectivity(Context context){
        NetworkConnectivityObserver.observeInternetConnectivity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connected ->{
                    if(connected){
                        myView.onNetworkAvailable();
                    }else{
                        myView.onNetworkLost();
                    }
                },error-> myView.onNetworkLost());
    }
    public void setSharedPreferencesValues(SharedPreferences sharedPreferences){
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putBoolean("isGuest",false);
        myEditor.putString("Date",null);
        myEditor.putString("Meal",null);
        myEditor.apply();
    }
    public boolean isGuestMode(SharedPreferences sharedPreferences){
        return sharedPreferences.getBoolean("isGuest",false);
    }
    public void logout(){
        mealsRepo.deleteAllMeals().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        plannedMealRepo.deleteAllPlannedMeal().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();;
        FirebaseManagement.logoutFromFirebase();
    }

//    public boolean isNetworkConnectedOnStart() {
//        networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
//        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
//    }
}
