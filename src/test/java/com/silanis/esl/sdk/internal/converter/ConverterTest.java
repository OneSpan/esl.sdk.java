package com.silanis.esl.sdk.internal.converter;

/**
 * User: jessica
 * Date: 22/11/13
 * Time: 2:14 PM
 *
 * A Converter test interface.
 */
public interface ConverterTest{

    public void convertNullSDKToAPI();

    public void convertNullAPIToSDK();

    public void convertNullSDKToSDK();

    public void convertNullAPIToAPI();

    public void convertSDKToSDK();

    public void convertAPIToAPI();

    public void convertAPIToSDK();

    public void convertSDKToAPI();
}
