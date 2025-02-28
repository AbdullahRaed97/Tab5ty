package com.example.tabty.search.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tabty.R;
import com.example.tabty.home.view.HomeFragmentDirections;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.model.network.POJOs.Category;
import com.example.tabty.model.network.POJOs.Country;
import com.example.tabty.model.network.POJOs.Ingredient;
import com.example.tabty.search.presenter.SearchPresenter;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class SearchFragment extends Fragment implements SearchView ,OnImageClickListener {

    RecyclerView recyclerView;
    ChipGroup chipGroup;
    EditText searchBar;
    ImageButton menuButton;
    SearchPresenter presenter;
    SearchAdapter myAdapter;
    View myView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuButton=view.findViewById(R.id.searchMenuBtn);
        searchBar=view.findViewById(R.id.searchBar);
        chipGroup=view.findViewById(R.id.chipGroup);
        recyclerView=view.findViewById(R.id.searchRecyclerView);
        myView=view;

        MealsRepository myRepo = MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(requireContext()));
        presenter=new SearchPresenter(myRepo,this);
        presenter.getAllCategories();
        presenter.getAllCountries();
        presenter.getAllIngredients();

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        myAdapter = new SearchAdapter(requireContext(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),"",this);
        recyclerView.setAdapter(myAdapter);
        setupFilterChips();

        menuButton.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }

    @Override
    public void showAllIngredients(List<Ingredient> ingredients) {
        myAdapter.setIngredients(ingredients);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    presenter.observeOnIngredient(ingredients,s,myAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void showAllCountries(List<Country> countries) {
        myAdapter.setCountries(countries);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.observeOnCountries(countries,s,myAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void showAllCategories(List<Category> categories) {
       myAdapter.setCategories(categories);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.observeOnCategories(categories,s,myAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    void setupFilterChips()
    {
        for (int i=0 ; i< chipGroup.getChildCount() ; i++)
        {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        myAdapter.setType(chip.getText().toString());
                    }
                }
            });
        }
    }

    @Override
    public void onImageClickAction(String filter) {
        SearchFragmentDirections.ActionSearchFragmentToSearchListFragment action = SearchFragmentDirections.actionSearchFragmentToSearchListFragment(filter);
        Navigation.findNavController(myView).navigate(action);
    }
}