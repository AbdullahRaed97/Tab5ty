package com.example.tabty.profile.presenter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.profile.view.ProfileView;
import com.example.tabty.utilities.FirebaseManagement;
import com.example.tabty.utilities.FirestoreManagement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenter {
    private ProfileView myView;
    private MealsRepository mealRepo;
    private PlannedMealRepository plannedMealRepository;

    @SuppressLint("CheckResult")
    public ProfilePresenter(ProfileView myView, MealsRepository mealRepo,PlannedMealRepository plannedMealRepository) {
        this.myView = myView;
        this.mealRepo=mealRepo;
        this.plannedMealRepository = plannedMealRepository;

    }
    public void dataBackup(){
        FirestoreManagement.dataBackUp(mealRepo,plannedMealRepository,myView);
    }
    public void logout(){
        mealRepo.deleteAllMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        plannedMealRepository.deleteAllPlannedMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        FirebaseManagement.logoutFromFirebase();
    }
}
