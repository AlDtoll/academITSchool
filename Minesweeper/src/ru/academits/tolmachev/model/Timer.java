package ru.academits.tolmachev.model;

import javax.swing.*;
import java.util.TimerTask;

public class Timer {
    private int time = -1;

    public Timer(java.util.Timer timer) {
        TimerTask timerTask = new TimerTask() {

            private Runnable refresher = () -> {
            };

            @Override
            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public int getTime() {
        return time;
    }

}
