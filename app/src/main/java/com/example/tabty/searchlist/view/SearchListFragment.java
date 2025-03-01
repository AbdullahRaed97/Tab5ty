package com.example.tabty.searchlist.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tabty.R;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.searchlist.presenter.SearchListPresenter;
import com.example.tabty.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;


public class SearchListFragment extends Fragment implements SearchListView, OnImageClickListener {

   private String filter;
   private RecyclerView recyclerView;
   private SearchListPresenter presenter;
   private SearchListAdapter myAdapter;
   private EditText searchBar;
   private ImageButton menuButton;
   private List<Meal> meals;
   private NavController navController;
   private TextWatcher watcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.searchListRecyclerView);
        searchBar=view.findViewById(R.id.searchListSearchBar);
        menuButton=view.findViewById(R.id.searchListMenuBtn);
        navController= Navigation.findNavController(view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        presenter=new SearchListPresenter(MealsRepository.getInstance(MealRemoteDataSource.getInstance()
                ,new MealsLocalDataSource(requireContext())),this);

        filter = SearchListFragmentArgs.fromBundle(getArguments()).getFilter();

        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.observeOnMeals(meals, s, myAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        presenter.getAllMealsByCategory(filter);
        presenter.getAllMealsByCountry(filter);
        presenter.getAllMealsByIngredient(filter);

        myAdapter = new SearchListAdapter(requireContext(),new ArrayList<>(),this);
        recyclerView.setAdapter(myAdapter);

        menuButton.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }

    @Override
    public void showAllMealsByIngredient(List<Meal> meals) {
            this.meals=meals;
            myAdapter.setData(meals);

        searchBar.addTextChangedListener(watcher);
        Log.i("TAG", "showAllMealsByIngredient: "+meals.toString());
    }

    @Override
    public void showAllMealsByCategory(List<Meal> meals) {
            this.meals=meals;
            myAdapter.setData(meals);
        searchBar.addTextChangedListener(watcher);
        Log.i("TAG", "showAllMealsByCategory: "+meals.toString());
    }

    @Override
    public void showAllMealsByCountry(List<Meal> meals) {
            this.meals=meals;
            myAdapter.setData(meals);
        searchBar.addTextChangedListener(watcher);
        Log.i("TAG", "showAllMealsByCountry: "+meals.toString());
    }

    @Override
    public void onImageClickAction(String mealID) {
        SearchListFragmentDirections.ActionSearchListFragmentToMealFragment action =
                SearchListFragmentDirections.actionSearchListFragmentToMealFragment(mealID,false);
        navController.navigate(action);
    }
}