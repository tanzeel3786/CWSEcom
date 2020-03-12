package com.example.cwsecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyCountDown timer = new MyCountDown(1000, 5000);
    }

    private class MyCountDown extends CountDownTimer {
        long duration, interval;

        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
            start();
        }

        @Override
        public void onFinish() {
            //textView1.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onTick(long duration) {
            // could set text for a timer here
        }

    }
}