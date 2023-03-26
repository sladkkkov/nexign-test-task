package ru.sladkkov.nexigntesttask;


import ru.sladkkov.nexigntesttask.dto.CallDataRecordDto;
import ru.sladkkov.nexigntesttask.rate.impl.MinuteTariff;
import ru.sladkkov.nexigntesttask.service.FileReader;
import ru.sladkkov.nexigntesttask.service.ReportService;

import java.util.List;
import java.util.Map;

public class NexignTestTaskApplication {

    public static void main(String[] args) {

        var listFromFile = FileReader.getListCdrFromFile();

        Map<String, List<CallDataRecordDto>> mapGroupingByClientNumber = new MinuteTariff()
                .getMapGroupingByClientNumber(listFromFile);

        mapGroupingByClientNumber.values().forEach(ReportService::printReport);
    }
}
