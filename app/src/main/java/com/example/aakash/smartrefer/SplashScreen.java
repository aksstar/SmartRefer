package com.example.aakash.smartrefer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        YoYo.with(Techniques.Swing)
                .duration(700)
                .repeat(10)
                .playOn(findViewById(R.id.relanim));
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(5000);
                    Intent intent =new Intent(getApplicationContext(),LoginGlobal.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
