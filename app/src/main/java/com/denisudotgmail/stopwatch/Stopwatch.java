package com.denisudotgmail.stopwatch;


import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

public class Stopwatch {
    private long hours,minutes,seconds,now,milliseconds,calcTime,mill,timeStop;
    private int numberOfRound=1;
    private long restTime=0;
    private long roundTime=960000000;
    private long setTime;
    private boolean running,restOrRound,wasRunning;
    private TextView timeView;

    //two constructors
    Stopwatch(){}
    Stopwatch(TextView timeView){
        this.timeView=timeView;
    }

    public void setTimeView(TextView timeView){
        this.timeView=timeView;
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
                timeView.setText(time);
                if (running) {
                    now = System.currentTimeMillis();
                    calcTime = now - mill;
                    if (calcTime >= setTime) {
                        if (restOrRound == false) {
                            mill = System.currentTimeMillis();
                            restOrRound = true;
                            setTime = restTime;
                            numberOfRound--;
//                            playAlarm();
                            if (numberOfRound <= 0){
                                running=false;
                            }
                        } else if (restOrRound == true) {
                            mill =System.currentTimeMillis();
                            restOrRound = false;
                            setTime = roundTime;
//                            playAlarm();
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
        numberOfRound=1;
        restTime=0;
        roundTime=960000000;
    }
    public void setRoundTime(long roundTime){
        this.roundTime=roundTime;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public void setRestTime(long restTime){
        this.restTime=restTime;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public void setNumberOfRound(int numberOfRound){
        this.numberOfRound=numberOfRound;
        running=false;
        wasRunning=running;
        calcTime=0;
    }
    public String getRoundTime(){
        if(convert(roundTime)==null){
            return "mistake";
        }
        else{

            return convert(roundTime);
        }
    }
    public String getRestTime(){
        if(convert(restTime)==null){
            return "mistake";
        }
        else{

            return convert(restTime);
        }
    }
    public int getNumberOfRound(){
        return numberOfRound;
    }
//    public void playAlarm(){
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//        r.play();
//    }
    private String convert(long number){
        long hoursConvert = TimeUnit.MILLISECONDS.toHours(number) % 24;
        long minutesConvert = TimeUnit.MILLISECONDS.toMinutes(number) % 60;
        long secondsConvert = TimeUnit.MILLISECONDS.toSeconds(number) % 60;
        String timeConvert = String.format("%d:%02d:%02d", hoursConvert, minutesConvert, secondsConvert);
        return timeConvert;
    }
}
