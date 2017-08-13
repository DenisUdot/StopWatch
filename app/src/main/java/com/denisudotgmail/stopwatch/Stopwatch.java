package com.denisudotgmail.stopwatch;


import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

public class Stopwatch implements Parcelable {
    long hours,minutes,seconds,now,milliseconds,calcTime,mill,timeStop;
    int numberOfRound=1;
    long restTime=0;
    long roundTime=360000000;
    long setTime;
    boolean running,restOrRound,wasRunning;
    TextView timeView;
    private int mData;

    public Stopwatch(TextView timeView){
        this.timeView=timeView;
    }


    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Stopwatch(Parcel in) {
        mData = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mData);
    }
    public static final Parcelable.Creator<Stopwatch> CREATOR = new Parcelable.Creator<Stopwatch>() {
        public Stopwatch createFromParcel(Parcel in) {
            return new Stopwatch(in);
        }

        public Stopwatch[] newArray(int size) {
            return new Stopwatch[size];
        }
    };




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
                handler.postDelayed(this, 75);
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
    }
    public void setRoundTime(long roundTime){
        this.roundTime=roundTime;
        reset();
    }
    public void setRestTime(long restTime){
        this.restTime=restTime;
        reset();
    }
    public void setNumberOfRound(int numberOfRound){
        this.numberOfRound=numberOfRound;
        reset();
    }
//    public void playAlarm(){
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//        r.play();
//    }
}
