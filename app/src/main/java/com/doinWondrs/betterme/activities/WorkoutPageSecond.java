package com.doinWondrs.betterme.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.doinWondrs.betterme.R;
import com.doinWondrs.betterme.helpers.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkoutPageSecond extends AppCompatActivity {
    public static String typeWorkoutString;
    public TypesOfWorkouts workoutsHelper;
    public ArrayList<String> workoutName;
    private ArrayList<String> infoWorkouts;
    private ArrayList<String> workoutInfo;




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
        //use this to determine what workouts in helper
        typeWorkoutString = intent.getStringExtra(typeWorkoutString);

        //set workouts to object for later
        workoutsHelper = new TypesOfWorkouts(typeWorkoutString);

        //set arraylist for usage
        workoutName = new ArrayList<>();
        workoutInfo = new ArrayList<>();
        infoWorkouts = new ArrayList<>();

        /* ***********************
        *Logic for setting arraylist from helper class workouts
        * *******************/
        //use workout helper to get reps, sets, strings

        for(String key : workoutsHelper.workoutRoutine.keySet())
        {
            workoutName.add(key);
            workoutInfo.add(workoutsHelper.workoutRoutine.get(key));

        }

        //GET DATA from workouts helper class - pass iti to an array
        for(int i = 0 ; i < workoutInfo.size(); ++i)
        {
            for(String workoutInfoTemp : workoutInfo.get(i).split(","))
            {
                infoWorkouts.add(workoutInfoTemp);//has split data such as sets, reps, jpg, etc
            }
        }
        /* ******************
         * END: Logic for setting arraylist from helper class workouts
         ************* */

        //GET location from .xml file
        TextView workoutTitle = findViewById(R.id.textViewTest);
        ImageView workoutGif  = (ImageView) findViewById(R.id.workoutGif);
        ImageView workoutGif2 = (ImageView) findViewById(R.id.workoutGif2);
        ImageView workoutGif3 = (ImageView) findViewById(R.id.workoutGif3);

        //GET gifs
        int gifLocation1 = workoutsHelper.gif1;
        int gifLocation2 = workoutsHelper.gif2;
        int gifLocation3 = workoutsHelper.gif3;

        //GET values from helper - to place to .xml

        //first workout data
        String workoutInstructions1 = infoWorkouts.get(0);
        String workoutLocation1     = infoWorkouts.get(1);
        String workoutOtherInfo1    = infoWorkouts.get(2);

        //second workout data
        String workoutInstructions2 = infoWorkouts.get(3);
        String workoutLocation2     = infoWorkouts.get(4);
        String workoutOtherInfo2    = infoWorkouts.get(5);

        //third workout data
        String workoutInstructions3 = infoWorkouts.get(6);
        String workoutLocation3     = infoWorkouts.get(7);
        String workoutOtherInfo3    = infoWorkouts.get(8);

        //SET title
        workoutTitle.setText(typeWorkoutString);
        //SET Text View

        //TODO: @JWilsonHub

        //SET imageView
        workoutGif.setImageResource(gifLocation1);
        workoutGif2.setImageResource(gifLocation2);
        workoutGif3.setImageResource(gifLocation3);












        //TextView workout1TextView
//        TextView workoutNameOne = findViewById(R.id.workoutOneName);
//        workoutTitle.setText(workoutName.get(0));





    }//END: typesWorkouts

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
