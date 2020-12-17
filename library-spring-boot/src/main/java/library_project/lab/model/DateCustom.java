package library_project.lab.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateCustom {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static ZonedDateTime getDateNow(){
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public static String getDateNowString(){
        return getDateNow().format(formatter);
    }

    public static String getDateString(ZonedDateTime z){
        return z.format(formatter);
    }

    public static ZonedDateTime getZonedDateTimeFromDateTimeString(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        ZonedDateTime dateTime = ZonedDateTime.parse(dateString, formatter);
        return dateTime;
    }
    public static ZonedDateTime getZonedDateTimeFromDateString(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        ZonedDateTime dateTime = ZonedDateTime.parse(dateString+" 00:00:00 UTC", formatter);
        return dateTime;
    }

}
