package com.silanis.esl.sdk.apitoken;

import org.junit.Assert;
import org.junit.Test;

public class ApiTokenConfigTest {
    @Test
    public void test() {
        ApiTokenConfig apiTokenConfig = ApiTokenConfig.newBuilder()
            .baseUrl("https://site.com/")
            .clientAppId("APP_ID")
            .clientAppSecret("APP_SECRET")
            .tokenType(ApiTokenConfig.TokenType.SENDER)
            .senderEmail("bob@me.com")
            .build();

        Assert.assertEquals("https://site.com", apiTokenConfig.getBaseUrl());
        Assert.assertEquals("APP_ID", apiTokenConfig.getClientAppId());
        Assert.assertEquals("APP_SECRET", apiTokenConfig.getClientAppSecret());
        Assert.assertEquals(ApiTokenConfig.TokenType.SENDER, apiTokenConfig.getTokenType());
        Assert.assertEquals("bob@me.com", apiTokenConfig.getSenderEmail());
    }
}