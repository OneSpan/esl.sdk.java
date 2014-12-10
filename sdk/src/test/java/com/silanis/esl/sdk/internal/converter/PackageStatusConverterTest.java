package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-06-02.
 */
public class PackageStatusConverterTest implements ConverterTest {
    
    private com.silanis.esl.sdk.PackageStatus sdkPackageStatus1 = null;
    private com.silanis.esl.sdk.PackageStatus sdkPackageStatus2 = null;
    private String apiPackageStatus1 = null;
    private String apiPackageStatus2 = null;
    private PackageStatusConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkPackageStatus1 = null;
        converter = new PackageStatusConverter(sdkPackageStatus1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIPackageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiPackageStatus1 = null;
        converter = new PackageStatusConverter(apiPackageStatus1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKPackageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkPackageStatus1 = null;
        converter = new PackageStatusConverter(sdkPackageStatus1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKPackageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiPackageStatus1 = null;
        converter = new PackageStatusConverter(apiPackageStatus1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIPackageStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkPackageStatus1 = createTypicalSDKPackageStatus();
        sdkPackageStatus2 = new PackageStatusConverter(sdkPackageStatus1).toSDKPackageStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkPackageStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkPackageStatus2, is(equalTo(sdkPackageStatus1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiPackageStatus1 = "COMPLETED";
        apiPackageStatus2 = new PackageStatusConverter(apiPackageStatus1).toAPIPackageStatus();

        assertThat("Converter returned a null api object for a non null api object", apiPackageStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiPackageStatus2, is(equalTo(apiPackageStatus1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiPackageStatus1 = "COMPLETED";
        sdkPackageStatus1 = new PackageStatusConverter(apiPackageStatus1).toSDKPackageStatus();

        assertThat("Sender type was not set correctly", sdkPackageStatus1.toString(), is(apiPackageStatus1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkPackageStatus1 = createTypicalSDKPackageStatus();
        apiPackageStatus1 = new PackageStatusConverter(sdkPackageStatus1).toAPIPackageStatus();

        assertThat("Sender type was not set correctly", apiPackageStatus1.toString(), is(sdkPackageStatus1.toString()));
    }

    /**
     * Returns a SDK PackageStatus enum.
     *
     * @return
     */
    private com.silanis.esl.sdk.PackageStatus createTypicalSDKPackageStatus() {
        return com.silanis.esl.sdk.PackageStatus.DRAFT;
    }
}
