package Api.src.griffith;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class mapDayToDate {
    public static String mapDayToDate(String day) {
        LocalDate startDate = LocalDate.now();

        switch (day.toLowerCase()) {
            case "day1":
                return startDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            case "day2":
                return startDate.plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
            case "day3":
                return startDate.plusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
            default:
                return startDate.format(DateTimeFormatter.ISO_LOCAL_DATE); // Default to "day1" if input is unrecognized
        }
    }
}