package com.silanis.esl.sdk.apitoken;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiTokenAccessRequestTest {
    @Test
    public void test() {
        ApiTokenAccessRequest apiTokenAccessRequest = ApiTokenAccessRequest.newBuilder()
            .clientId("APP_ID")
            .secret("APP_SECRET")
            .type(ApiTokenConfig.TokenType.SENDER)
            .email("bob@me.com")
            .build();

        Assert.assertEquals("APP_ID", apiTokenAccessRequest.getClientId());
        Assert.assertEquals("APP_SECRET", apiTokenAccessRequest.getSecret());
        Assert.assertEquals(ApiTokenConfig.TokenType.SENDER, apiTokenAccessRequest.getType());
        Assert.assertEquals("bob@me.com", apiTokenAccessRequest.getEmail());
    }

    @Test
    public void testJsonSerialization() throws IOException {
        ApiTokenAccessRequest apiTokenAccessRequest = ApiTokenAccessRequest.newBuilder()
            .clientId("APP_ID")
            .secret("APP_SECRET")
            .type(ApiTokenConfig.TokenType.SENDER)
            .email("bob@me.com")
            .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(apiTokenAccessRequest);

        ApiTokenAccessRequest apiTokenAccessRequest2 = objectMapper.readValue(json, ApiTokenAccessRequest.class);

        Assert.assertEquals(apiTokenAccessRequest, apiTokenAccessRequest2);
    }
}