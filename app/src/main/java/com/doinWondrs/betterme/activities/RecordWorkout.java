//package com.doinWondrs.betterme.activities;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.doinWondrs.betterme.activities.CalendarAdapter;
//import com.doinWondrs.betterme.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class RecordWorkout extends AppCompatActivity implements CalendarAdapter.OnItemListener {
//    private TextView monthYearText;
//    private RecyclerView calendarRecyclerView;
//    private LocalDate selectedDate;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initWidgets();
//        selectedDate = LocalDate.now();
//        setMonthView();
//    }
//
//    private void initWidgets() {
//        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
//        monthYearText = findViewById(R.id.monthYearTV);
//    }
//
//    private void setMonthView() {
//        monthYearText.setText(monthYearFromDate(selectedDate));
//        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
//
//        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
//        calendarRecyclerView.setLayoutManager(layoutManager);
//        calendarRecyclerView.setAdapter(calendarAdapter);
//    }
//
//    private ArrayList<String> daysInMonthArray(LocalDate date) {
//        ArrayList<String> daysInMonthArray = new ArrayList<>();
//        YearMonth yearMonth = YearMonth.from(date);
//
//        int daysInMonth = yearMonth.lengthOfMonth();
//
//        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
//        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
//
//        for (int i = 1; i <= 42; i++) {
//            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
//                daysInMonthArray.add("");
//            } else {
//                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
//            }
//        }
//        return daysInMonthArray;
//    }
//
//    private String monthYearFromDate(LocalDate date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
//        return date.format(formatter);
//    }
//
//    public void previousMonthAction(View view) {
//        selectedDate = selectedDate.minusMonths(1);
//        setMonthView();
//    }
//
//    public void nextMonthAction(View view) {
//        selectedDate = selectedDate.plusMonths(1);
//        setMonthView();
//    }
//
//    @Override
//    public void onItemClick(int position, String dayText) {
//        if (!dayText.equals("")) {
//            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
//            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void navGoTo() {
//        //notes: https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
//        //TODO: bottomnavbar is deprecated: https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView.OnNavigationItemSelectedListener
//
//        //initialize, instantiate
//        NavigationBarView navigationBarView;//new way to do nav's but more research needed
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        //set home selected: calendar
//        bottomNavigationView.setSelectedItemId(R.id.calendar_nav);
//        //perform item selected listener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home_nav:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        break;
//                    case R.id.calendar_nav:
//                        //we are here right now
//                        break;
//                    case R.id.gps_nav:
//                        startActivity(new Intent(getApplicationContext(), GPSAndGymLocation.class));
//                        overridePendingTransition(0, 0);
//                        break;
//                    case R.id.workouts_nav:
//                        startActivity(new Intent(getApplicationContext(), WorkoutPageFirst.class));
//                        overridePendingTransition(0, 0);
//                        break;
//                    case R.id.settings_nav:
//                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
//                        overridePendingTransition(0, 0);
//                        break;
//                    default:
//                        return false;// this is to cover all other cases if not working properly
//                }
//
//                return true;
//            }
//        });//end lambda: bottomNavview
//    }
//
//}