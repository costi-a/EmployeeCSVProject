package controller;

public class CalculateTimeTaken {
    public long calculateStartTime() {
        long startTimeSeconds = 0;

        try {
            System.out.println("timer started");
            startTimeSeconds = System.nanoTime() / 1000000000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startTimeSeconds;
    }

    public long calculateEndTime(long startTimeSeconds) {
        long duration = 0;

        try {
            long endTimeSeconds = System.nanoTime() / 1000000000;
            duration = endTimeSeconds - startTimeSeconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }
}
