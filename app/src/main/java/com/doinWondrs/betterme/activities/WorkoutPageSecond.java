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

import java.lang.reflect.Array;
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
        typeWorkoutString = intent.getStringExtra(typeWorkoutString);

        //set workouts to object for later
        workoutsHelper = new TypesOfWorkouts(typeWorkoutString);



        //use workout helper to get reps, sets, strings
        workoutName = new ArrayList<>();
        workoutInfo = new ArrayList<>();
        for(String key : workoutsHelper.workoutRoutine.keySet())
        {
            workoutName.add(key);
            workoutInfo.add(workoutsHelper.workoutRoutine.get(key));

        }

        //pass onto xml to view new stuff by using .set...
        infoWorkouts = new ArrayList<>();
        for(int i = 0 ; i < workoutInfo.size(); ++i)
        {
            for(String workoutInfoTemp : workoutInfo.get(i).split(","))
            {
                infoWorkouts.add(workoutInfoTemp);//has split data such as sets, reps, jpg, etc
            }
        }

        //set title
        TextView workoutTitle = findViewById(R.id.textViewTest);
        workoutTitle.setText(typeWorkoutString);

        ImageView chestTest = (ImageView) findViewById(R.id.workoutGif);
        //use helper class.this is how to get info, sets, reps
        //get from helper - this does work

        //String gifImage1 = infoWorkouts.get(2);
        //String gifImage2 = infoWorkouts.get(5);
        //String gifImage3 = infoWorkouts.get(8);
        int gifLocation1 = workoutsHelper.gif1;
        int gifLocation2 = workoutsHelper.gif2;
        int gifLocation3 = workoutsHelper.gif3;
        //set imageView
        chestTest.setImageResource(gifLocation2);



        //TextView workout1TextView
//        TextView workoutNameOne = findViewById(R.id.workoutOneName);
//        workoutTitle.setText(workoutName.get(0));





        workoutDescription2.setText(workoutInstructions2);
        workoutRoutine2.setText(workoutLocation2);

        workoutDescription3.setText(workoutInstructions3);
        workoutRoutine3.setText(workoutLocation3);

        //SET imageView
        workoutGif.setImageResource(gifLocation1);
        workoutGif2.setImageResource(gifLocation2);
        workoutGif3.setImageResource(gifLocation3);
        //added new comment but not important here


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
