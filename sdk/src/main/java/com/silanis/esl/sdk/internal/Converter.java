package com.silanis.esl.sdk.internal;

import org.apache.commons.codec.binary.Base64;

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

    public static String apiKeyToUID(String apiKey) {
        Base64 base64 = new Base64();
        byte[] decodedBytes = base64.decode(apiKey);
        return new String(decodedBytes).split(":")[0];
    }
}
