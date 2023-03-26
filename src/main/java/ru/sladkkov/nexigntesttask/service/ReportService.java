package ru.sladkkov.nexigntesttask.service;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.rate.Tariff;
import ru.sladkkov.nexigntesttask.rate.TariffFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public final class ReportService {

    private ReportService() {
    }

    public static void printReport(List<CallDataRecordDto> callDataRecordDto) {

        var current = callDataRecordDto.get(0);

        createDirectory();

        try (PrintWriter writer = new PrintWriter("reports/" + current.getRate().toString().toLowerCase() + "/" + current.getClientNumber() + ".txt")) {

            writer.printf("Tariff index: %s%n", current.getRate().getNumericTypeTariff());
            writer.print("----------------------------------------------------------------------------\n");

            writer.printf("Report for phone number %s:%n", current.getClientNumber());
            writer.print("----------------------------------------------------------------------------\n");

            writer.println("| Call Type |   Start Time        |     End Time        | Duration | Cost  |");
            writer.print("----------------------------------------------------------------------------\n");


            var rate = checkRate(callDataRecordDto);

            for (CallDataRecordDto dataRecordDto : callDataRecordDto) {
                writer.printf("|     %s    | %tF %<tT | %tF %<tT | %tT | %5.2f |%n",
                        dataRecordDto.getTypeCall().getNumericNameOfType(),
                        dataRecordDto.getDateAndTimeStartCall(),
                        dataRecordDto.getDateAndTimeEndCall(),
                        getDuration(dataRecordDto),
                        rate.calculateCallPrice(dataRecordDto));
            }

            writer.print("----------------------------------------------------------------------------\n");
            writer.printf("|                                           Total Cost: |    %5.2f rubles |%n", rate.calculateCallPriceForTariffPeriod());
            writer.print("----------------------------------------------------------------------------\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDirectory() {
        new File("reports").mkdirs();
        new File("reports/minute").mkdirs();
        new File("reports/ordinary").mkdirs();
        new File("reports/unlimited").mkdirs();
    }

    private static LocalTime getDuration(CallDataRecordDto dataRecordDto) {

        var duration = Duration.between(dataRecordDto.getDateAndTimeStartCall(),
                dataRecordDto.getDateAndTimeEndCall());

        return LocalTime.of((int) duration.toHours(),
                (int) duration.toMinutes() % 60,
                (int) duration.getSeconds() % 60);

    }

    private static Tariff checkRate(List<CallDataRecordDto> callDataRecordDtoList) {
        var rate = callDataRecordDtoList.get(0).getRate();

        if (callDataRecordDtoList.stream()
                .map(CallDataRecordDto::getRate)
                .allMatch(t -> t.equals(rate))) {

            return TariffFactory.createCdr(rate);
        }

        throw new IllegalArgumentException();
    }
}
