package com.doinWondrs.betterme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.doinWondrs.betterme.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO: Use current auth user to Pull "nickname/username" and save User to use
}