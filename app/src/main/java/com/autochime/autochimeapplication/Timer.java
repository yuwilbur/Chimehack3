package com.autochime.autochimeapplication;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

interface TimerListener {
    void onTimerExpire();
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class Timer {
    private static Timer mInstance = null;
    public static Timer instance() {
        if (mInstance == null)
            mInstance = new Timer();
        return mInstance;
    }
    Timer() {}

    private List<TimerListener> mListeners = new ArrayList<TimerListener>();
    public void addListener(TimerListener listener) { mListeners.add(listener); }
    private void OnExpire() { for (TimerListener listener : mListeners) listener.onTimerExpire(); }

    public void Start(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                OnExpire();
            }
        }, milliseconds);
    }
}
