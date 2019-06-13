package com.silanis.esl.sdk.internal.converter;

/**
 * A Static Converter test interface.
 *
 * @author x_MacieMi1 (Michał Maciejewski)
 */
public interface StaticConverterTest {

    public void convertNullSDKToAPI();

    public void convertNullAPIToSDK();

    public void convertAPIToSDK();

    public void convertSDKToAPI();
}
