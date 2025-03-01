package com.example.tabty.search.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.network.POJOs.Category;
import com.example.tabty.model.network.POJOs.Country;
import com.example.tabty.model.network.POJOs.Ingredient;
import com.example.tabty.search.view.SearchAdapter;
import com.example.tabty.search.view.SearchView;

import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    private MealsRepository myRepo;
    private SearchView myView;

    public SearchPresenter(MealsRepository myRepo , SearchView myView){
        this.myRepo=myRepo;
        this.myView=myView;
    }
    @SuppressLint("CheckResult")
    public void getAllIngredients(){
        myRepo.getAllIngredients().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item -> item.getIngredients())
                .subscribe(item ->myView.showAllIngredients(item),
                        error-> Log.i("TAG", "getRemoteAllMealsByFirstLetter: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
   public void getAllCountries(){
        myRepo.getAllCountries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item -> item.getCountries())
                .subscribe(item -> myView.showAllCountries(item),
                        error-> Log.i("TAG", "getRemoteAllMealsByFirstLetter: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
   public void getAllCategories(){
        myRepo.getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(item -> item.getCategories())
                .subscribe(item -> myView.showAllCategories(item),
                        error-> Log.i("TAG", "getRemoteAllMealsByFirstLetter: "+error.getLocalizedMessage()));
    }
    @SuppressLint("CheckResult")
    public void observeOnIngredient(List<Ingredient> ingredients , CharSequence s, SearchAdapter adapter){
        Observable<Ingredient> myObservable = Observable.fromIterable(ingredients);
        myObservable.filter(item-> item.getStrIngredient().toLowerCase().startsWith(s.toString()))
                .toList()
                .subscribe(item->{
                    adapter.setIngredients(item);
                });
    }
    @SuppressLint("CheckResult")
    public void observeOnCountries(List<Country> countries , CharSequence s, SearchAdapter adapter){
        Observable<Country> myObservable = Observable.fromIterable(countries);
        myObservable.filter(item-> item.getStrArea().toLowerCase().startsWith(s.toString()))
                .toList()
                .subscribe(item->{
                    adapter.setCountries(item);
                });
    }
    @SuppressLint("CheckResult")
    public void observeOnCategories(List<Category> categories , CharSequence s, SearchAdapter adapter){
        Observable<Category> myObservable = Observable.fromIterable(categories);
        myObservable.filter(item-> item.getStrCategory().toLowerCase().startsWith(s.toString()))
                .toList()
                .subscribe(item->{
                    adapter.setCategories(item);
                });
    }
}
