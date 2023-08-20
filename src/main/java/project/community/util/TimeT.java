package test.testPage.util;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Component(value = "TimeT")
public class TimeT {
    public static String getTimeT(String value) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime timeValue = LocalDateTime.parse(value);
        long betweenTime = ChronoUnit.MINUTES.between(timeValue, today);

        if (betweenTime < 1) {
            return "방금전";
        }

        if (betweenTime < 60) {
            return betweenTime + "분전";
        }

        long betweenTimeHour = ChronoUnit.HOURS.between(timeValue, today);
        if (betweenTimeHour < 24) {
            return betweenTimeHour + "시간전";
        }

        long betweenTimeDay = ChronoUnit.DAYS.between(timeValue, today);
        if (betweenTimeDay < 365) {
            return betweenTimeDay + "일전";
        }

        return betweenTimeDay / 365 + "년전";
    }
}
