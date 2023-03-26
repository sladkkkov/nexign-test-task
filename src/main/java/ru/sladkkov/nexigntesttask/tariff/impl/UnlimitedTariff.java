package ru.sladkkov.nexigntesttask.tariff.impl;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.tariff.Tariff;

import java.math.BigDecimal;
import java.time.Duration;

public class UnlimitedTariff implements Tariff {

    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(100);
    private static final BigDecimal UNLIMITED_MINUTE_PRICE = BigDecimal.ONE;
    private static final long FIXED_COUNT_MINUTE = 300;
    private Duration sumTotalDuration = Duration.ZERO;

    @Override
    public BigDecimal calculateCallPriceForTariffPeriod() {

        var minutes = sumTotalDuration.toMinutes();

        if (minutes > FIXED_COUNT_MINUTE) {

            return UNLIMITED_MINUTE_PRICE
                    .multiply(BigDecimal.valueOf(minutes - FIXED_COUNT_MINUTE))
                    .add(FIXED_PRICE);
        }

        return FIXED_PRICE;
    }

    @Override
    public BigDecimal calculateCallPrice(CallDataRecordDto callDataRecordDto) {

        Duration totalDuration = calculateDurationForOneCall(callDataRecordDto.getDateAndTimeStartCall(),
                callDataRecordDto.getDateAndTimeEndCall());

        sumTotalDuration = sumTotalDuration.plus(totalDuration);

        if (sumTotalDuration.toMinutes() > FIXED_COUNT_MINUTE) {

            var val = FIXED_COUNT_MINUTE - (sumTotalDuration.toMinutes() - totalDuration.toMinutes());

            return UNLIMITED_MINUTE_PRICE.multiply(BigDecimal.valueOf(totalDuration.toMinutes() - val));

        }

        return BigDecimal.ZERO;
    }

}
