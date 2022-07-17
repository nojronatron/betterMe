package com.doinWondrs.betterme.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.doinWondrs.betterme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;

        import com.doinWondrs.betterme.R;
        import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.navigation.NavigationBarView;

public class WorkoutPageFirst extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page_first);



        //function declarations:

        navGoTo();//Our Bottom Navigation
    }//end of on create


















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
}//end Class