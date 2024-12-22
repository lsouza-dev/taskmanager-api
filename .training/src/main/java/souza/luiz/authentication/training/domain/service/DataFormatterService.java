package souza.luiz.authentication.training.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFormatterService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String formatarData(LocalDateTime data) {
        return data.format(FORMATTER);
    }
}