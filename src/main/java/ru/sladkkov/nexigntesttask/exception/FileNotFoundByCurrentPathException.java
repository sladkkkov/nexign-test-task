package ru.sladkkov.nexigntesttask.exception;

public class FileNotFoundByCurrentPathException extends RuntimeException {
    public FileNotFoundByCurrentPathException(String message, Throwable cause) {
        super(message, cause);
    }
}

