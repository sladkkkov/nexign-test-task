package ru.sladkkov.nexigntesttask.service;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.enums.TypeCall;
import ru.sladkkov.nexigntesttask.enums.TypeTariff;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class FileReader {

    private FileReader() {
    }

    public static List<CallDataRecordDto> getListCdrFromFile() {

        List<CallDataRecordDto> callDataRecordDtoList = new ArrayList<>();
        var path = Paths.get("src/main/resources/cdr.txt");
        var dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyyMMddHHmmss")
                .toFormatter();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(t -> {
                var cdr = t.split(", ");

                callDataRecordDtoList.add(new CallDataRecordDto(
                        TypeCall.fromNumericNameOfType(cdr[0]),
                        cdr[1],
                        LocalDateTime.parse(cdr[2], dateTimeFormatter),
                        LocalDateTime.parse(cdr[3], dateTimeFormatter),
                        TypeTariff.fromNumericRateOfType(cdr[4])));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return callDataRecordDtoList;
    }
}
