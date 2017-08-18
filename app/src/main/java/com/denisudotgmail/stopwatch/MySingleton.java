package com.denisudotgmail.stopwatch;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

/**
 * Created by dan on 14.08.2017.
 */

public class MySingleton extends Application {

    private Stopwatch stopwatch=new Stopwatch();

    public Stopwatch getStopwatch(TextView timeView, Context context) {
        stopwatch.setUp(timeView,context);
        return stopwatch;
    }
    public Stopwatch getStopwatch(){
        return  stopwatch;
    }
}
