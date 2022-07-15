package com.doinWondrs.betterme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.doinWondrs.betterme.R;

public class SignUpActivity extends AppCompatActivity {
    public final String TAG = "SignUpActivity";
    static String TAG_SIGNUP_EMAIL = "Signup_Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupBtn();
    }

    public void signupBtn() {
        Button signupBtn = findViewById(R.id.signupButton);
        signupBtn.setOnClickListener(view -> {

            String email = ((EditText) findViewById(R.id.signupTextEmailAddress)).getText().toString();
            String password = ((EditText) findViewById(R.id.signupTextPassword)).getText().toString();
            String username = ((EditText) findViewById(R.id.signupTextUserame)).getText().toString();

            Amplify.Auth.signUp(
                    email,
                    password,
                    AuthSignUpOptions.builder()
                            .userAttribute(AuthUserAttributeKey.email(), email)
                            .userAttribute(AuthUserAttributeKey.nickname(), username)
                            .build(),
                    success -> {
                        Log.i(TAG, "Signedup successfully :" + success);
                        Intent goVerify = new Intent(SignUpActivity.this, VerifyUserActivity.class);
                        goVerify.putExtra(TAG_SIGNUP_EMAIL, email);
                        startActivity(goVerify);
                    },
                    fail -> {
                        Log.e(TAG, "Signup failed : " + fail);
                        runOnUiThread(() ->
                        {
                            Toast.makeText(SignUpActivity.this, "SIGNUP FAILED, TRY AGAIN", Toast.LENGTH_SHORT);
                        });
                    }
            );

        });
    }
}