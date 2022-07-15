package com.doinWondrs.betterme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.doinWondrs.betterme.R;

public class VerifyUserActivity extends AppCompatActivity {
    String TAG = "VerifyUserActivity";
    static String TAG_VERIFY = "VerifyUserTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);

    }

    public void verifyBtn() {
        Button verifyBtn = findViewById(R.id.verifyButton);
        Intent gettingIntent = getIntent();
        String email = gettingIntent.getStringExtra(SignUpActivity.TAG_SIGNUP_EMAIL);
        EditText verifyEmail = findViewById(R.id.verifyTextEmailAddress);
        verifyEmail.setText(email);

        verifyBtn.setOnClickListener(v -> {
            String userEmail = verifyEmail.getText().toString();
            String verification = ((EditText) findViewById(R.id.verifyTextPassword)).getText().toString();

            Amplify.Auth.confirmSignUp(
                    userEmail,
                    verification,
                    success -> {
                        Log.i(TAG, "Verify successful " + success);
                        Intent goLogin = new Intent(VerifyUserActivity.this, LogInActivity.class);
                        startActivity(goLogin);
                    },
                    fail -> {
                        Log.e(TAG, "Failed Verify :" + fail);

                        runOnUiThread(() ->
                        {
                            Toast.makeText(VerifyUserActivity.this, "Verification Failed!", Toast.LENGTH_SHORT);
                        });

                    });
        });
    }
}