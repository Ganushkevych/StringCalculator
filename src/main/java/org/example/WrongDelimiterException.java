package org.example;

public class WrongDelimiterException extends RuntimeException{
    public WrongDelimiterException(String text){
        super(text);
    }
}
