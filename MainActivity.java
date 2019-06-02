package com.ryan.vargo.runnit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Handler;




public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        final ImageView labelHeader = findViewById(R.id.labelHeader);

        //opens the next screen after 2 seconds
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent guestScreen = new Intent(MainActivity.this,GuestScreen.class);
                startActivity(guestScreen);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        }, 3000);


        //animates the picture across the screen
        TranslateAnimation animation = new TranslateAnimation(-500.0f, 1500.0f, 0.0f, 0.0f);
        animation.setDuration(3100);
        animation.setRepeatCount(0);
        animation.setFillAfter(false);
        labelHeader.startAnimation(animation);
    }
}


