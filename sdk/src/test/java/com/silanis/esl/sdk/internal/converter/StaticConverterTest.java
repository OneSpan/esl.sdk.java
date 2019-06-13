package com.silanis.esl.sdk.internal.converter;

/**
 * A Static Converter test interface.
 *
 * @author x_MacieMi1 (Michał Maciejewski)
 */
public interface StaticConverterTest {

    void convertNullSDKToAPI();

    void convertNullAPIToSDK();

    void convertAPIToSDK();

    void convertSDKToAPI();
}
