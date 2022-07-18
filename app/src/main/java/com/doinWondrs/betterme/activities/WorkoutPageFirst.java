package com.doinWondrs.betterme.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.doinWondrs.betterme.R;
import com.doinWondrs.betterme.helpers.GoToNav;
import com.google.android.material.bottomnavigation.BottomNavigationView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.doinWondrs.betterme.R;
        import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.navigation.NavigationBarView;

public class WorkoutPageFirst extends AppCompatActivity {
    //Field Declarations
    GoToNav helper = new GoToNav();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page_first);



        //function declarations:

        setUpAddImageButton();//clickable imagebuttons
        navGoTo();//Our Bottom Navigation
    }//end of on create



    // Makes the ImageViewbutton clickable to upload profile pic
    private void setUpAddImageButton(){
        ImageButton abdominalFocusButtonWeightlifting = this.findViewById(R.id.buttonWeightlifting);
        ImageButton workoutFocusButtonStrength = this.findViewById(R.id.buttonStrength);
        ImageButton workoutFocusButtonHiit = this.findViewById(R.id.buttonHiit);

        ImageButton workoutFocusButtonCrossfit = this.findViewById(R.id.buttonCrossfit);
        ImageButton workoutFocusButtonOlympic = this.findViewById(R.id.buttonOlympic);
        ImageButton workoutFocusButtonCalisthenics = this.findViewById(R.id.buttonCalisthenics);

        //1. Abdominal Focus
        //set event listener
        abdominalFocusButtonWeightlifting.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Abdominal Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

        //2. Setup Chest Focus
        //set event listener
        workoutFocusButtonStrength.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Chest Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

        //3. Setup Legs Focus
        //set event listener
        workoutFocusButtonHiit.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Legs Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

        //4. Setup Back Focus
        //set event listener
        workoutFocusButtonCrossfit.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Back Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

        //5. Setup Arms Focus
        //set event listener
        workoutFocusButtonOlympic.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Arms Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

        //6. Setup Shoulders Focus
        //set event listener
        workoutFocusButtonCalisthenics.setOnClickListener(view -> {
            Intent workoutFocus = new Intent(this, WorkoutPageSecond.class);
            //passing extras for workout - library for this information
            workoutFocus.putExtra(WorkoutPageSecond.typeWorkoutString, "Shoulders Focus");
            //call the startActivity
            startActivity(workoutFocus);
        });

    }//END: setupImagButton

//ON RESUME: next button should go back to second workout page with previous workouts
//TODO:  Implement OnResume() for the next button












    public void navGoTo()
    {
        //NOTES: https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
        //NOTES: bottomnavbar is deprecated: https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView.OnNavigationItemSelectedListener

        //initialize, instantiate
        NavigationBarView navigationBarView;//new way to do nav's but more research needed
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //set home selected: home
        bottomNavigationView.setSelectedItemId(R.id.workouts_nav);
        //perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home_nav:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.calendar_nav:
                        startActivity(new Intent(getApplicationContext(), RecordWorkout.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.gps_nav:
                        startActivity(new Intent(getApplicationContext(), GPSAndGymLocation.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.workouts_nav:
                        //we are here
                        break;
                    case R.id.settings_nav:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(0,0);
                        break;
                    default: return false;// this is to cover all other cases if not working properly
                }

                return true;
            }
        });//end lambda: bottomNavview
    }//end method: navGoTo
}//end Class