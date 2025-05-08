package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeConversionService {
    public Map<String, String> getConvertedTimes() {
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver");
        ZoneId zUTC = ZoneId.of("UTC");

        // Current local time
        LocalDateTime localDateTime = LocalDateTime.now();

        // Associate system default time zone
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        // Convert to target zones
        ZonedDateTime zonedDateTimeEastern = zonedDateTime.withZoneSameInstant(zEastern);
        ZonedDateTime zonedDateTimeMountain = zonedDateTime.withZoneSameInstant(zMountain);
        ZonedDateTime zonedDateTimeUTC = zonedDateTime.withZoneSameInstant(zUTC);

        // Formatters
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy");

        Map<String, String> response = new HashMap<>();
        response.put("date", zonedDateTimeEastern.toLocalDateTime().format(dateFormatter));
        response.put("easternTime", zonedDateTimeEastern.toLocalDateTime().format(timeFormatter));
        response.put("mountainTime", zonedDateTimeMountain.toLocalDateTime().format(timeFormatter));
        response.put("utcTime", zonedDateTimeUTC.toLocalDateTime().format(timeFormatter));

        return response;
    }
}