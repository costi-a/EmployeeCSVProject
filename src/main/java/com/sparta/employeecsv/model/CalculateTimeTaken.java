package com.sparta.employeecsv.model;

public class CalculateTimeTaken {
    public long calculateStartTime() {
        long startTimeSeconds = System.currentTimeMillis() / 1000;
        return startTimeSeconds;
    }

    public long calculateEndTime(long startTimeSeconds) {
        long endTimeSeconds = System.currentTimeMillis() / 1000;
        long duration = endTimeSeconds - startTimeSeconds;
        return duration;
    }
}
