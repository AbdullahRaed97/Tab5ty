package com.example.tabty.favourite.view;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tabty.R;
import com.example.tabty.favourite.presenter.FavouritePresenter;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements OnDeleteClickListener {

    FavouriteAdapter myAdapter;
    FavouritePresenter presenter;
    MealsRepository myRepo;
    LiveData<List<MealEntity>> meals;
    View myView;
    Dialog myDialog;

    Button cancelButton;
    Button deleteButton;
    RecyclerView recyclerView;
    MealEntity myMeal;
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

        myDialog = new Dialog(requireContext());
        myDialog.setContentView(R.layout.custom_dialog);
        myDialog.setCancelable(false);
        cancelButton=myDialog.findViewById(R.id.dialogCancelBtn);
        deleteButton=myDialog.findViewById(R.id.dialogDeleteBtn);

        cancelButton.setOnClickListener(v->{
            myDialog.cancel();
        });
        deleteButton.setOnClickListener(v->{
            presenter.deleteLocalMeal(myMeal);
            Snackbar.make(myView,"Your meal has been deleted successfully",Snackbar.LENGTH_SHORT).show();
            myDialog.cancel();
        });

        myRepo=MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(getContext()));
        presenter = new FavouritePresenter(myRepo);
        meals= presenter.getAllLocalMeals();

        meals.observe(requireActivity(), new Observer<List<MealEntity>>() {
            @Override
            public void onChanged(List<MealEntity> mealEntities) {
                myAdapter.setData(mealEntities);
            }
        });

        myAdapter = new FavouriteAdapter(getContext(),new ArrayList<>(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onDeleteClickAction(MealEntity meal) {
        myMeal=meal;
        myDialog.show();
    }
}