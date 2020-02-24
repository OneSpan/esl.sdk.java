package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.DataManagementPolicy;
import com.silanis.esl.sdk.TransactionRetention;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class DataManagementPolicyConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DataManagementPolicy sdkDataManagementPolicy1 = null;
    private com.silanis.esl.sdk.DataManagementPolicy sdkDataManagementPolicy2 = null;
    private com.silanis.esl.api.model.DataManagementPolicy apiDataManagementPolicy1 = null;
    private com.silanis.esl.api.model.DataManagementPolicy apiDataManagementPolicy2 = null;
    private DataManagementPolicyConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkDataManagementPolicy1 = null;
        converter = new DataManagementPolicyConverter(sdkDataManagementPolicy1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPIDataManagementPolicy(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiDataManagementPolicy1 = null;
        converter = new DataManagementPolicyConverter(apiDataManagementPolicy1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKDataManagementPolicy(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkDataManagementPolicy1 = null;
        converter = new DataManagementPolicyConverter(sdkDataManagementPolicy1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKDataManagementPolicy(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiDataManagementPolicy1 = null;
        converter = new DataManagementPolicyConverter(apiDataManagementPolicy1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIDataManagementPolicy(), is(nullValue()));

    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkDataManagementPolicy1 = new DataManagementPolicy();
        sdkDataManagementPolicy2 = new DataManagementPolicyConverter(sdkDataManagementPolicy1).toSDKDataManagementPolicy();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkDataManagementPolicy2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkDataManagementPolicy2, is(equalTo(sdkDataManagementPolicy1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiDataManagementPolicy1 = new com.silanis.esl.api.model.DataManagementPolicy();
        apiDataManagementPolicy2 = new DataManagementPolicyConverter(apiDataManagementPolicy1).toAPIDataManagementPolicy();

        assertThat("Converter returned a null api object for a non null api object", apiDataManagementPolicy2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiDataManagementPolicy2, is(equalTo(apiDataManagementPolicy1)));

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiDataManagementPolicy1 = new com.silanis.esl.api.model.DataManagementPolicy();
        apiDataManagementPolicy1.setTransactionRetention(buildApiTransactionRetention());
        sdkDataManagementPolicy1 = new DataManagementPolicyConverter(apiDataManagementPolicy1).toSDKDataManagementPolicy();

        assertThat("Converter returned a null sdk object for a non null api object", sdkDataManagementPolicy1, is(notNullValue()));
        assertThat("Transaction Retention was not correctly set", sdkDataManagementPolicy1.getTransactionRetention(), is(notNullValue()));
        assertThat("Expired Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getExpired(), is(10));
        assertThat("Sent Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getSent(), is(20));
        assertThat("Archived Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getArchived(), is(30));
        assertThat("Completed Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getCompleted(), is(40));
        assertThat("Declined Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getDeclined(), is(50));
        assertThat("Draft Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getDraft(), is(60));
        assertThat("OptedOut Value was not correctly set", sdkDataManagementPolicy1.getTransactionRetention().getOptedOut(), is(70));
  }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkDataManagementPolicy1 = new DataManagementPolicy();
        sdkDataManagementPolicy1.setTransactionRetention(buildSdkTransactionRetention());
        apiDataManagementPolicy1 = new DataManagementPolicyConverter(sdkDataManagementPolicy1).toAPIDataManagementPolicy();

        assertThat("Converter returned a null api object for a non null sdk object", apiDataManagementPolicy1, is(notNullValue()));
        assertThat("Transaction Retention was not correctly set", apiDataManagementPolicy1.getTransactionRetention(), is(notNullValue()));
        assertThat("Expired Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getExpired(), is(10));
        assertThat("Sent Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getSent(), is(20));
        assertThat("Archived Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getArchived(), is(30));
        assertThat("Completed Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getCompleted(), is(40));
        assertThat("Declined Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getDeclined(), is(50));
        assertThat("Draft Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getDraft(), is(60));
        assertThat("OptedOut Value was not correctly set", apiDataManagementPolicy1.getTransactionRetention().getOptedOut(), is(70));
    }

    private TransactionRetention buildSdkTransactionRetention() {
        TransactionRetention result = new TransactionRetention();
        result.setExpired(10);
        result.setSent(20);
        result.setArchived(30);
        result.setCompleted(40);
        result.setDeclined(50);
        result.setDraft(60);
        result.setOptedOut(70);
        return result;
    }


    private com.silanis.esl.api.model.TransactionRetention buildApiTransactionRetention() {
        com.silanis.esl.api.model.TransactionRetention result = new com.silanis.esl.api.model.TransactionRetention();
        result.setExpired(10);
        result.setSent(20);
        result.setArchived(30);
        result.setCompleted(40);
        result.setDeclined(50);
        result.setDraft(60);
        result.setOptedOut(70);
        return result;
    }
}