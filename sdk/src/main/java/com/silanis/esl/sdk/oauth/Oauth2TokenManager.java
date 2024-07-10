package com.silanis.esl.sdk.oauth;

import java.time.Instant;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Oauth2TokenManager {

    public static final int ACCESS_TOKEN_EXPIRATION_LEEWAY = 10;

    ObjectMapper objectMapper = new ObjectMapper();

    public boolean isOAuth2TokenExpired(String oAuthAccessToken) {
        String[] chunks = oAuthAccessToken.split("\\.");

        String payload = new String(Base64.getUrlDecoder().decode(chunks[1]));

        JsonNode payloadJson;
        try {
            payloadJson = objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        long unixEpochTime = payloadJson.path("exp").asLong();
        Instant tokenExpiresAt = Instant.ofEpochSecond(unixEpochTime);
        Instant now = Instant.now();

        return now.isAfter(tokenExpiresAt.minusSeconds(ACCESS_TOKEN_EXPIRATION_LEEWAY));
    }
}
