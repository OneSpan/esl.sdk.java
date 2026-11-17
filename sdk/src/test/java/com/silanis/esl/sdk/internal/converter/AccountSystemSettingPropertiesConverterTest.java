package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.internal.Serialization;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class AccountSystemSettingPropertiesConverterTest {

    private static final String EXISTING_FIELDS = "\"senderLoginMaxFailedAttempts\":2,"
            + "\"loginSessionTimeout\":60000,"
            + "\"sessionTimeoutWarning\":200000,"
            + "\"orderLastNameFirstName\":false";

    @Test
    public void convertResponseWithSigningDateFormatSettingsToSDK() {
        String response = "{" + EXISTING_FIELDS + ","
                + "\"signingDateDefaultFormat\":\"d MMMM yyyy\","
                + "\"signingDateAllowSenderChange\":true,"
                + "\"signingDateAllowCustomPattern\":false}";

        com.silanis.esl.sdk.AccountSystemSettingProperties sdkProperties = toSDK(response);

        assertThat(sdkProperties.getSigningDateDefaultFormat(), is(equalTo("d MMMM yyyy")));
        assertThat(sdkProperties.getSigningDateAllowSenderChange(), is(true));
        assertThat(sdkProperties.getSigningDateAllowCustomPattern(), is(false));
        assertThat(sdkProperties.getSessionTimeoutWarning(), is(200000));
    }

    @Test
    public void convertResponseWithoutSigningDateFormatSettingsToSDK() {
        String response = "{" + EXISTING_FIELDS + "}";

        com.silanis.esl.sdk.AccountSystemSettingProperties sdkProperties = toSDK(response);

        assertThat(sdkProperties.getSigningDateDefaultFormat(), is(nullValue()));
        assertThat(sdkProperties.getSigningDateAllowSenderChange(), is(nullValue()));
        assertThat(sdkProperties.getSigningDateAllowCustomPattern(), is(nullValue()));
        assertThat(sdkProperties.getSessionTimeoutWarning(), is(200000));
    }

    @Test
    public void convertSDKWithSigningDateFormatSettingsToAPI() {
        com.silanis.esl.sdk.AccountSystemSettingProperties sdkProperties = sdkWithExistingFields();
        sdkProperties.setSigningDateDefaultFormat("d MMMM yyyy");
        sdkProperties.setSigningDateAllowSenderChange(true);
        sdkProperties.setSigningDateAllowCustomPattern(true);

        com.silanis.esl.api.model.AccountSystemSettingProperties apiProperties =
                new AccountSystemSettingPropertiesConverter(sdkProperties).toAPIAccountSystemSettingProperties();

        assertThat(apiProperties.getSigningDateDefaultFormat(), is(equalTo("d MMMM yyyy")));
        assertThat(apiProperties.getSigningDateAllowSenderChange(), is(true));
        assertThat(apiProperties.getSigningDateAllowCustomPattern(), is(true));
    }

    @Test
    public void convertSDKWithoutSigningDateFormatSettingsToAPI() {
        com.silanis.esl.api.model.AccountSystemSettingProperties apiProperties =
                new AccountSystemSettingPropertiesConverter(sdkWithExistingFields()).toAPIAccountSystemSettingProperties();

        assertThat(apiProperties.getSigningDateDefaultFormat(), is(nullValue()));
        assertThat(apiProperties.getSigningDateAllowSenderChange(), is(nullValue()));
        assertThat(apiProperties.getSigningDateAllowCustomPattern(), is(nullValue()));
    }

    private static com.silanis.esl.sdk.AccountSystemSettingProperties toSDK(String response) {
        com.silanis.esl.api.model.AccountSystemSettingProperties apiProperties =
                Serialization.fromJson(response, com.silanis.esl.api.model.AccountSystemSettingProperties.class);
        return new AccountSystemSettingPropertiesConverter(apiProperties).tosdkAccountSystemSettingProperties();
    }

    private static com.silanis.esl.sdk.AccountSystemSettingProperties sdkWithExistingFields() {
        com.silanis.esl.sdk.AccountSystemSettingProperties sdkProperties = new com.silanis.esl.sdk.AccountSystemSettingProperties();
        sdkProperties.setSenderLoginMaxFailedAttempts(2);
        sdkProperties.setLoginSessionTimeout(60000);
        sdkProperties.setSessionTimeoutWarning(200000);
        sdkProperties.setOrderLastNameFirstName(false);
        return sdkProperties;
    }
}
