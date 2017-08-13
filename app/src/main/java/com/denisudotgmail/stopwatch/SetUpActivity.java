package com.denisudotgmail.stopwatch;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class SetUpActivity extends AppCompatActivity {
    Stopwatch timer;
    Intent intent;
    private NumberPicker roundDurationHours,roundDurationSeconds,roundDurationMinutes,restDurationHours,restDurationMinutes,restDurationSeconds,
    roundNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        roundDurationHours=(NumberPicker)findViewById(R.id.roundDurationHours);
        roundDurationHours.setMinValue(0);
        roundDurationHours.setMaxValue(59);
        roundDurationHours.setWrapSelectorWheel(true);
        roundDurationMinutes=(NumberPicker)findViewById(R.id.roundDurationMinutes);
        roundDurationMinutes.setMinValue(0);
        roundDurationMinutes.setMaxValue(59);
        roundDurationMinutes.setWrapSelectorWheel(true);
        roundDurationSeconds =(NumberPicker)findViewById(R.id.roundDurationSeconds);
        roundDurationSeconds.setMinValue(0);
        roundDurationSeconds.setMaxValue(59);
        roundDurationSeconds.setWrapSelectorWheel(true);
        restDurationHours=(NumberPicker)findViewById(R.id.restDurationHours);
        restDurationHours.setMaxValue(30);
        restDurationHours.setMinValue(0);
        restDurationHours.setWrapSelectorWheel(true);
        restDurationMinutes=(NumberPicker)findViewById(R.id.restDurationMinutes);
        restDurationMinutes.setMaxValue(30);
        restDurationMinutes.setMinValue(0);
        restDurationMinutes.setWrapSelectorWheel(true);
        restDurationSeconds=(NumberPicker)findViewById(R.id.restDurationSeconds);
        restDurationSeconds.setMinValue(0);
        restDurationSeconds.setMaxValue(59);
        restDurationSeconds.setWrapSelectorWheel(true);
        roundNumber=(NumberPicker)findViewById(R.id.numberOfRound);
        roundNumber.setMinValue(1);
        roundNumber.setMaxValue(30);
        roundNumber.setWrapSelectorWheel(true);
    }

    public void onClickOk(View view){

        intent=getIntent();
        timer=(Stopwatch)intent.getExtras().getParcelable(StopwatchActivity.STOPWATCH);
        timer.setRoundTime((long)(roundDurationHours.getValue()*60*60*1000)+(roundDurationMinutes.getValue()*60*1000)+(roundDurationSeconds.getValue()*1000));
        timer.setRestTime((long)(restDurationHours.getValue()*60*60*1000)+(restDurationMinutes.getValue()*60*1000)+(restDurationSeconds.getValue()*1000));
        timer.setNumberOfRound(roundNumber.getValue());
        finish();
    }
    // onClickCancel sets all value to zero
    public void onClickCancel(View view){
        restDurationSeconds.setValue(0);
        restDurationMinutes.setValue(0);
        restDurationHours.setValue(0);

        roundDurationSeconds.setValue(0);
        roundDurationMinutes.setValue(0);
        roundDurationHours.setValue(0);

        roundNumber.setValue(1);
    }

}
