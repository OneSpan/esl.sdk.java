package com.silanis.esl.sdk.internal;

import com.silanis.esl.sdk.EslException;

public class Asserts {

    private Asserts() {}


    public static void genericAssert(boolean condition, String message) {
        if ( !condition ) {
            throw new EslException( message );
        }
    }

    public static void notNullOrEmpty(String assertedValue, String fieldName) {
        genericAssert( !(assertedValue == null || assertedValue.trim().isEmpty()), fieldName + "cannot be null or empty" );
    }

    public static void notNull(Object assertedValue, String fieldName) {
        genericAssert( !(assertedValue == null), fieldName + "cannot be null" );
    }
}