package com.ryan.vargo.runnit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;


public class PaceCalcScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pace_calc_screen);
        this.getSupportActionBar().hide();


        final Button paceButton = findViewById(R.id.calculateButton);
        final EditText hoursInput = findViewById(R.id.hoursInput);
        final EditText minutesInput = findViewById(R.id.minutesInput);
        final EditText secondsInput = findViewById(R.id.secondsInput);
        final EditText milesInput = findViewById(R.id.milesInput);
        final Button clearButton = findViewById(R.id.clearButton);
        final Button backButton = findViewById(R.id.backButton);

        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(PaceCalcScreen.this, GuestScreen.class);
                startActivity(intent);
            }
        });

        paceButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {


                //fills in empty text boxes
                if(milesInput.length() == 0)
                {
                    milesInput.setText("0");
                }
                if(hoursInput.length() == 0)
                {
                    hoursInput.setText("0");
                }
                if(minutesInput.length() == 0)
                {
                    minutesInput.setText("0");
                }
                if(secondsInput.length() == 0)
                {
                    secondsInput.setText("0");
                }


                //defines all of the inputs as numbers
                TextView pace = findViewById(R.id.paceLabel);
                TextView fourPace = findViewById(R.id.fourPaceLabel);

                pace.setVisibility(View.VISIBLE);
                fourPace.setVisibility(View.VISIBLE);

                String hoursEnter = hoursInput.getText().toString();
                double hoursInput = Double.parseDouble(hoursEnter);

                String minutesEnter = minutesInput.getText().toString();
                double minutesInput = Double.parseDouble(minutesEnter);

                String secondsEnter = secondsInput.getText().toString();
                double secondsInput = Double.parseDouble(secondsEnter);

                String milesEnter = milesInput.getText().toString();
                double milesInput = Double.parseDouble(milesEnter);

                double hours;
                double minutes;
                double seconds;
                double paceSeconds;
                double miles;
                double finalPaceSeconds;
                double finalPaceMinutes;
                double fourMinute;
                double fourSecond;


                //converts minutes and hours into seconds
                minutes = minutesInput * 60;
                hours = 3600 * hoursInput;
                seconds = secondsInput;
                miles = milesInput;
                paceSeconds = hours + minutes + seconds;


                //calculates the pace
                finalPaceSeconds = Math.round((((paceSeconds/miles)/60 - (int)(paceSeconds/miles)/60) * 60)*100.0)/100.0;
                finalPaceMinutes = (int)(paceSeconds/miles)/60;




                //adds an minute(hour) value if seconds(minutes) is over 60
                while(finalPaceMinutes >= 60)
                {
                    finalPaceMinutes = finalPaceMinutes - 60;
                }
                while(finalPaceSeconds >= 60)
                {
                    finalPaceMinutes = finalPaceMinutes + 1;
                    finalPaceSeconds = finalPaceSeconds - 60;
                }

                fourSecond = Math.round((((paceSeconds/(miles*4)/60 - (int)(paceSeconds/(miles*4))/60) * 60)*100.0)/100.0);
                fourMinute = (int)(paceSeconds/(miles*4))/60;

                while(fourSecond >= 60)
                {
                    fourSecond = fourSecond - 60;
                }


                //converts the values
                int minuteValue = (int)finalPaceMinutes;
                String finalMinutes = Integer.toString(minuteValue);
                String finalSeconds = Double.toString(finalPaceSeconds);

                int finalPaceFour = (int)fourMinute;
                String finalFourMinute = Integer.toString(finalPaceFour);
                String finalFourSecond = Double.toString(fourSecond);


                //adds leading zeros to numbers less than 10 to be more aesthetically appealing
                if(minuteValue < 10)
                {
                    finalMinutes = "0" + finalMinutes;
                }

                if (finalPaceSeconds < 10)
                {
                    finalSeconds = "0" + finalSeconds;
                }

                if(finalPaceFour < 10)
                {
                    finalFourMinute = "0" + finalFourMinute;
                }

                if(fourSecond < 10)
                {
                    finalFourSecond = "0" + finalFourSecond;
                }



                //prints the final pace
                String paceText = finalMinutes + ":" + finalSeconds;
                String fourPaceText = finalFourMinute + ":" + finalFourSecond;
                pace.setText("Mile Pace: " + paceText);
                fourPace.setText("400 Meter Pace: " + fourPaceText);
            }
        });
    }
}