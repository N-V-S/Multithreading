package com.epam.task09.main.logic;

public class PortException extends Exception {

    public PortException(String message, Exception e) {
        super(message, e);
    }
}