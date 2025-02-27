package com.example.tabty.common.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.tabty.common.view.MainView;
import com.example.tabty.utilities.NetworkConnectivityObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter {
    MainView myView;

    public MainPresenter(MainView myView){
        this.myView = myView;
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
                });
    }
    public void setSharedPreferencesValues(SharedPreferences sharedPreferences){
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putBoolean("isGuest",false);
        myEditor.apply();
    }
    public boolean isGuestMode(SharedPreferences sharedPreferences){
        return sharedPreferences.getBoolean("isGuest",false);
    }
}
