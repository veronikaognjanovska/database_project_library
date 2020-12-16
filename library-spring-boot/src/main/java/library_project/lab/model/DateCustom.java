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


}
