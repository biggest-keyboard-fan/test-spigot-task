package com.example.exceptions;

public class InvalidDataType extends RuntimeException {
    public InvalidDataType(String message){
        super(message);
    }
    //public <T> InvalidDataType(T type) {
    //    super("Encountered invalid '"+type.getClass().getName()+"' data type.");
    //}
}
