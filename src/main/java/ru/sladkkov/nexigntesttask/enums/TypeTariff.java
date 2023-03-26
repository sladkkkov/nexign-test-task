package ru.sladkkov.nexigntesttask.enums;

import ru.sladkkov.nexigntesttask.exception.TariffConvertationException;

public enum TypeTariff {
    UNLIMITED("06"),
    MINUTE("03"),
    ORDINARY("11");

    private final String numericTypeTariff;

    TypeTariff(String numericTypeTariff) {
        this.numericTypeTariff = numericTypeTariff;
    }

    public String getNumericTypeTariff() {
        return numericTypeTariff;
    }

    public static TypeTariff fromNumericRateOfType(String numericTypeRate) {

        if (numericTypeRate.equals("06")) {
            return UNLIMITED;
        }

        if (numericTypeRate.equals("03")) {
            return ORDINARY;
        }

        if (numericTypeRate.equals("11")) {
            return MINUTE;
        }

        throw new TariffConvertationException("Неопознаное значение: " + numericTypeRate, new IllegalArgumentException());
    }
}
