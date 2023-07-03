package com.example.finalstop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chronometer cr;
    Button start;
    Button pause;
    Button stop;
    long stopTime=0;
    long laptime=0;
    int lap=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        cr=(Chronometer ) findViewById ( R.id.chronometer );
        start=(Button ) findViewById ( R.id.start );
        pause=(Button) findViewById ( R.id.pause );
        stop=(Button) findViewById ( R.id.reset );
        start.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                cr.setBase( SystemClock.elapsedRealtime() + stopTime);
                cr.start();
                start.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }
        } );
        pause.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                stopTime = cr.getBase() - SystemClock.elapsedRealtime();
                cr.stop();


                Toast.makeText ( MainActivity.this, "lap is"+ lap +"lap time is"+ (stopTime-laptime)/1000 +"total"+stopTime/1000, Toast.LENGTH_SHORT ).show ();

                laptime=stopTime;
                lap++;
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);

            }
        } );
        stop.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                cr.setBase(SystemClock.elapsedRealtime());
                stopTime = 0;
                cr.stop();
                start.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }
        } );


    }
}