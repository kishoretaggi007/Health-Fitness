package com.example.personal.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Launching extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(Launching.this, Signup.class));
                Launching.this.finish();
            }
        }, 2000);
    }
}
