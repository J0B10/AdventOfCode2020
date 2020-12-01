package de.ungefroren.adventofcode.y2020.helpers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Useful for measuring elapsed time
 */
public class Timer {
    private boolean started = false;
    private long start = Long.MIN_VALUE;

    /**
     * Start the timer
     */
    public Timer start() {
        start = System.nanoTime();
        started = true;
        return this;
    }

    /**
     * Resets the timer so it can be started again
     */
    public Timer reset() {
        start = Long.MIN_VALUE;
        started = false;
        return this;
    }

    /**
     * Measures the elapsed time since start in nanoseconds
     */
    public long nanosElapsed() {
        long end = System.nanoTime();
        if (!started) throw new IllegalStateException("Timer hasn't started");
        return end - start;
    }


    /**
     * Measures the elapsed time since start in the given time unit
     */
    public double elapsed(TimeUnit unit) {
        double elapsed = nanosElapsed();
        switch (unit) {
            case DAYS:
                elapsed = elapsed / 24;
            case HOURS:
                elapsed = elapsed / 60;
            case MINUTES:
                elapsed = elapsed / 60;
            case SECONDS:
                elapsed = elapsed / 1000;
            case MILLISECONDS:
                elapsed = elapsed / 1000;
            case MICROSECONDS:
                elapsed = elapsed / 1000;
        }
        return elapsed;
    }

    /**
     * Logs the elapsed time in ISO-8601 format to {@link System#out}, see {@link Duration#toString()}
     */
    public void logElapsed() {
        long elapsed = nanosElapsed();
        System.out.println(Duration.ofNanos(elapsed).toString());;
    }
}
