package org.example.kotiki.infrastructure.exceptions;

public class TypeException extends Exception {
    @Override
    public String getMessage() {
        return "More than one cosmetics with the same type";
    }
}
