package com.example.tabty.meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tabty.common.view.MainActivity;
import com.example.tabty.meal.presenter.MealPresenter;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.R;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MealFragment extends Fragment implements MealView {
    ImageView mealImage;
    ImageButton favBtn;
    ImageButton calendarBtn;
    TextView mealName;
    TextView mealType;
    ImageView countryFlag;
    TextView instText;
    View myView;
    RecyclerView recyclerView;
    IngredientsAdapter myAdapter;
    YouTubePlayerView youtubeView;
    MealPresenter presenter;
    NavController navController;
    Meal recievedMeal;
    ImageButton mealMenuBtn;
        private static final String TAG = "MealFragment";
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_meal, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mealImage=view.findViewById(R.id.meal_iv);
            favBtn=view.findViewById(R.id.mealFavButton);
            calendarBtn=view.findViewById(R.id.mealCalendarButton);
            mealName=view.findViewById(R.id.meal_mealName_tv);
            mealType=view.findViewById(R.id.mealType_tv);
            countryFlag=view.findViewById(R.id.countryFlag_iv);
            instText=view.findViewById(R.id.instructionsText);
            recyclerView=view.findViewById(R.id.ingredientRecyclerView);
            youtubeView=view.findViewById(R.id.youtubeView);
            mealMenuBtn=view.findViewById(R.id.mealMenuBtn);
            myView=view;
            navController= Navigation.findNavController(myView);
            MealsRepository myRepo = MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(getContext()));
            PlannedMealRepository plannedMealRepo = PlannedMealRepository.getInstance(new PlannedMealLocalDataSource(requireContext()));
            presenter = new MealPresenter(myRepo,plannedMealRepo,this);

            //receive clicked meal from home
            String mealID = MealFragmentArgs.fromBundle(getArguments()).getMealID();
            presenter.getMealByID(mealID);

            favBtn.setOnClickListener(v->{
                if(presenter.isGuestMode(MainActivity.sharedPreferences)) {
                    new MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Authentication needed ! ")
                            .setMessage("Sorry you must login first to add meals to your Favourite list")
                            .setPositiveButton("Login", (dialog, which) -> {
                                navController.navigate(R.id.action_global_loginFragment);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {

                            })
                            .show();
                }else{
                    MealEntity myMeal = new MealEntity(recievedMeal);
                    presenter.insertLocalMeal(myMeal);
                    Log.i(TAG, "onViewCreated: " + myMeal);
                }
            });

            calendarBtn.setOnClickListener(v->{
                if(presenter.isGuestMode(MainActivity.sharedPreferences)) {
                    new MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Authentication needed ! ")
                            .setMessage("Sorry you must login first to add meals to your Calendar list")
                            .setPositiveButton("Login", (dialog, which) -> {
                                navController.navigate(R.id.action_global_loginFragment);
                            })
                            .setNegativeButton("Cancel", (dialog, which) -> {

                            })
                            .show();
                }else{
                    presenter.insertLocalPlannedMeal(recievedMeal, requireContext());
                }
            });

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);

            mealMenuBtn.setOnClickListener(v->{
                Utilities.openDrawer(requireActivity());
            });

        }

        @Override
        public void onInsertMealSuccess(String success) {
            Snackbar.make(myView,success,Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onInsertMealFailure(String errorMessage) {
            Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onInsertPlannedMealSuccess(String success) {
            Snackbar.make(myView,success,Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void OnInsertPlannedMealFailure(String errorMessage) {
            Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
        }

    @Override
    public void showMealByID(Meal meal) {
        recievedMeal = meal;
        mealName.setText(recievedMeal.getStrMeal());
        instText.setText(recievedMeal.getStrInstructions());
        Glide.with(getContext()).load(recievedMeal.getStrMealThumb())
                .apply(new RequestOptions().override(500,500)).into(mealImage);
        Glide.with(requireContext()).load("https://www.themealdb.com/images/icons/flags/big/64/"+ Utilities.getCountryNameCode(recievedMeal.getStrArea())+".png")
                .apply(new RequestOptions().override(500, 500)).into(countryFlag);
        mealType.setText(recievedMeal.getStrArea());
        getLifecycle().addObserver(youtubeView);
        myAdapter=new IngredientsAdapter(getContext(),recievedMeal.getIngredients(),recievedMeal.getMeasures());
        recyclerView.setAdapter(myAdapter);
        //Load The video
        youtubeView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                try{ Log.i("YOUTUBE", "onReady: " + recievedMeal.getStrYoutube());
                    String videoKey = presenter.getVideoKey(recievedMeal.getStrYoutube());
                    youTubePlayer.cueVideo(videoKey,0);}
                catch(Exception e){
                    Snackbar.make(myView,"Sorry this",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}