package ru.sladkkov.nexigntesttask.tariff.impl;

import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.enums.TypeCall;
import ru.sladkkov.nexigntesttask.tariff.Tariff;

import java.math.BigDecimal;
import java.time.Duration;

public class OrdinaryTariff implements Tariff {

    private static final BigDecimal MINUTE_PRICE = BigDecimal.valueOf(1.5);
    private static final BigDecimal ORDINARY_PRICE = BigDecimal.valueOf(0.5);
    private static final long FIXED_COUNT_MINUTE = 100;


    private Duration sumTotalDuration = Duration.ZERO;

    @Override
    public BigDecimal calculateCallPriceForTariffPeriod() {

        var minutes = sumTotalDuration.toMinutes();

        if (minutes > FIXED_COUNT_MINUTE) {
            return BigDecimal.valueOf(FIXED_COUNT_MINUTE)
                    .multiply(ORDINARY_PRICE)
                    .add(BigDecimal.valueOf(minutes - FIXED_COUNT_MINUTE))
                    .multiply(MINUTE_PRICE);
        }

        return ORDINARY_PRICE.multiply(BigDecimal.valueOf(minutes));
    }

    @Override
    public BigDecimal calculateCallPrice(CallDataRecordDto callDataRecordDto) {


        if (callDataRecordDto.getTypeCall().equals(TypeCall.INCOMING)) {

            return BigDecimal.ZERO;
        }

        Duration totalDuration = calculateDurationForOneCall(callDataRecordDto.getDateAndTimeStartCall(),
                callDataRecordDto.getDateAndTimeEndCall());

        sumTotalDuration = sumTotalDuration.plus(totalDuration);

        if (sumTotalDuration.toMinutes() > FIXED_COUNT_MINUTE) {
            var val = FIXED_COUNT_MINUTE - (sumTotalDuration.toMinutes() - totalDuration.toMinutes());

            return ORDINARY_PRICE
                    .multiply(BigDecimal.valueOf(val))
                    .add(MINUTE_PRICE.multiply(BigDecimal.valueOf(totalDuration.toMinutes() - val)));

        }



        return ORDINARY_PRICE.multiply(BigDecimal.valueOf(totalDuration.toMinutes()));
    }

}
