package com.example.nhom5_oderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class WellcomeActivity extends AppCompatActivity {
     LottieAnimationView lottiee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        lottiee = findViewById(R.id.lottie);

        lottiee.animate().translationX(0).setDuration(0).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WellcomeActivity.this, LoginActivity.class));
            }
        },5000);
    }
}