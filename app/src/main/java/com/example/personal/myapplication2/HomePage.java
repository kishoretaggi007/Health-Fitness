package com.example.personal.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class HomePage extends AppCompatActivity {
ImageButton imagebutton,imagebutton1,imagebutton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        imagebutton = (ImageButton) findViewById(R.id.dec_weight);
        imagebutton1 = (ImageButton) findViewById(R.id.inc_weight);
        imagebutton2 = (ImageButton) findViewById(R.id.stay_fit);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Timer().schedule(new TimerTask(){
                    public void run() {
                        startActivity(new Intent(HomePage.this, MainPage.class));
                        HomePage.this.finish();
                    }
                }, 1000);
            }
        });
        imagebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Timer().schedule(new TimerTask(){
                    public void run() {
                        startActivity(new Intent(HomePage.this, MainPage.class));
                        HomePage.this.finish();
                    }
                }, 1000);
            }
        });
        imagebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Timer().schedule(new TimerTask(){
                    public void run() {
                        startActivity(new Intent(HomePage.this, MainPage.class));
                        HomePage.this.finish();
                    }
                }, 1000);
            }
        });
    }
}
