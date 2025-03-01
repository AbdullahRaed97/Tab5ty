package com.example.tabty.utilities;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FirestoreManagement {
    public static void dataBackUp(MealsRepository mealRepo , PlannedMealRepository plannedMealRepository , ProfileView myView){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        //Favourite Meal Backup
        firestore.collection("Users")
                .document(userID)
                .collection("FavouriteMeal")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                            Map<String,Object> mealMap =document.getData();
                                MealEntity myMeal = new MealEntity();
                                myMeal.setIdMeal(mealMap.get("mealId").toString());
                                myMeal.setStrMeal(mealMap.get("mealName").toString());
                                myMeal.setStrMealThumb(mealMap.get("mealThump").toString());
                                myMeal.setStrArea(mealMap.get("mealArea").toString());
                                myMeal.setStrCategory(mealMap.get("mealCategory").toString());
                                myMeal.setStrYoutube(mealMap.get("mealVideo").toString());
                                myMeal.setStrInstructions(mealMap.get("mealInstructions").toString());
                                Log.i("TAG", "onSuccess: "+myMeal.toString());
                                mealRepo.insertLocalMeal(myMeal).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                ()->{},error-> Log.i("TAG", "onSuccess: "+error.getLocalizedMessage())
                                        );
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
                .collection("PlannedMeal")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                            Map<String,Object> mealMap = document.getData();
                                PlannedMeal myMeal = new PlannedMeal();
                                myMeal.setIdMeal(mealMap.get("mealId").toString());
                                myMeal.setStrMeal(mealMap.get("mealName").toString());
                                myMeal.setStrMealThumb(mealMap.get("mealThump").toString());
                                myMeal.setStrArea(mealMap.get("mealArea").toString());
                                myMeal.setStrCategory(mealMap.get("mealCategory").toString());
                                Log.d("TAG", "onSuccess: "+mealMap.get("mealLocalDate"));
                                myMeal.setMealDate(getLocalDate((Map<String, Object>) mealMap.get("mealLocalDate")));
                                myMeal.setStrInstructions(mealMap.get("mealInstructions").toString());
                                Log.i("TAG", "onSuccess: "+myMeal.toString());
                                plannedMealRepository.insertLocalPlannedMeal(myMeal)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                ()->{},error-> Log.i("TAG", "onSuccess: "+error.getLocalizedMessage())
                                        );
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
    private static LocalDate getLocalDate(Map<String,Object> obj){
        long day = (long) obj.get("dayOfMonth");
        long year = (long) obj.get("year");
        long month = (long) obj.get("monthValue");
        return LocalDate.of((int) year,(int) month, (int)day);
    }
    public static void insertFavMealInFirestore(MealEntity meal){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();
        Map<String,Object> mealMap = new HashMap<>();
        mealMap.put("mealId",meal.getIdMeal());
        mealMap.put("mealName",meal.getStrMeal());
        mealMap.put("mealCategory", meal.getStrCategory());
        mealMap.put("mealArea",meal.getStrArea());
        mealMap.put("mealThump",meal.getStrMealThumb());
        mealMap.put("mealVideo",meal.getStrYoutube());
        mealMap.put("mealInstructions",meal.getStrInstructions());
        firestore.collection("Users")
                .document(userID)
                .collection("FavouriteMeal")
                .whereEqualTo("mealId",meal.getIdMeal())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){
                            firestore.collection("Users")
                                    .document(userID)
                                    .collection("FavouriteMeal")
                                    .document(meal.getIdMeal())
                                    .set(mealMap)
                                    .addOnSuccessListener(d->{
                                        Log.i("TAG", "onSuccess: meal inserted successfully in firestore");
                                    });
                        }else {
                            Log.i("TAG", "onSuccess: meal is already exist");
                        }
                    }
                }).addOnFailureListener(d->{
                    Log.i("TAG", "insertMealInFirestore: failed to insert meal  ");
                });
    }
    public static void deleteMealFromFirestore(MealEntity meal){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();
        firestore.collection("Users")
                .document(userID)
                .collection("FavouriteMeal")
                .document(meal.getIdMeal())
                .delete()
                .addOnSuccessListener(d->{
                    Log.i("TAG", "deleteMealFromFirestore: meal deleted successfully from firestore");
                })
                .addOnFailureListener(d->{
                    Log.i("TAG", "deleteMealFromFirestore: meal cannot be deleted from firestore ");
                });
    }
    public static void deletePlannedMealFromFirestore(PlannedMeal meal){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();
        firestore.collection("Users")
                .document(userID)
                .collection("PlannedMeal")
                .document(meal.getIdMeal())
                .delete()
                .addOnSuccessListener(d->{
                    Log.i("TAG", "deleteMealFromFirestore: meal deleted successfully from firestore"+meal.getIdMeal());
                })
                .addOnFailureListener(d->{
                    Log.i("TAG", "deleteMealFromFirestore: meal cannot be deleted from firestore ");
                });
    }

    public static void insertPlannedMealInFirestore(PlannedMeal meal){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userID = user.getUid();
        Map<String,Object> mealMap = new HashMap<>();
        mealMap.put("mealId",meal.getIdMeal());
        mealMap.put("mealName",meal.getStrMeal());
        mealMap.put("mealCategory", meal.getStrCategory());
        mealMap.put("mealArea",meal.getStrArea());
        mealMap.put("mealThump",meal.getStrMealThumb());
        mealMap.put("mealInstructions",meal.getStrInstructions());
        mealMap.put("mealLocalDate",meal.getMealDate());
        firestore.collection("Users")
                .document(userID)
                .collection("PlannedMeal")
                .whereEqualTo("mealId",meal.getIdMeal())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.isEmpty()){
                            firestore.collection("Users")
                                    .document(userID)
                                    .collection("PlannedMeal")
                                    .document(meal.getIdMeal())
                                    .set(mealMap)
                                    .addOnSuccessListener(d->{
                                        Log.i("TAG", "onSuccess: meal inserted successfully in firestore");
                                    });
                        }else {
                            Log.i("TAG", "onSuccess: meal is already exist");
                        }
                    }
                }).addOnFailureListener(d->{
                    Log.i("TAG", "insertMealInFirestore: failed to insert meal  ");
                });
    }
}
