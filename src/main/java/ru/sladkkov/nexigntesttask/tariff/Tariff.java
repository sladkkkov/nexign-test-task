package ru.sladkkov.nexigntesttask.tariff;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Tariff {

    BigDecimal calculateCallPriceForTariffPeriod();

    BigDecimal calculateCallPrice(CallDataRecordDto callDataRecordDto);

    default Map<String, List<CallDataRecordDto>> getMapGroupingByClientNumber(
            List<CallDataRecordDto> callDataRecordDtoList) {

        return callDataRecordDtoList.stream()
                .collect(Collectors.groupingBy(CallDataRecordDto::getClientNumber));
    }

    default Duration calculateDurationForOneCall(LocalDateTime dateAndTimeStartCall,
                                                 LocalDateTime dateAndTimeEndCall) {

        return Duration.between(dateAndTimeStartCall,
                dateAndTimeEndCall).truncatedTo(ChronoUnit.MINUTES).plusMinutes(1);
    }

}
