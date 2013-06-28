package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class EslClientTest {

    @Test(expected = EslException.class)
    public void cannotCreateClientWithNullAPIKey() {
        new EslClient(null, "");
    }

    @Test(expected = EslException.class)
    public void cannotCreateClientWithNullURL() {
        new EslClient("api-key", null);
    }
}