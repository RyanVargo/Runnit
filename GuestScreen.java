package com.ryan.vargo.runnit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class GuestScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_screen);
        this.getSupportActionBar().hide();

        final Button paceButton = findViewById(R.id.paceButton);
        final Button predictorButton = findViewById(R.id.predictorButton);
        final Button easterEggButton = findViewById(R.id.easterEgg);

        //opens the pace calculator screen
        paceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openSplitScreen();
            }
        });

        predictorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openPredictorScreen();
            }
        });

        easterEggButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(GuestScreen.this, "This app is dedicated to my Comp Science mentor, Mr. Minich. He has provided me with an extensive and amazing computer science education. I owe all of my CS skills to him.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void openSplitScreen()
    {
        Intent intent = new Intent(GuestScreen.this, PaceCalcScreen.class);
        startActivity(intent);
    }

    public void openPredictorScreen()
    {
        Intent intent = new Intent(GuestScreen.this, PredictorScreen.class);
        startActivity(intent);
    }
}
