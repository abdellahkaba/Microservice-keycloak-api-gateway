package com.isi.employe.exception;

public class EmailConflictException extends RuntimeException {
    public EmailConflictException(String message) {

        super(message);
    }
}
