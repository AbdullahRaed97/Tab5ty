package com.example.tabty.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabty.home.presenter.HomePresenter;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.R;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HomeFragment extends Fragment implements OnImageClickedListener ,HomeView{
    ImageButton menuButton;
    ImageView randomMeal_iv;
    TextView instructionsText;
    RecyclerView recyclerView;
    HomeAdapter myAdapter;
    View homeView;
    HomePresenter presenter;
    TextView randomMeal_title;
    MealsRepository myRepo;
    TextView home_Instruction_tv;
    Meal randoMealSent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeView=view;
        randomMeal_iv=view.findViewById(R.id.random_meal_iv);
        instructionsText=view.findViewById(R.id.home_instruction_tv);
        recyclerView=view.findViewById(R.id.home_recyclerView);
        randomMeal_title=view.findViewById(R.id.random_meal_tv);
        home_Instruction_tv=view.findViewById(R.id.home_instruction_tv);
        menuButton=view.findViewById(R.id.homeMenuButton);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        myRepo=  MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(getContext()) );
        presenter= new HomePresenter(myRepo,this);
        presenter.getRemoteAllMealsByFirstLetter("s");
        presenter.getRemoteRandomMeal();
        randomMeal_iv.setOnClickListener(v->{
            HomeFragmentDirections.ActionHomeFragmentToMealFragment2 action = HomeFragmentDirections.actionHomeFragmentToMealFragment2(randoMealSent.getIdMeal());
            Navigation.findNavController(homeView).navigate(action);
        });

        menuButton.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }

    @Override
    public void imageClickedAction(Meal meal) {
        Snackbar.make(homeView,"Image Clicked",Snackbar.LENGTH_SHORT).show();
        Meal sendMeal = meal;
        HomeFragmentDirections.ActionHomeFragmentToMealFragment2 action = HomeFragmentDirections.actionHomeFragmentToMealFragment2(sendMeal.getIdMeal());
        Navigation.findNavController(homeView).navigate(action);
    }

    @Override
    public void showAllMealsByFirstLetter(List<Meal> meals) {
        myAdapter= new HomeAdapter(getContext(),this,meals);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setData(meals);
    }

    @Override
    public void showAllMealsByFirstLetterError(String errorMessage) {
        Snackbar.make(homeView, errorMessage ,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showRandomMeal(Meal meal) {
        Glide.with(getContext()).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(500,500)).into(randomMeal_iv);
        randomMeal_title.setText(meal.getStrMeal());
        home_Instruction_tv.setText(meal.getStrInstructions());
        randoMealSent = meal;
    }

    @Override
    public void showRandomMealError(String errorMessage) {
        Snackbar.make(homeView, errorMessage ,Snackbar.LENGTH_SHORT).show();
    }
}