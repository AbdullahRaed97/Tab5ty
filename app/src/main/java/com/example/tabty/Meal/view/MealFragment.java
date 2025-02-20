package com.example.tabty.Meal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabty.R;


public class MealFragment extends Fragment implements MealView{
ImageView mealImage;
ImageButton favBtn;
ImageButton calendarBtn;
TextView mealName;
TextView mealType;
ImageView countryFlag;
TextView instText;
WebView webView;
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
        webView=view.findViewById(R.id.webView);
    }
}