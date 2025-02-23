package com.example.tabty.calendar.view;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.tabty.R;
import com.example.tabty.calendar.presenter.CalendarPresenter;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements OnCalendarDeleteClickListener {
    CalendarView calendarView;
    CalendarPresenter presenter;
    PlannedMealRepository myRepo;
    View myView;
    RecyclerView recyclerView;
    CalendarAdapter myAdapter;
    LiveData<List<PlannedMeal>> plannedMeals;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.calRecyclerView);
        calendarView = view.findViewById(R.id.calendarView);
        myView=view;
        myAdapter = new CalendarAdapter(requireContext(),new ArrayList<>(),this);
        myRepo = PlannedMealRepository.getInstance(new PlannedMealLocalDataSource(requireContext()));
        presenter = new CalendarPresenter(myRepo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                plannedMeals = presenter.getAllPlannedMealsByDate(LocalDate.of(year,month,dayOfMonth));
                Log.i("TAG", "onSelectedDayChange: "+selectedDate.getTime());
                Log.i("TAG", "onSelectedDayChange: "+plannedMeals);
                plannedMeals.observe(requireActivity(), new Observer<List<PlannedMeal>>() {
                    @Override
                    public void onChanged(List<PlannedMeal> plannedMeals) {
                        Log.i("TAG", "onChanged: "+plannedMeals.toString());
                        myAdapter.setData(plannedMeals);
                    }
                });
            }
        });
    }
    @Override
    public void onDeleteClickAction(PlannedMeal meal) {
        presenter.deleteLocalMeal(meal);
    }
}