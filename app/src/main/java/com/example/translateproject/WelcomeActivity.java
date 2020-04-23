package com.example.translateproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;


public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
        Log.d(TAG, "onStop: ");
    }

    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d(TAG, "onCreate: Created");
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d(TAG, "onAuthStateChanged: ");
                if (firebaseAuth.getCurrentUser() != null)
                {
                    Log.d(TAG, "onAuthStateChanged: currentuser!=null");

                    Intent i = new Intent(WelcomeActivity.this, Translate.class);
                    finish();
                    startActivity(i);
                } else {
                    Log.d(TAG, "onAuthStateChanged: currentuser null");
                    Intent i = new Intent(WelcomeActivity.this, SignInActivity.class);
                    finish();
                    startActivity(i);
                }

            }
        };
    }
}