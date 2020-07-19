package org.example.intervalChecker;

import java.util.Timer;

public class GamesIntervalCheckExecutor extends Timer {
    public void checkInInterval(GamesIntervalCheckTask task, TimePeriod timePeriod) {
        schedule(task, 0, timePeriod.getTimeInMillis());
    }

    public void stopChecking() {
        cancel();
    }
}
