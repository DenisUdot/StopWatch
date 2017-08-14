package com.denisudotgmail.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;





public class StopwatchActivity extends Activity {
    private Stopwatch timer;
    private TextView timeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        MySingleton mySingleton=(MySingleton)getApplicationContext();
        timer=(Stopwatch)mySingleton.getStopwatch();
        timeView=(TextView)findViewById(R.id.my_chronometer);
        timer.setTimeView(timeView);
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
    protected void onStop(){
        super.onStop();

    }
    @Override
    protected void onStart(){
        super.onStart();
//        TextView numberOfRound=(TextView)findViewById(R.id.numberOfRoundView);
//        numberOfRound.setText(timer.getNumberOfRound());
    }

}

