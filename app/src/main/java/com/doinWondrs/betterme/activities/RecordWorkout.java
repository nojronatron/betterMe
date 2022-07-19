package com.doinWondrs.betterme.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.DailyInfo;
import com.amplifyframework.datastore.generated.model.User;
import com.doinWondrs.betterme.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordWorkout extends AppCompatActivity {
    private static final String TAG = "RecordWorkOut Activity";

    private TextView  Bmi;
    private EditText weightInput;
    private List<DailyInfo> infoHistory;
    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_workout);

        infoHistory = new ArrayList<>();

        setUpHistoryACTextView();
        createDailyInfo();
        calcBmi();




        navGoTo();
    }//END: onCreate

    //TODO: Create page to allow User to add workout and body Information
    //TODO: Takes information and saves as DailyInfo connected to current User

    public void navGoTo()
    {
        //notes: https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
        //TODO: bottomnavbar is deprecated: https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView.OnNavigationItemSelectedListener

        //initialize, instantiate
        NavigationBarView navigationBarView;//new way to do nav's but more research needed
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //set home selected: calendar
        bottomNavigationView.setSelectedItemId(R.id.calendar_nav);
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
                        //we are here right now
                        break;
                    case R.id.gps_nav:
                        startActivity(new Intent(getApplicationContext(), GPSAndGymLocation.class));
                        overridePendingTransition(0,0);
                        break;
                    case R.id.workouts_nav:
                        startActivity(new Intent(getApplicationContext(), WorkoutPageFirst.class));
                        overridePendingTransition(0,0);
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

    //    TODO: Grab DailyInfo from List and send information as Extras
    private void setUpHistoryACTextView() {
        ArrayAdapter<DailyInfo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,infoHistory);
        AutoCompleteTextView acList = (AutoCompleteTextView) findViewById(R.id.dailyInfoList);
        acList.setAdapter(adapter);
        acList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                Log.i("Record Workout","Position = " + position + id);
            }
        });


    }

    //    TODO: Needs User to save to its ID and connect it to.
    private void createDailyInfo()
    {
        Button addInfo = findViewById(R.id.saveDailyInfo);
        String currentDate = com.amazonaws.util.DateUtils.format("MM-dd-yyyy", new Date());

        addInfo.setOnClickListener(v -> {
            DailyInfo newDailyInfo = DailyInfo.builder()
                    .userId(currentUser.getId())
                    .weight(Integer.valueOf(weightInput.getText().toString()))
                    .bmi(Integer.valueOf(Bmi.getText().toString()))
                    .dateCreated( new Temporal.DateTime(currentDate))
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newDailyInfo),
                    success -> {
                        Toast.makeText(RecordWorkout.this, "DailyInfo Recorded ", Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Daily Workout Info created successfully");
                    },
                    failure -> Log.e(TAG,"Failed to create new daily Info" + failure.getMessage())
            );


        });
    }

    //    TODO: Make sure We have user to pull Height
    private void calcBmi() {

        Button calcBmiBtn = findViewById(R.id.calcBMIBtn);

        calcBmiBtn.setOnClickListener(v -> {

            Bmi = findViewById(R.id.BMIdisplay);
            weightInput = findViewById(R.id.weightInput);

            double weightNum = Double.parseDouble(weightInput.getText().toString());
            int height = 72;
//                    currentUser.getHeight();

            int userBmi = (int) ((weightNum / (height * height)) * 703);

            Bmi.setText( String.valueOf(userBmi));
        });
    }


}//END: class