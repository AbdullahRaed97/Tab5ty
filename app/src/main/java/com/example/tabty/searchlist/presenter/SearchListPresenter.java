package com.example.tabty.searchlist.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.Meal;
import com.example.tabty.searchlist.view.SearchListAdapter;
import com.example.tabty.searchlist.view.SearchListView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchListPresenter {

    private MealsRepository myRepo;
    private SearchListView myView;
    public SearchListPresenter(MealsRepository myRepo, SearchListView myView) {
        this.myRepo = myRepo;
        this.myView = myView;
    }
    @SuppressLint("CheckResult")
    public void getAllMealsByIngredient(String ingredient){
        myRepo.getAllMealsByIngredient(ingredient).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item->item.getMeals())
                .subscribe(item->myView.showAllMealsByIngredient(item),
                        error-> Log.i("TAG", "getAllMealsByCategory: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
    public void getAllMealsByCategory(String category){
        myRepo.getAllMealsByCategory(category).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item->item.getMeals())
                .subscribe(item->myView.showAllMealsByCategory(item)
                ,error-> Log.i("TAG", "getAllMealsByCategory: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
    public void getAllMealsByCountry(String country){
        myRepo.getAllMealsByArea(country).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item->item.getMeals())
                .subscribe(item->myView.showAllMealsByCountry(item)
                        ,error-> Log.i("TAG", "getAllMealsByCategory: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
    public void observeOnMeals(List<Meal> meals , CharSequence s, SearchListAdapter adapter){
        Observable<Meal> myObservable = Observable.fromIterable(meals);
        myObservable.filter(item-> item.getStrMeal().toLowerCase().startsWith(s.toString()))
                .toList()
                .subscribe(item->{
                    adapter.setData(item);
                },error -> Log.i("TAG", "observeOnMeals: "+error.getLocalizedMessage()));
    }
}
