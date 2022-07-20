package com.doinWondrs.betterme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.DailyInfo;
import com.amplifyframework.datastore.generated.model.User;
import com.doinWondrs.betterme.R;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RecordDailyInfo extends AppCompatActivity {
    private static final String TAG = "dailyinfoactivity";
    private CompletableFuture<User> userFuture;
    private User userInfo;
    private String date;
    private String userEmail = null;
    private String userNickName = null;
    private HashMap<String, DailyInfo> mapOfInfo;
    EditText weightInfo;
    TextView bmi;
    EditText currentCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_daily_info);
        mapOfInfo = new HashMap<>();

        getDailyInfo();
        getUserAttributes();
        getUser();
        calcBmi();
        grabDateAndSet();
        createOrUpdate();
    }

    private void getUserAttributes(){
        Amplify.Auth.fetchUserAttributes(
                success -> {
                    for(AuthUserAttribute attribute : success){
                        if(attribute.getKey().getKeyString().equals("email")){
                            userEmail = attribute.getValue();
                        }
                        if(attribute.getKey().getKeyString().equals("nickname")){
                            userNickName = attribute.getValue();
                        }
                    }
                },
                error -> Log.e(TAG, "failed")
        );
    }

    private void getUser(){
        userFuture = new CompletableFuture<>();
        Amplify.API.query(
                ModelQuery.list(User.class),
                success -> {
                    Log.i(TAG, "Read Users successfully");
                    for(User user : success.getData()){
                        if(user.getUsername().equals(userNickName)){
                            userFuture.complete(user);
                        }
                    }
                },
                failure -> Log.i(TAG,"Failed to read Users")
        );
        try {
            userInfo = userFuture.get();
        } catch (InterruptedException ie) {
            Log.e(TAG, "InterruptedException while getting product");
            Thread.currentThread().interrupt();
        } catch (ExecutionException ee) {
            Log.e(TAG, "ExecutionException while getting product");
        }
    }

    private void getDailyInfo(){
        // create API query
        Amplify.API.query(
                ModelQuery.list(DailyInfo.class),
                onSuccess -> {
                    for(DailyInfo info: onSuccess.getData()) {
                        if (!mapOfInfo.containsKey(info.getCalendarDate())) {
                            mapOfInfo.put(info.getCalendarDate(), info);
                        }
                    }
                    Log.i(TAG, "Read DailyInfo successfully.");
                },
                onFailure -> Log.e(TAG, "Failed to read DailyInfo.")
        );
    }

    private void createOrUpdate() {
        if (mapOfInfo.containsKey(date)) {
            // we have a dailyinfo
            infoUpdate();
        } else {
            infoCreate();
        }
    }

    private void infoUpdate(){
        Button saveBtn = findViewById(R.id.infoSaveBtn);
        String dateCreation = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
        String finalDate = date;

        // set values into UI textboxes
        weightInfo = findViewById(R.id.inforCurrentWeightInput);
        bmi = findViewById(R.id.infoBmiInput);
        currentCalories = findViewById(R.id.infoConsumedCalories);
        DailyInfo currentInfo = mapOfInfo.get(date);
        // TODO: getWeight can return null, maybe need a turnary or ??
        weightInfo.setText(currentInfo.getWeight());
        bmi.setText(currentInfo.getBmi());
        currentCalories.setText(currentInfo.getCurrentCalorie());

        saveBtn.setOnClickListener(v ->{
            weightInfo = findViewById(R.id.inforCurrentWeightInput);
            bmi = findViewById(R.id.infoBmiInput);
            currentCalories = findViewById(R.id.infoConsumedCalories);

            DailyInfo newDailyInfo = DailyInfo.builder()
                    .userId(userInfo.getId())
                    .user(userInfo)
                    .calendarDate(finalDate)
                    .weight(Integer.parseInt(weightInfo.getText().toString()))
                    .bmi(Integer.parseInt(bmi.getText().toString()))
                    .currentCalorie(Integer.parseInt(currentCalories.getText().toString()))
                    .dateCreated(new Temporal.DateTime(dateCreation))
                    .build();

            Amplify.API.mutate(
                    ModelMutation.update(newDailyInfo),
                    success -> {
                        Log.i(TAG, "Daily Info Updated Successfully");
                    },
                    failure -> Log.e(TAG, "Daily Info Creation Failed" + failure.getMessage())
            );

            Toast.makeText(RecordDailyInfo.this,
                    "Daily info was updated.",
                    Toast.LENGTH_SHORT).show();

        });
    }

    private void calcBmi(){
        Button calcBtn = findViewById(R.id.infoCalcBmiBtn);
        // TODO: User MUST have input their height and weight on their profile BEFORE running this
        calcBtn.setOnClickListener(v->{
            TextView bmi = findViewById(R.id.infoBmiInput);
            EditText weightInfo = findViewById(R.id.inforCurrentWeightInput);
            double weightNum = Double.parseDouble(weightInfo.getText().toString());
            int height = userInfo.getHeight();
            int userBmi = (int)((weightNum/(height * height)) * 703);
            bmi.setText(String.valueOf(userBmi));
        });

    }

    private void grabDateAndSet(){
        Intent callingIntent = getIntent();
        date = "";
        if(callingIntent != null){
            date = callingIntent.getStringExtra(CalendarActivity.CALENDAR_DATE);
        }
    }
    
    private void infoCreate(){
//        Intent callingIntent = getIntent();
//        date = "";
//        if(callingIntent != null){
//            date = callingIntent.getStringExtra(CalendarActivity.CALENDAR_DATE);
//        }
        Button saveBtn = findViewById(R.id.infoSaveBtn);
        String dateCreation = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
        String finalDate = date;
        saveBtn.setOnClickListener(v ->{
            EditText weightInfo = findViewById(R.id.inforCurrentWeightInput);
            TextView bmi = findViewById(R.id.infoBmiInput);
            currentCalories = findViewById(R.id.infoConsumedCalories);

            DailyInfo newDailyInfo = DailyInfo.builder()
                    .userId(userInfo.getId())
                    .user(userInfo)
                    .calendarDate(finalDate)
                    .weight(Integer.parseInt(weightInfo.getText().toString()))
                    .bmi(Integer.parseInt(bmi.getText().toString()))
                    .currentCalorie(Integer.parseInt(currentCalories.getText().toString()))
                    .dateCreated(new Temporal.DateTime(dateCreation))
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(newDailyInfo),
                    success -> Log.i(TAG, "Daily Info Created Successfully" + success),
                    failure -> Log.e(TAG, "Daily Info Creation Failed" + failure.getMessage())
            );

            Toast.makeText(RecordDailyInfo.this,
                    "Daily info was saved!",
                    Toast.LENGTH_SHORT).show();

        });
    }

}