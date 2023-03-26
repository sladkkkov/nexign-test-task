package ru.sladkkov.nexigntesttask.tariff.factory;

import ru.sladkkov.nexigntesttask.enums.TypeTariff;
import ru.sladkkov.nexigntesttask.exception.TariffConvertationException;
import ru.sladkkov.nexigntesttask.tariff.Tariff;
import ru.sladkkov.nexigntesttask.tariff.impl.MinuteTariff;
import ru.sladkkov.nexigntesttask.tariff.impl.OrdinaryTariff;
import ru.sladkkov.nexigntesttask.tariff.impl.UnlimitedTariff;

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

        throw new TariffConvertationException("Ошибка конвертации тарифа", new IllegalArgumentException());
    }
}
