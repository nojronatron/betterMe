package com.doinWondrs.betterme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.doinWondrs.betterme.R;

public class RecordDailyInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_daily_info);
        Intent callingIntent = getIntent();
        String date = "";
        if(callingIntent != null){
            date = callingIntent.getStringExtra(CalendarActivity.CALENDAR_DATE);
        }
    }
}