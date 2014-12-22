package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 12/17/14.
 */
public class FieldTypeConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.FieldType sdkFieldType1 = null;
    private com.silanis.esl.sdk.FieldType sdkFieldType2 = null;
    private String apiFieldType1 = null;
    private String apiFieldType2 = null;
    private FieldTypeConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkFieldType1 = null;
        converter = new FieldTypeConverter(sdkFieldType1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIFieldType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiFieldType1 = null;
        converter = new FieldTypeConverter(apiFieldType1);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKFieldType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkFieldType1 = null;
        converter = new FieldTypeConverter(sdkFieldType1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKFieldType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiFieldType1 = null;
        converter = new FieldTypeConverter(apiFieldType1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIFieldType(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkFieldType1 = FieldType.SIGNATURE;
        sdkFieldType2 = new FieldTypeConverter(sdkFieldType1).toSDKFieldType();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkFieldType2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkFieldType2, is( equalTo( sdkFieldType1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiFieldType1 = "SIGNATURE";
        apiFieldType2 = new FieldTypeConverter(apiFieldType1).toAPIFieldType();

        assertThat( "Converter returned a null api object for a non null api object", apiFieldType2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiFieldType2, is( equalTo( apiFieldType1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiFieldType1 = "SIGNATURE";
        sdkFieldType1 = new FieldTypeConverter(apiFieldType1).toSDKFieldType();
        assertThat("Member type was not correctly set", sdkFieldType1, is( equalTo(FieldType.SIGNATURE) ) );

        apiFieldType1 = "INPUT";
        sdkFieldType1 = new FieldTypeConverter(apiFieldType1).toSDKFieldType();
        assertThat("Member type was not correctly set", sdkFieldType1, is( equalTo(FieldType.INPUT) ) );

        apiFieldType1 = "IMAGE";
        sdkFieldType1 = new FieldTypeConverter(apiFieldType1).toSDKFieldType();
        assertThat("Member type was not correctly set", sdkFieldType1, is( equalTo(FieldType.IMAGE) ) );

        apiFieldType1 = "UNKNOWN";
        sdkFieldType1 = new FieldTypeConverter(apiFieldType1).toSDKFieldType();
        assertThat("Member type was not correctly set", sdkFieldType1.toString(), is( equalTo(FieldType.UNRECOGNIZED("UNKNOWN").toString()) ) );

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkFieldType1 = FieldType.SIGNATURE;
        apiFieldType1 = new FieldTypeConverter(sdkFieldType1).toAPIFieldType();
        assertThat("Member type was not correctly set", apiFieldType1, is( equalTo("SIGNATURE") ) );

        sdkFieldType1 = FieldType.INPUT;
        apiFieldType1 = new FieldTypeConverter(sdkFieldType1).toAPIFieldType();
        assertThat("Member type was not correctly set", apiFieldType1, is( equalTo("INPUT") ) );

        sdkFieldType1 = FieldType.IMAGE;
        apiFieldType1 = new FieldTypeConverter(sdkFieldType1).toAPIFieldType();
        assertThat("Member type was not correctly set", apiFieldType1, is( equalTo("IMAGE") ) );

        sdkFieldType1 = FieldType.UNRECOGNIZED("UNKNOWN");
        apiFieldType1 = new FieldTypeConverter(sdkFieldType1).toAPIFieldType();
        assertThat("Member type was not correctly set", apiFieldType1, is( equalTo("UNKNOWN") ) );
    }
}
