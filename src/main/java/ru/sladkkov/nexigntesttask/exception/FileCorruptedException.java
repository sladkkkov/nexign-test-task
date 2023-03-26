package ru.sladkkov.nexigntesttask.exception;

public class FileCorruptedException extends RuntimeException {
    public FileCorruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}

