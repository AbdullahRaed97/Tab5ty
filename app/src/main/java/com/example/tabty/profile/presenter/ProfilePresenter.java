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
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private MealsRepository mealRepo;
    private PlannedMealRepository plannedMealRepository;
    private List<MealEntity> favMealList ;
    private List<PlannedMeal> plannedMeals;
    private FirebaseFirestore firestore;
    @SuppressLint("CheckResult")
    public ProfilePresenter(ProfileView myView, MealsRepository mealRepo,PlannedMealRepository plannedMealRepository) {
        this.myView = myView;
        this.mealRepo=mealRepo;
        this.plannedMealRepository = plannedMealRepository;
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        mealRepo.getAllLocalMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> favMealList = item);

        plannedMealRepository.getAllPlannedMeal().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> plannedMeals = item);
    }
    private List<Map<String,Object>> prepareListOfFavMeals(){
        List<Map<String, Object>> preparedList = new ArrayList<>();
        for(MealEntity meal : favMealList){
            Map<String,Object> mealMap = new HashMap<>();
            mealMap.put("mealId",meal.getIdMeal());
            mealMap.put("mealName",meal.getStrMeal());
            mealMap.put("mealCategory", meal.getStrCategory());
            mealMap.put("mealArea",meal.getStrArea());
            mealMap.put("mealThump",meal.getStrMealThumb());
            mealMap.put("mealVideo",meal.getStrYoutube());
            mealMap.put("mealInstructions",meal.getStrInstructions());
            preparedList.add(mealMap);
        }
        return preparedList;
    }
    private List<Map<String,Object>> prepareListOfPlannedMeals(){
        List<Map<String, Object>> preparedList = new ArrayList<>();
        for(PlannedMeal meal : plannedMeals){
            Map<String,Object> mealMap = new HashMap<>();
            mealMap.put("mealId",meal.getIdMeal());
            mealMap.put("mealName",meal.getStrMeal());
            mealMap.put("mealCategory", meal.getStrCategory());
            mealMap.put("mealArea",meal.getStrArea());
            mealMap.put("mealThump",meal.getStrMealThumb());
            mealMap.put("mealInstructions",meal.getStrInstructions());
            mealMap.put("mealLocalDate",meal.getMealDate());
            preparedList.add(mealMap);
        }
        return preparedList;
    }
    public void syncData(){
        String userID = user.getUid();
        //Adding Favourite Meal Document
            firestore.collection("Users")
                    .document(userID)
                    .collection("FavouriteMeals")
                    .add(new HashMap<String,Object>(){{
                        put("FavouriteMeals",prepareListOfFavMeals());
            }})
                    .addOnSuccessListener(d->{
                        myView.onSyncDataSuccess("Your data has been synced successfully");
                    })
                    .addOnFailureListener(d->{
                        myView.onSyncDataFailure("Sync failed");
                    });
         //Adding Planned Meal Document
        firestore.collection("Users")
                .document(userID)
                .collection("PlannedMeals")
                .add(new HashMap<String,Object>(){{
                    put("PlannedMeals",prepareListOfPlannedMeals());
                }})
                .addOnSuccessListener(d->{
                    myView.onSyncDataSuccess("Your data has been synced successfully");
                })
                .addOnFailureListener(d->{
                    myView.onSyncDataFailure("Sync failed");
                });
    }

    public void dataBackUp(){
        String userID = user.getUid();

        //Favourite Meal Backup
        firestore.collection("Users")
                .document(userID)
                .collection("FavouriteMeals")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                            List<Map<String,Object>> mealMap = (List<Map<String, Object>>) document.get("FavouriteMeals");
                            for(Map<String,Object> meal : mealMap){
                                MealEntity myMeal = new MealEntity();
                                myMeal.setIdMeal(meal.get("mealId").toString());
                                myMeal.setStrMeal(meal.get("mealName").toString());
                                myMeal.setStrMealThumb(meal.get("mealThump").toString());
                                myMeal.setStrArea(meal.get("mealArea").toString());
                                myMeal.setStrCategory(meal.get("mealCategory").toString());
                                myMeal.setStrYoutube(meal.get("mealVideo").toString());
                                myMeal.setStrInstructions(meal.get("mealInstructions").toString());
                                Log.i("TAG", "onSuccess: "+myMeal.toString());
                                mealRepo.insertLocalMeal(myMeal).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                ()->{},error-> Log.i("TAG", "onSuccess: "+error.getLocalizedMessage())
                                        );
                            }
                        }
                        myView.onBackUpSuccess("Backup Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        myView.onBackUpSuccess(e.getLocalizedMessage());
                    }
                });

        //Planned Meals Backup
        firestore.collection("Users")
                .document(userID)
                .collection("PlannedMeals")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                            List<Map<String,Object>> mealMap = (List<Map<String, Object>>) document.get("PlannedMeals");
                            for(Map<String,Object> meal : mealMap){
                                PlannedMeal myMeal = new PlannedMeal();
                                myMeal.setIdMeal(meal.get("mealId").toString());
                                myMeal.setStrMeal(meal.get("mealName").toString());
                                myMeal.setStrMealThumb(meal.get("mealThump").toString());
                                myMeal.setStrArea(meal.get("mealArea").toString());
                                myMeal.setStrCategory(meal.get("mealCategory").toString());
                                Log.d("TAG", "onSuccess: "+meal.get("mealLocalDate"));
                               myMeal.setMealDate(getLocalDate((Map<String, Object>) meal.get("mealLocalDate")));
                                myMeal.setStrInstructions(meal.get("mealInstructions").toString());
                                Log.i("TAG", "onSuccess: "+myMeal.toString());
                                plannedMealRepository.insertLocalPlannedMeal(myMeal)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                ()->{},error-> Log.i("TAG", "onSuccess: "+error.getLocalizedMessage())
                                        );
                            }
                        }
                        myView.onBackUpSuccess("Backup Success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        myView.onBackUpFailure(e.getLocalizedMessage());
                    }
                });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate getLocalDate(Map<String,Object> obj){
            long day = (long) obj.get("dayOfMonth");
            long year = (long) obj.get("year");
            long month = (long) obj.get("monthValue");
            return LocalDate.of((int) year,(int) month, (int)day);
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
