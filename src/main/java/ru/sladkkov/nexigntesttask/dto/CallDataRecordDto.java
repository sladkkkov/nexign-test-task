package ru.sladkkov.nexigntesttask.dto;


import ru.sladkkov.nexigntesttask.enums.TypeCall;
import ru.sladkkov.nexigntesttask.enums.TypeTariff;

import java.time.LocalDateTime;
import java.util.Objects;

public class CallDataRecordDto {
    private final TypeCall typeCall;
    private final String clientNumber;
    private final LocalDateTime dateAndTimeStartCall;
    private final LocalDateTime dateAndTimeEndCall;
    private final TypeTariff rate;

    public CallDataRecordDto(TypeCall typeCall, String clientNumber, LocalDateTime dateAndTimeStartCall, LocalDateTime dateAndTimeEndCall, TypeTariff rate) {

        this.typeCall = typeCall;
        this.clientNumber = clientNumber;
        this.dateAndTimeStartCall = dateAndTimeStartCall;
        this.dateAndTimeEndCall = dateAndTimeEndCall;
        this.rate = rate;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public TypeCall getTypeCall() {
        return typeCall;
    }

    public LocalDateTime getDateAndTimeStartCall() {
        return dateAndTimeStartCall;
    }

    public LocalDateTime getDateAndTimeEndCall() {
        return dateAndTimeEndCall;
    }

    public TypeTariff getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallDataRecordDto that = (CallDataRecordDto) o;
        return typeCall == that.typeCall && Objects.equals(clientNumber, that.clientNumber) && Objects.equals(dateAndTimeStartCall, that.dateAndTimeStartCall) && Objects.equals(dateAndTimeEndCall, that.dateAndTimeEndCall) && rate == that.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeCall, clientNumber, dateAndTimeStartCall, dateAndTimeEndCall, rate);
    }

    @Override
    public String toString() {
        return "CallDataRecordDto{" + "typeCall=" + typeCall + ", clientNumber='" + clientNumber + '\'' + ", dateAndTimeStartCall=" + dateAndTimeStartCall + ", dateAndTimeEndCall=" + dateAndTimeEndCall + ", rate=" + rate + '}';
    }
}
