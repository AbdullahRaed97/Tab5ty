package com.example.tabty.favourite.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.tabty.R;
import com.example.tabty.favourite.presenter.FavouritePresenter;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements OnFavItemClickListener ,FavouriteView{

    private FavouriteAdapter myAdapter;
    private FavouritePresenter presenter;
    private MealsRepository myRepo;
    private View myView;
    private RecyclerView recyclerView;
    private ImageButton favMenuBtn;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.favRecyclerView);
        favMenuBtn = view.findViewById(R.id.favMenuBtn);
        myView=view;

        navController= Navigation.findNavController(view);

        myRepo =MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(getContext()));
        presenter = new FavouritePresenter(myRepo,this);
        presenter.getAllLocalMeals();

        myAdapter = new FavouriteAdapter(getContext(),new ArrayList<>(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        favMenuBtn.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }

    @Override
    public void onDeleteClickAction(MealEntity meal) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete item")
                .setMessage("Are you sure you want to delete this meal ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    presenter.deleteLocalMeal(meal);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .show();
    }

    @Override
    public void onCardClickAction(String mealID) {
        FavouriteFragmentDirections.ActionFavouriteFragmentToMealFragment action =
                FavouriteFragmentDirections.actionFavouriteFragmentToMealFragment(mealID,true);
        navController.navigate(action);
    }

    @Override
    public void onFavouriteMealListSuccess(List<MealEntity> mealList) {
        myAdapter.setData(mealList);
    }

    @Override
    public void onFavouriteMealListFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteMealSuccess(String success) {
        Snackbar.make(myView,success,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteMealFailure(String fail) {
        Snackbar.make(myView,fail,Snackbar.LENGTH_SHORT).show();
    }
}