package com.silanis.esl.sdk.internal;

import java.io.UnsupportedEncodingException;

/**
 * User: dave
 */
public class Converter {
    public static byte[] toBytes( String str ) throws UnsupportedEncodingException {
        return str.getBytes( "UTF-8" );
    }

    public static String toString( byte[] bytes ) throws UnsupportedEncodingException {
        return new String( bytes, "UTF-8" );
    }
}
