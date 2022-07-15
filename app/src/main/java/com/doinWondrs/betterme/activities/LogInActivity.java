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

public class LogInActivity extends AppCompatActivity {
    public final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginLoginBtn();

        goSignUpBtn();

    }


    public void loginLoginBtn() {
        Button loginBtn = findViewById(R.id.loginButton);
        Intent gettingIntent = getIntent();

        String email = gettingIntent.getStringExtra(VerifyUserActivity.TAG_VERIFY);
        EditText loginEmail = findViewById(R.id.loginTextEmailAddress);
        EditText loginPassword = findViewById(R.id.loginTextPassword);

        loginEmail.setText(email);

        loginBtn.setOnClickListener(v -> {
            String userEmail = loginEmail.getText().toString();
            String userPassword = loginPassword.getText().toString();

            Amplify.Auth.signIn(
                    userEmail,
                    userPassword,

                    success -> {
                        Log.i(TAG, "Successfully Logged-in");
                        Intent goHome = new Intent(LogInActivity.this, MainActivity.class);
                        startActivity(goHome);
                    },

                    fail -> {
                        Log.e(TAG, "Failed Login :" + fail);

                        runOnUiThread(() ->
                        {
                            Toast.makeText(LogInActivity.this, "LOGIN Failed!", Toast.LENGTH_SHORT);
                        });

                    }
            );
        });


    }


    public void goSignUpBtn() {
        Button signUpBtn = findViewById(R.id.loginSignUpButton);

        signUpBtn.setOnClickListener(v -> {
            Intent goSignup = new Intent(LogInActivity.this, SignUpActivity.class);

            startActivity(goSignup);
        });
    }


}