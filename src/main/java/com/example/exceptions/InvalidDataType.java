package com.example.exceptions;

public class InvalidDataType extends RuntimeException {
    public <T> InvalidDataType(T type) {
        super("Encountered invalid '"+type.getClass().getName()+"' data type.");
    }
}
