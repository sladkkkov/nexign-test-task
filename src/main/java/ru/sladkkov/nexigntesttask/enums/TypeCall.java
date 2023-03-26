package ru.sladkkov.nexigntesttask.enums;

public enum TypeCall {
    OUTGOING("01"),
    INCOMING("02");

    private final String numericNameOfType;

    TypeCall(String numericNameOfType) {
        this.numericNameOfType = numericNameOfType;
    }

    public String getNumericNameOfType() {
        return numericNameOfType;
    }

    public static TypeCall fromNumericNameOfType(String numericNameOfType) {

        if (numericNameOfType.equals("01")) {
            return OUTGOING;
        }

        return INCOMING;
    }
}
