package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SupportedLanguages;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AccountLimitSupportedLanguagesSettingsConverterTest implements ConverterTest{

    @Test
    public void convertNullSDKToAPI() {
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter((SupportedLanguages) null);
        Assert.assertNull(converter.toAPIAccountLimitSupportedLanguagesSettings());
    }

    @Test
    public void convertNullAPIToSDK() {
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter((com.silanis.esl.api.model.SupportedLanguages) null);
        Assert.assertNull(converter.toAPIAccountLimitSupportedLanguagesSettings());
    }

    @Test
    public void convertNullSDKToSDK() {
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter((SupportedLanguages) null);
        Assert.assertNull(converter.toAPIAccountLimitSupportedLanguagesSettings());
    }

    @Test
    public void convertNullAPIToAPI() {
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter((com.silanis.esl.api.model.SupportedLanguages) null);
        Assert.assertNull(converter.toAPIAccountLimitSupportedLanguagesSettings());
    }

    @Override
    public void convertSDKToSDK() {

    }

    @Override
    public void convertAPIToAPI() {

    }

    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.SupportedLanguages supportedLanguagesApi = new com.silanis.esl.api.model.SupportedLanguages("fr", Arrays.asList("fr", "en"));
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter(supportedLanguagesApi);
        SupportedLanguages supportedLanguagesSdk = converter.tosdkAccountLimitSupportedLanguagesSettings();
        Assert.assertEquals("fr", supportedLanguagesSdk.getDefaultSignerLanguage());
    }

    @Test
    public void convertSDKToAPI() {
        SupportedLanguages supportedLanguagesApi = new SupportedLanguages("fr", Arrays.asList("fr", "en"));
        AccountLimitSupportedLanguagesSettingsConverter converter = new AccountLimitSupportedLanguagesSettingsConverter(supportedLanguagesApi);
        com.silanis.esl.api.model.SupportedLanguages supportedLanguagesSdk = converter.toAPIAccountLimitSupportedLanguagesSettings();
        Assert.assertEquals("fr", supportedLanguagesSdk.getDefaultSignerLanguage());
    }


}
