package ru.sladkkov.nexigntesttask.rate.impl;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.rate.Tariff;

import java.math.BigDecimal;
import java.time.Duration;

public class MinuteTariff implements Tariff {

    private static final BigDecimal MINUTE_PRICE = BigDecimal.valueOf(1.5);

    private Duration sumTotalDuration = Duration.ZERO;

    @Override
    public BigDecimal calculateCallPriceForTariffPeriod() {

        return BigDecimal.valueOf(sumTotalDuration.toMinutes())
                .multiply(MINUTE_PRICE);
    }

    @Override
    public BigDecimal calculateCallPrice(CallDataRecordDto callDataRecordDto) {

        Duration totalDuration = calculateDurationForOneCall(callDataRecordDto.getDateAndTimeStartCall(),
                callDataRecordDto.getDateAndTimeEndCall());

        sumTotalDuration = sumTotalDuration.plus(totalDuration);

        return BigDecimal.valueOf(totalDuration.toMinutes())
                .multiply(MINUTE_PRICE);
    }


}
