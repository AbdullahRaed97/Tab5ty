package com.example.tabty.meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.tabty.meal.presenter.MealPresenter;
import com.example.tabty.model.db.Meal;
import com.example.tabty.model.db.MealEntity;
import com.example.tabty.model.db.MealsLocalDataSource;
import com.example.tabty.model.MealsRepository;
import com.example.tabty.model.network.MealRemoteDataSource;
import com.example.tabty.R;
import com.google.android.material.snackbar.Snackbar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MealFragment extends Fragment {
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
MealEntity myMeal;
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
        myView=view;
        MealsRepository myRepo = MealsRepository.getInstance(MealRemoteDataSource.getInstance(),new MealsLocalDataSource(getContext()));
        presenter = new MealPresenter(myRepo);

        //receive clicked meal from home
        Meal recievedMeal = MealFragmentArgs.fromBundle(getArguments()).getMeal();
        myMeal=new MealEntity(recievedMeal);
        mealName.setText(recievedMeal.getStrMeal());
        instText.setText(recievedMeal.getStrInstructions());
        Glide.with(getContext()).load(recievedMeal.getStrMealThumb())
                .apply(new RequestOptions().override(500,500)).into(mealImage);
        mealType.setText(recievedMeal.getStrArea());
        getLifecycle().addObserver(youtubeView);

        //Load The video
        youtubeView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String videoKey = presenter.getVideoKey(recievedMeal.getStrYoutube());
                youTubePlayer.loadVideo(videoKey,0);
            }
        });

        favBtn.setOnClickListener(v->{
            Snackbar.make(myView,"Meal is Added to Favourite",Snackbar.LENGTH_SHORT).show();
            presenter.insertLocalMeal(myMeal);
            Log.i(TAG, "onViewCreated: "+myMeal);
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        myAdapter=new IngredientsAdapter(getContext(),recievedMeal.getIngredients(),recievedMeal.getMeasures());
        recyclerView.setAdapter(myAdapter);
    }
}