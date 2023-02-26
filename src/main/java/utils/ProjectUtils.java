package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectUtils {
    public static String getFutureDate(int days){
        LocalDateTime todayDate = LocalDateTime.now();
        LocalDateTime futureDate = todayDate.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return futureDate.format(formatter);
    }
}
