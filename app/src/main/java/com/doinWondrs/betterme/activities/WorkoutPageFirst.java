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
        ImageButton hiitButton = this.findViewById(R.id.buttonHiit);

        //set event listener
        hiitButton.setOnClickListener(view -> {
            Intent hiitWorkoutFocus = new Intent(this, WorkoutPageSecond.class);
            //helper.gotoWorkouts(hiitWorkoutFocus);
            startActivity(hiitWorkoutFocus);
        });

    }
















    public void navGoTo()
    {
        //notes: https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
        //TODO: bottomnavbar is deprecated: https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView.OnNavigationItemSelectedListener

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