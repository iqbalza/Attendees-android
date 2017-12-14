package com.example.iqbalzauqul.attendees;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);//disesuaikan dengan halaman xml untuk splash screen nya

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep (1000);//sleep(1000)
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);//disesuaikan : Intent(SplashScreen.this, "HalamanTujuan".class)
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}