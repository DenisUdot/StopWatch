package com.denisudotgmail.stopwatch;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

public class Stopwatch {
    private MediaPlayer mp;
    private long hours,savedRoundTime ,minutes, savedRestTime, seconds, now, milliseconds, calcTime, mill, timeStop;
    private int savedNumberOfRound, numberOfRound=1;
    private long restTime=0;
    private long roundTime=86400000;
    private long setTime;
    private boolean running,restOrRound,wasRunning;
    private TextView timeView;

    public Stopwatch (TextView timeView,Context context){
        this.timeView=timeView;
        mp = MediaPlayer.create(context, R.raw.beep);
    }
    public Stopwatch(){

    }

    public void setUp(TextView timeView,Context context){
        this.timeView=timeView;
        mp = MediaPlayer.create(context, R.raw.beep);

    }

    public void start(){
        setTime=roundTime;
        // loop for save time when timer stoped
        if (wasRunning==true){
            mill=mill+(System.currentTimeMillis()-timeStop);
        }
        else {
            restOrRound=false;
            mill = System.currentTimeMillis();
        }
        running=true;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                hours = TimeUnit.MILLISECONDS.toHours(calcTime) % 24;
                minutes = TimeUnit.MILLISECONDS.toMinutes(calcTime) % 60;
                seconds = TimeUnit.MILLISECONDS.toSeconds(calcTime) % 60;
                // milliseconds show time in hundred
                milliseconds = (calcTime/10) % 100;
                String time = String.format("%d:%02d:%02d.%02d", hours, minutes, seconds,milliseconds);
                try{
                    timeView.setText(time);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                if (running) {
                    now = System.currentTimeMillis();
                    calcTime = now - mill;
                    if (calcTime >= setTime) {
                        if (restOrRound == false) {
                            mill = System.currentTimeMillis();
                            restOrRound = true;
                            setTime = restTime;
                            numberOfRound--;
                            mp.start();
                            if (numberOfRound <= 0){
                              //  reset();
                                running=false;
                                wasRunning=running;
                                numberOfRound=savedNumberOfRound;
                            }
                        } else if (restOrRound == true) {
                            mill =System.currentTimeMillis();
                            restOrRound = false;
                            setTime = roundTime;
                            mp.start();
                        }
                    }
                }
                handler.postDelayed(this, 70);
            }
        });
    }
    public void stop(){
        running=false;
        timeStop=System.currentTimeMillis();
        wasRunning=true;
    }
    public void reset(){
        running=false;
        wasRunning=running;
        calcTime=0;
        numberOfRound=savedNumberOfRound;
        restTime=savedRestTime;
        roundTime=savedRoundTime;
    }
    public void setRoundTime(long roundTime){
        savedRoundTime=roundTime;
        this.roundTime=savedRoundTime;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public void setRestTime(long restTime){
        savedRestTime=restTime;
        this.restTime=savedRestTime;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public void setNumberOfRound(int numberOfRound){
        savedNumberOfRound=numberOfRound;
        this.numberOfRound=savedNumberOfRound;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public String getRoundTime(){
        if(convert(savedRoundTime)==null){
            return "mistake";
        }
        else{

            return convert(savedRoundTime);
        }
    }
    public String getRestTime(){
        if(convert(savedRestTime)==null){
            return "mistake";
        }
        else{

            return convert(savedRestTime);
        }
    }
    public int getNumberOfRound(){
        return savedNumberOfRound;
    }

    private String convert(long number){
        long hoursConvert = TimeUnit.MILLISECONDS.toHours(number) % 24;
        long minutesConvert = TimeUnit.MILLISECONDS.toMinutes(number) % 60;
        long secondsConvert = TimeUnit.MILLISECONDS.toSeconds(number) % 60;
        String timeConvert = String.format("%d:%02d:%02d", hoursConvert, minutesConvert, secondsConvert);
        return timeConvert;
    }
}
