package com.silanis.esl.sdk.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.silanis.esl.sdk.apitoken.ApiToken;
import com.silanis.esl.sdk.apitoken.ApiTokenConfig;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class RestClientTest {
    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        wireMockServer = new WireMockServer(wireMockConfig().port(0)); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @After
    public void tearDown() {
        wireMockServer.shutdown();
    }

    @Test
    public void addAuthorizationHeader() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiToken apiToken = new ApiToken();
        apiToken.setAccessToken("ACCESS_TOKEN");
        apiToken.setExpiresAt(1234567890000L);

        stubFor(post(urlEqualTo(ApiTokenConfig.ACCESS_TOKEN_URL))
            .withRequestBody(equalToJson("{\"clientId\":\"APP_ID\",\"secret\":\"APP_SECRET\",\"type\":\"OWNER\"}"))
            .willReturn(aResponse().withBody(objectMapper.writeValueAsBytes(apiToken))));

        ApiTokenConfig apiTokenConfig = ApiTokenConfig.newBuilder()
            .baseUrl("http://localhost:"+wireMockServer.port())
            .clientAppId("APP_ID")
            .clientAppSecret("APP_SECRET")
            .tokenType(ApiTokenConfig.TokenType.OWNER)
            .build();
        RestClient restClient = new RestClient(apiTokenConfig, true, null,
            false, Collections.<String,String>emptyMap());
        HttpGet httpGet = new HttpGet("https://test.onespan.com");

        restClient.addAuthorizationHeader(httpGet);

        Header[] headers = httpGet.getHeaders("Authorization");
        Assert.assertEquals(1, headers.length);
        Assert.assertEquals("Bearer ACCESS_TOKEN", headers[0].getValue());
    }
}