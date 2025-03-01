package com.example.tabty.calendar.view;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageButton;

import com.example.tabty.R;
import com.example.tabty.calendar.presenter.CalendarPresenter;
import com.example.tabty.model.PlannedMealRepository;
import com.example.tabty.model.db.PlannedMeal;
import com.example.tabty.model.db.PlannedMealLocalDataSource;
import com.example.tabty.utilities.Utilities;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements OnCalendarItemClickListener, com.example.tabty.calendar.view.CalendarView {
    CalendarView calendarView;
    CalendarPresenter presenter;
    PlannedMealRepository myRepo;
    View myView;
    RecyclerView recyclerView;
    CalendarAdapter myAdapter;
    ImageButton calMenuBtn;
    NavController navController;
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
        calMenuBtn = view.findViewById(R.id.calMenuBtn);
        myView=view;

        navController = Navigation.findNavController(view);

        myAdapter = new CalendarAdapter(requireContext(),new ArrayList<>(),this);
        myRepo = PlannedMealRepository.getInstance(new PlannedMealLocalDataSource(requireContext()));
        presenter = new CalendarPresenter(myRepo,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                presenter.getAllPlannedMealsByDate(LocalDate.of(year,month,dayOfMonth));
                Log.i("TAG", "onSelectedDayChange: "+selectedDate.getTime());
            }
        });

        calMenuBtn.setOnClickListener(v->{
            Utilities.openDrawer(requireActivity());
        });
    }
    @Override
    public void onDeleteClickAction(PlannedMeal meal) {
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
    public void onCalendarItemClickListener(String mealID) {
        CalendarFragmentDirections.ActionCalendarFragmentToMealFragment action =
                CalendarFragmentDirections.actionCalendarFragmentToMealFragment(mealID,true);
        navController.navigate(action);
    }

    @Override
    public void onPlannedMealListSuccess(List<PlannedMeal> plannedMeals) {
        myAdapter.setData(plannedMeals);
    }

    @Override
    public void onPlannedMealFailure(String errorMessage) {
        Snackbar.make(myView,errorMessage,Snackbar.LENGTH_SHORT).show();
    }
}