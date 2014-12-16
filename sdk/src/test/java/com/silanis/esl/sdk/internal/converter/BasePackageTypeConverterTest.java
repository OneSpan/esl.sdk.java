package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.BasePackageType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 12/16/14.
 */
public class BasePackageTypeConverterTest implements ConverterTest {

    private BasePackageType sdkBasePackageType1 = null;
    private BasePackageType sdkBasePackageType2 = null;
    private String apiBasePackageType1 = null;
    private String apiBasePackageType2 = null;
    private BasePackageTypeConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkBasePackageType1 = null;
        converter = new BasePackageTypeConverter(sdkBasePackageType1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIBasePackageType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiBasePackageType1 = null;
        converter = new BasePackageTypeConverter(apiBasePackageType1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKBasePackageType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkBasePackageType1 = null;
        converter = new BasePackageTypeConverter(sdkBasePackageType1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKBasePackageType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiBasePackageType1 = null;
        converter = new BasePackageTypeConverter(apiBasePackageType1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIBasePackageType(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkBasePackageType1 = BasePackageType.PACKAGE;
        sdkBasePackageType2 = new BasePackageTypeConverter(sdkBasePackageType1).toSDKBasePackageType();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkBasePackageType2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkBasePackageType2, is(equalTo(sdkBasePackageType1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiBasePackageType1 = "PACKAGE";
        apiBasePackageType2 = new BasePackageTypeConverter(apiBasePackageType1).toAPIBasePackageType();

        assertThat("Converter returned a null api object for a non null api object", apiBasePackageType2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiBasePackageType2, is(equalTo(apiBasePackageType1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiBasePackageType1 = "PACKAGE";
        sdkBasePackageType1 = new BasePackageTypeConverter(apiBasePackageType1).toSDKBasePackageType();
        assertThat("BasePackageType was not set correctly", sdkBasePackageType1, is(BasePackageType.PACKAGE));

        apiBasePackageType1 = "TEMPLATE";
        sdkBasePackageType1 = new BasePackageTypeConverter(apiBasePackageType1).toSDKBasePackageType();
        assertThat("BasePackageType was not set correctly", sdkBasePackageType1, is(BasePackageType.TEMPLATE));

        apiBasePackageType1 = "LAYOUT";
        sdkBasePackageType1 = new BasePackageTypeConverter(apiBasePackageType1).toSDKBasePackageType();
        assertThat("BasePackageType was not set correctly", sdkBasePackageType1, is(BasePackageType.LAYOUT));

        apiBasePackageType1 = "UNKNOWN";
        sdkBasePackageType1 = new BasePackageTypeConverter(apiBasePackageType1).toSDKBasePackageType();
        assertThat("BasePackageType was not set correctly", sdkBasePackageType1.toString(), is(BasePackageType.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkBasePackageType1 = BasePackageType.PACKAGE;
        apiBasePackageType1 = new BasePackageTypeConverter(sdkBasePackageType1).toAPIBasePackageType();
        assertThat("BasePackageType was not set correctly", apiBasePackageType1, is("PACKAGE"));

        sdkBasePackageType1 = BasePackageType.TEMPLATE;
        apiBasePackageType1 = new BasePackageTypeConverter(sdkBasePackageType1).toAPIBasePackageType();
        assertThat("BasePackageType was not set correctly", apiBasePackageType1, is("TEMPLATE"));

        sdkBasePackageType1 = BasePackageType.LAYOUT;
        apiBasePackageType1 = new BasePackageTypeConverter(sdkBasePackageType1).toAPIBasePackageType();
        assertThat("BasePackageType was not set correctly", apiBasePackageType1, is("LAYOUT"));

        sdkBasePackageType1 = BasePackageType.UNRECOGNIZED("UNKNOWN");
        apiBasePackageType1 = new BasePackageTypeConverter(sdkBasePackageType1).toAPIBasePackageType();
        assertThat("BasePackageType was not set correctly", apiBasePackageType1, is("UNKNOWN"));
    }
}
