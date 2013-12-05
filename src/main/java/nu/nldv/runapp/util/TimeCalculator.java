package nu.nldv.runapp.util;

import org.springframework.stereotype.Service;

@Service
public class TimeCalculator {

    public double calculatePace(String duration, double trackLength) {
        double durationInMinutes = getDurationInMinutesFromString(duration);
        return durationInMinutes / trackLength;
    }

    private double getDurationInMinutesFromString(String durationString) {
        String[] times = durationString.split(":");
        double duration = Double.valueOf(times[1]);
        if (!times[0].equalsIgnoreCase("00")) {
            int hours = Integer.parseInt(times[0]);
            duration += hours * 60;
        }
        int seconds = Integer.parseInt(times[2]);
        duration += (seconds / 60);
        return duration;
    }

}
