package nu.nldv.runapp.util;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public String getDurationBetweenDates(Date startDate, Date lastPointDate) {
        long diff = lastPointDate.getTime() - startDate.getTime();
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffSeconds = diff / 1000 % 60;
        StringBuilder sb = new StringBuilder();
        if(diffHours<10) sb.append("0");
        sb.append(diffHours).append(":");
        if(diffMinutes<10) sb.append("0");
        sb.append(diffMinutes).append(":");
        if(diffSeconds<10) sb.append("0");
        sb.append(diffSeconds);
        return sb.toString();
    }
}
