package ru.academits.tolmachev.model;

import javax.swing.*;
import java.util.TimerTask;

public class Timer {
    private boolean isWork = false;
    private int time = 0;

    public Timer(java.util.Timer timer) {
        TimerTask timerTask = new TimerTask() {

            private Runnable refresher = () -> {
            };

            @Override
            public void run() {
                if (isWork) {
                    time++;
                }
                SwingUtilities.invokeLater(refresher);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public int getTime() {
        return time;
    }

    public void startTimer(boolean enable) {
        isWork = enable;
    }

    public void resetTimer() {
        time = 0;
    }



}
