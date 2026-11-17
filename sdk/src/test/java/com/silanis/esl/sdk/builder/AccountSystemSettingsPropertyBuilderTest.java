package com.silanis.esl.sdk.builder;

import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.AccountSystemSettingProperties;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.AccountSystemSettingsPropertyBuilder.newAccountSystemSettingsPropertyBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class AccountSystemSettingsPropertyBuilderTest {

    @Test
    public void buildWithSigningDateFormatSettings() {
        AccountSystemSettingProperties properties = newAccountSystemSettingsPropertyBuilder()
                .withSigningDateDefaultFormat("d MMMM yyyy")
                .withSigningDateAllowSenderChange(true)
                .withSigningDateAllowCustomPattern(false)
                .build();

        assertThat(properties.getSigningDateDefaultFormat(), is(equalTo("d MMMM yyyy")));
        assertThat(properties.getSigningDateAllowSenderChange(), is(true));
        assertThat(properties.getSigningDateAllowCustomPattern(), is(false));
    }

    @Test
    public void signingDateFormatSettingsAreNullByDefault() {
        AccountSystemSettingProperties properties = newAccountSystemSettingsPropertyBuilder()
                .withSessionTimeoutWarning(200000)
                .build();

        assertThat(properties.getSigningDateDefaultFormat(), is(nullValue()));
        assertThat(properties.getSigningDateAllowSenderChange(), is(nullValue()));
        assertThat(properties.getSigningDateAllowCustomPattern(), is(nullValue()));
    }

    @Test
    public void payloadHasTheSigningDateFormatSettings() throws Exception {
        AccountSystemSettingProperties properties = newAccountSystemSettingsPropertyBuilder()
                .withSigningDateDefaultFormat("d MMMM yyyy")
                .withSigningDateAllowSenderChange(true)
                .withSigningDateAllowCustomPattern(true)
                .build();

        String payload = JacksonUtil.serialize(properties);

        assertThat(payload, containsString("\"signingDateDefaultFormat\":\"d MMMM yyyy\""));
        assertThat(payload, containsString("\"signingDateAllowSenderChange\":true"));
        assertThat(payload, containsString("\"signingDateAllowCustomPattern\":true"));
    }

    @Test
    public void payloadLeavesOutTheSigningDateFormatSettingsThatAreNotSet() throws Exception {
        AccountSystemSettingProperties properties = newAccountSystemSettingsPropertyBuilder()
                .withSessionTimeoutWarning(200000)
                .build();

        String payload = JacksonUtil.serialize(properties);

        assertThat(payload, not(containsString("signingDate")));
    }
}
