package com.example.tabty.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.tabty.favourite.view.FavouriteView;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.MealEntity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritePresenter {

    MealsRepository myRepo;
    FavouriteView myView;
    public FavouritePresenter(MealsRepository myRepo,FavouriteView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
    public void deleteLocalMeal(MealEntity meal){
        myRepo.deleteLocalMeal(meal).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //do-nothing
            }

            @Override
            public void onComplete() {
                myView.onDeleteMealSuccess("Meal deleted Successfully");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                myView.onDeleteMealFailure(e.getLocalizedMessage());
            }
        });
    }
    public void getAllLocalMeals(){
         myRepo.getAllLocalMeals().subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new SingleObserver<List<MealEntity>>() {
                     @Override
                     public void onSubscribe(@NonNull Disposable d) {
                         //Do-Nothing
                     }

                     @Override
                     public void onSuccess(@NonNull List<MealEntity> mealEntities) {
                         myView.onFavouriteMealListSuccess(mealEntities);
                     }

                     @Override
                     public void onError(@NonNull Throwable e) {
                        myView.onFavouriteMealListFailure(e.getLocalizedMessage());
                     }
                 });
    }
}
