package com.example.tabty.splash.presenter;

import com.example.tabty.splash.view.SplashView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.Handler;
import android.os.Looper;


public class SplashPresenter {
    SplashView myView;
    Handler myHandler;
    FirebaseUser user;
    public SplashPresenter(SplashView myView){
        this.myView=myView;
        myHandler = new Handler();
    }
 public void navigate() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        myHandler.postDelayed(new Runnable(){
           @Override
           public void run() {
               if(user==null) {
                   myView.navigateToSignUpFragment();
               } else{
                   myView.navigateToHomeFragment();
               }
           }
        },4000);
    }
}
