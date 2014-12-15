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
public class RequirementStatusConverterTest implements ConverterTest {

private com.silanis.esl.sdk.RequirementStatus sdkRequirementStatus1 = null;
private com.silanis.esl.sdk.RequirementStatus sdkRequirementStatus2 = null;
private String apiRequirementStatus1 = null;
private String apiRequirementStatus2 = null;
private RequirementStatusConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkRequirementStatus1 = null;
        converter = new RequirementStatusConverter(sdkRequirementStatus1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIRequirementStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiRequirementStatus1 = null;
        converter = new RequirementStatusConverter(apiRequirementStatus1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKRequirementStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkRequirementStatus1 = null;
        converter = new RequirementStatusConverter(sdkRequirementStatus1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKRequirementStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiRequirementStatus1 = null;
        converter = new RequirementStatusConverter(apiRequirementStatus1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIRequirementStatus(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkRequirementStatus1 = createTypicalSDKRequirementStatus();
        sdkRequirementStatus2 = new RequirementStatusConverter(sdkRequirementStatus1).toSDKRequirementStatus();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkRequirementStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkRequirementStatus2, is(equalTo(sdkRequirementStatus1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiRequirementStatus1 = "REJECTED";
        apiRequirementStatus2 = new RequirementStatusConverter(apiRequirementStatus1).toAPIRequirementStatus();

        assertThat("Converter returned a null api object for a non null api object", apiRequirementStatus2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiRequirementStatus2, is(equalTo(apiRequirementStatus1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiRequirementStatus1 = "REJECTED";
        sdkRequirementStatus1 = new RequirementStatusConverter(apiRequirementStatus1).toSDKRequirementStatus();

        assertThat("Sender type was not set correctly", sdkRequirementStatus1.toString(), is(apiRequirementStatus1.toString()));

        apiRequirementStatus1 = "UNKNOWN";
        sdkRequirementStatus1 = new RequirementStatusConverter(apiRequirementStatus1).toSDKRequirementStatus();

        assertThat("Sender type was not set correctly", sdkRequirementStatus1.toString(), is(apiRequirementStatus1.toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkRequirementStatus1 = createTypicalSDKRequirementStatus();
        apiRequirementStatus1 = new RequirementStatusConverter(sdkRequirementStatus1).toAPIRequirementStatus();

        assertThat("Sender type was not set correctly", apiRequirementStatus1.toString(), is(sdkRequirementStatus1.toString()));

        sdkRequirementStatus1 = com.silanis.esl.sdk.RequirementStatus.UNRECOGNIZED("UNKNOWN");
        apiRequirementStatus1 = new RequirementStatusConverter(sdkRequirementStatus1).toAPIRequirementStatus();

        assertThat("Sender type was not set correctly", apiRequirementStatus1.toString(), is(sdkRequirementStatus1.toString()));
    }

    /**
     * Returns a SDK RequirementStatus enum.
     *
     * @return
     */
    private com.silanis.esl.sdk.RequirementStatus createTypicalSDKRequirementStatus() {
        return com.silanis.esl.sdk.RequirementStatus.INCOMPLETE;
    }
}
