package controller;

public class CalculateTimeTaken {
    public long calculateStartTime() {
        try {
            System.out.println("timer started");
            long startTimeSeconds = System.currentTimeMillis() / 1000;
            return startTimeSeconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long calculateEndTime(long startTimeSeconds) {
        try {
            long endTimeSeconds = System.currentTimeMillis() / 1000;
            long duration = endTimeSeconds - startTimeSeconds;
            return duration;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
