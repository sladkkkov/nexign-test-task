package ru.sladkkov.nexigntesttask.rate;

import ru.sladkkov.nexigntesttask.enums.TypeTariff;
import ru.sladkkov.nexigntesttask.rate.impl.MinuteTariff;
import ru.sladkkov.nexigntesttask.rate.impl.OrdinaryTariff;
import ru.sladkkov.nexigntesttask.rate.impl.UnlimitedTariff;

public final class TariffFactory {

    private TariffFactory() {
    }

    public static Tariff createCdr(TypeTariff typeTariff) {

        if (typeTariff == TypeTariff.UNLIMITED) {
            return new UnlimitedTariff();
        }

        if (typeTariff == TypeTariff.MINUTE) {
            return new MinuteTariff();
        }

        if (typeTariff == TypeTariff.ORDINARY) {
            return new OrdinaryTariff();
        }

        throw new IllegalStateException("Unexpected value: " + typeTariff);
    }
}