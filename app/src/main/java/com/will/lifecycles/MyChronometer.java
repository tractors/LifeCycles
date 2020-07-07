package com.will.lifecycles;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyChronometer extends Chronometer implements LifecycleObserver {

    private static long elapsedTime;
    public MyChronometer(Context context) {
        this(context,null,0);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void pauseMeter(){
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void resumeMeter(){
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }
}
