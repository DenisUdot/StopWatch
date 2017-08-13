package com.denisudotgmail.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;





public class StopwatchActivity extends Activity {
    public Stopwatch timer;
    TextView timeView;
    public static final String STOPWATCH="Stopwatch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
//        Intent intent=getIntent();
//        numberOfRound=intent.getIntExtra("NUMBER_OF_ROUND",12);
//        restTime=intent.getLongExtra("REST_TIME",1000);
//        roundTime=intent.getLongExtra("ROUND_TIME",2000);
        timeView=(TextView)findViewById(R.id.my_chronometer);
        if (timer==null){
            timer=new Stopwatch(timeView);
        }
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
        intent.putExtra(STOPWATCH,timer);
        startActivity(intent);
    }

    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onStart(){
        super.onStart();

    }
    public Stopwatch getTimer(){
        return timer;
    }
}

