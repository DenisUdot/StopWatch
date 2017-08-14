package com.denisudotgmail.stopwatch;

import android.app.Application;

/**
 * Created by dan on 14.08.2017.
 */

public class MySingleton extends Application {
    private Stopwatch stopwatch=new Stopwatch();

    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
