package com.doinWondrs.betterme.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.doinWondrs.betterme.R;
import com.doinWondrs.betterme.helpers.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutPageSecond extends AppCompatActivity {
    public static String typeWorkoutString;
    public TypesOfWorkouts workoutsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page_second);


        //declarations
        navGoTo();//creates bottomnavbar
        typesOfWorkouts();//sets workout types
    }//END: OnCreate


    //pull all the extra's

    public void typesOfWorkouts()
    {
        //get intents extra's here
        Intent intent = getIntent();
        typeWorkoutString = intent.getStringExtra(typeWorkoutString);
        //set title
        TextView workoutTitle = findViewById(R.id.textViewTest);
        workoutTitle.setText(typeWorkoutString);

        //set workouts to object for later
        workoutsHelper = new TypesOfWorkouts(typeWorkoutString);

        //use workout helper to get reps, sets, strings

        //pass onto xml to view new stuff by using .set...


    }//END: typesWOrkouts

    public void navGoTo() {
        //initialize, instantiate
        //NavigationBarView navigationBarView;//TODO: new way to do nav's but more research needed
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //set home selected:workout_nav
        bottomNavigationView.setSelectedItemId(R.id.workouts_nav);

        //perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_nav:
                        startActivity
                                (new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.workouts_nav:
                        //we are here right now
                        break;
                    case R.id.settings_nav:
                        startActivity
                                (new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    default:
                        return false;// this is to cover all other cases if not working properly
                }

                return true;
            }//end method: onNavItemSelected
        });//end lambda: bottomNavview
    }//end method: navGOTO
}//END: class
