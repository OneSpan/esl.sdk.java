package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;

import static java.lang.String.format;

public class Asserts {

    private Asserts() {}

    public static void notNullOrEmpty(String assertedValue, String fieldName) {
        if (assertedValue == null || assertedValue.trim().isEmpty()) {
            throw new EslException(format("%s cannot be null or empty", fieldName));
        }
    }

    public static void nonZero(double assertedValue, String fieldName) {
        if (assertedValue == 0) {
            throw new EslException(format("%s cannot be 0", fieldName));
        }
    }
}