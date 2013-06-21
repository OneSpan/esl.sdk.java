package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author ehardy
 */
public class EslClientTest {

    @Test
    public void appendsServicePathToBaseURL() {
        assertThat(new EslClient("api-key", "https://api.e-signlive.com").getBaseURL(),
                is(equalTo("https://api.e-signlive.com/aws/rest/services")));
        assertThat(new EslClient("api-key", "https://api.e-signlive.com/").getBaseURL(),
                is(equalTo("https://api.e-signlive.com/aws/rest/services")));
    }

    @Test(expected = EslException.class)
    public void cannotCreateClientWithNullAPIKey() {
        new EslClient(null, "");
    }

    @Test(expected = EslException.class)
    public void cannotCreateClientWithNullURL() {
        new EslClient("api-key", null);
    }
}