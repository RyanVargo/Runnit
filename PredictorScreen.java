package com.ryan.vargo.runnit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.content.Intent;

public class PredictorScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor_screen);
        this.getSupportActionBar().hide();

        final TextView calculatedLabel = findViewById(R.id.calculatedLabel);

        Button calculateButton = findViewById(R.id.calculateButton);
        Button clearButton = findViewById(R.id.clearButton);
        Button backButton = findViewById(R.id.backButton);

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
                Intent intent = new Intent(PredictorScreen.this, GuestScreen.class);
                startActivity(intent);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                EditText hoursInput = findViewById(R.id.hoursInput);
                EditText minutesInput = findViewById(R.id.minutesInput);
                EditText secondsInput = findViewById(R.id.secondsInput);
                EditText firstDistance = findViewById(R.id.firstDistance);
                EditText secondDistance = findViewById(R.id.secondDistance);
                EditText mileage = findViewById(R.id.mileageText);
                CheckBox firstCheck = findViewById(R.id.firstCheckBox);
                CheckBox secondCheck = findViewById(R.id.secondCheckBox);

                double totalSeconds;

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
                if(firstDistance.length() == 0)
                {
                    firstDistance.setText("1");
                }
                if(secondDistance.length() == 0)
                {
                    secondDistance.setText("1");
                }
                if(mileage.length() == 0)
                {
                    mileage.setText("0");
                }

                String mile = mileage.getText().toString();
                double miles = Double.parseDouble(mile);

                String firstSelection = firstDistance.getText().toString();
                double first = Double.parseDouble(firstSelection);

                String secondSelection = secondDistance.getText().toString();
                double second = Double.parseDouble(secondSelection);

                String hours = hoursInput.getText().toString();
                double hoursEntered = Double.parseDouble(hours);

                String minutes = minutesInput.getText().toString();
                double minutesEntered = Double.parseDouble(minutes);

                String seconds = secondsInput.getText().toString();
                double secondsEntered = Double.parseDouble(seconds);

                totalSeconds = (hoursEntered * 3600) + (minutesEntered * 60) + secondsEntered;

                if(firstCheck.isChecked())
                {
                    first = first/1.609344;
                }
                if(secondCheck.isChecked())
                {
                    second = second/1.609344;
                }

                double finalTotalTime;
                double finalHours;
                double finalMinutes;
                double finalSeconds;
                String totalTime;
                String hoursTime;
                String minutesTime;
                String secondsTime;
                int minute;
                int hour;
                double factor = 1;

                if(first > second)
                {
                    if(first/second <= 2)
                    {
                        factor = .95;
                    }

                    if(first/second > 2 && first/second <= 3)
                    {
                        factor = .94;
                    }

                    if(first/second > 3 && first/second <= 4)
                    {
                        factor = .93;
                    }

                    if(first/second > 4 && first/second <= 5)
                    {
                        factor = .91;
                    }

                    if(first/second > 5 && first/second <= 6)
                    {
                        factor = .89;
                    }

                    if(first/second > 6 && first/second <= 7)
                    {
                        factor = .87;
                    }

                    if(first/second > 7 && first/second <= 8)
                    {
                        factor = .85;
                    }

                    if(first/second > 8 && first/second <= 9)
                    {
                        factor = .84;
                    }

                    if(first/second > 9 && first/second <= 10)
                    {
                        factor = .82;
                    }

                    if(first/second > 10)
                    {
                        factor = .8;
                    }
                }

                if(second > first)
                {
                    if(second/first <= 2)
                    {
                        factor = 1.05;
                    }

                    if(second/first > 2 && second/first <= 3)
                    {
                        factor = 1.06;
                    }

                    if(second/first > 3 && second/first <= 4)
                    {
                        factor = 1.07;
                    }

                    if(second/first > 5 && second/first <= 6)
                    {
                        factor = 1.09;
                    }

                    if(second/first > 6 && second/first <= 7)
                    {
                        factor = 1.11;
                    }

                    if(second/first > 8 && second/first <= 9)
                    {
                        factor = 1.13;
                    }

                    if(second/first > 9 && second/first <= 10)
                    {
                        factor = 1.15;
                    }

                    if(second/first > 10)
                    {
                        factor = 1.17;
                    }
                }



                //FORMULA
                //T2 = T1 x (D2/D1)1.06
                finalTotalTime = factor * totalSeconds * Math.pow((second/first),1.06);

                if(miles <= 30 && miles > 15)
                {
                    finalTotalTime = finalTotalTime * .999;
                }
                if(miles > 30 && miles <= 40)
                {
                    finalTotalTime = finalTotalTime * .9975;
                }
                if(miles > 40 && miles <= 50)
                {
                    finalTotalTime = finalTotalTime * .995;
                }
                if(miles > 50 && miles <= 60)
                {
                    finalTotalTime = finalTotalTime * .9925;
                }
                if(miles > 60)
                {
                    finalTotalTime = finalTotalTime * .99;
                }

                finalHours = Math.floor(finalTotalTime/3600);
                finalMinutes = (int)(finalTotalTime/60);
                finalSeconds = Math.round((((finalTotalTime/60) - (int)(finalTotalTime/60)) * 60)*100.0)/100.0;



                minute = (int) finalMinutes;
                hour = (int) finalHours;


                while(finalSeconds >= 60)
                {
                    finalSeconds = finalSeconds - 60;
                }

                while(minute >= 60)
                {
                    minute = minute - 60;
                }


                hoursTime = Integer.toString(hour);
                minutesTime = Integer.toString(minute);
                secondsTime = Double.toString(finalSeconds);

                if(hour < 10)
                {
                    hoursTime = "0" + hoursTime;
                }
                if(minute < 10)
                {
                    minutesTime = "0" + minutesTime;
                }
                if(finalSeconds < 10)
                {
                    secondsTime = "0" + secondsTime;
                }


                totalTime = hoursTime + ":" + minutesTime + ":" + secondsTime;
                calculatedLabel.setText(totalTime);
            }
        });
    }

}