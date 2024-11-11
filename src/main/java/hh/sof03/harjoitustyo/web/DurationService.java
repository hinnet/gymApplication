package hh.sof03.harjoitustyo.web;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DurationService {

    public String calculateDurationOfWorkout(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            return "";
        }

        Duration duration = Duration.between(startTime, endTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        // Palautetaan kesto muodossa hh:mm:ss (%d = korvattava paikkamerkki, 02 = varmistaa, että numero on kaksilukuinen esim. 00, 11, 05 jne)
        return String.format("%02d:%02d" + " Hours", hours, minutes);
    }
}
