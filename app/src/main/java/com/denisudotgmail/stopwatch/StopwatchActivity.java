package com.denisudotgmail.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends Activity {
    private Stopwatch timer;
    private TextView timeView,numberOfRound,roundDuration,restDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        MySingleton mySingleton=(MySingleton)getApplicationContext();
        timeView=(TextView)findViewById(R.id.my_chronometer);
        timer=(Stopwatch)mySingleton.getStopwatch(timeView,this);
        numberOfRound=(TextView)findViewById(R.id.numberOfRoundShowView);
        roundDuration=(TextView)findViewById(R.id.roundTimeShowView);
        restDuration=(TextView)findViewById(R.id.restTimeShowView);
    }

    public void onClickStart(View view){
        timer.start();
    }
    public void onClickStop(View view){
        timer.stop();
    }
    public void onClickReset(View view){
        timer.reset();
    }

    public void onClickSetUp(View view){
        Intent intent=new Intent(this, SetUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
           numberOfRound.setText(String.valueOf(timer.getNumberOfRound()));
           roundDuration.setText(timer.getRoundTime());
           restDuration.setText(timer.getRestTime());
    }
 }

