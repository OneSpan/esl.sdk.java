package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.TransactionRetention;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TransactionRetentionConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.TransactionRetention sdkTransactionRetention1 = null;
    private com.silanis.esl.sdk.TransactionRetention sdkTransactionRetention2 = null;
    private com.silanis.esl.api.model.TransactionRetention apiTransactionRetention1 = null;
    private com.silanis.esl.api.model.TransactionRetention apiTransactionRetention2 = null;
    private TransactionRetentionConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkTransactionRetention1 = null;
        converter = new TransactionRetentionConverter(sdkTransactionRetention1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toAPITransactionRetention(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiTransactionRetention1 = null;
        converter = new TransactionRetentionConverter(apiTransactionRetention1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toSDKTransactionRetention(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkTransactionRetention1 = null;
        converter = new TransactionRetentionConverter(sdkTransactionRetention1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKTransactionRetention(), is(nullValue()));

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiTransactionRetention1 = null;
        converter = new TransactionRetentionConverter(apiTransactionRetention1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPITransactionRetention(), is(nullValue()));

    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkTransactionRetention1 = new TransactionRetention();
        sdkTransactionRetention2 = new TransactionRetentionConverter(sdkTransactionRetention1).toSDKTransactionRetention();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkTransactionRetention2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkTransactionRetention2, is(equalTo(sdkTransactionRetention1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiTransactionRetention1 = new com.silanis.esl.api.model.TransactionRetention();
        apiTransactionRetention2 = new TransactionRetentionConverter(apiTransactionRetention1).toAPITransactionRetention();

        assertThat("Converter returned a null api object for a non null api object", apiTransactionRetention2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiTransactionRetention2, is(equalTo(apiTransactionRetention1)));

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiTransactionRetention1 = buildApiTransactionRetention();
        sdkTransactionRetention1 = new TransactionRetentionConverter(apiTransactionRetention1).toSDKTransactionRetention();

        assertThat("Converter returned a null sdk object for a non null api object", sdkTransactionRetention1, is(notNullValue()));
        assertThat("Expired Value was not correctly set", sdkTransactionRetention1.getExpired(), is(10));
        assertThat("Sent Value was not correctly set", sdkTransactionRetention1.getSent(), is(20));
        assertThat("Archived Value was not correctly set", sdkTransactionRetention1.getArchived(), is(30));
        assertThat("Completed Value was not correctly set", sdkTransactionRetention1.getCompleted(), is(40));
        assertThat("Declined Value was not correctly set", sdkTransactionRetention1.getDeclined(), is(50));
        assertThat("Draft Value was not correctly set", sdkTransactionRetention1.getDraft(), is(60));
        assertThat("OptedOut Value was not correctly set", sdkTransactionRetention1.getOptedOut(), is(70));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkTransactionRetention1 = buildSdkTransactionRetention();
        apiTransactionRetention1 = new TransactionRetentionConverter(sdkTransactionRetention1).toAPITransactionRetention();

        assertThat("Converter returned a null api object for a non null sdk object", apiTransactionRetention1, is(notNullValue()));
        assertThat("Expired Value was not correctly set", apiTransactionRetention1.getExpired(), is(10));
        assertThat("Sent Value was not correctly set", apiTransactionRetention1.getSent(), is(20));
        assertThat("Archived Value was not correctly set", apiTransactionRetention1.getArchived(), is(30));
        assertThat("Completed Value was not correctly set", apiTransactionRetention1.getCompleted(), is(40));
        assertThat("Declined Value was not correctly set", apiTransactionRetention1.getDeclined(), is(50));
        assertThat("Draft Value was not correctly set", apiTransactionRetention1.getDraft(), is(60));
        assertThat("OptedOut Value was not correctly set", apiTransactionRetention1.getOptedOut(), is(70));
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