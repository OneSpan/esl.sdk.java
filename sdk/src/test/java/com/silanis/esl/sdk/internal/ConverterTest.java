package com.silanis.esl.sdk.internal;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-06-03.
 */
public class ConverterTest {

    public static final String jenkinsApiKey = "amVua2luc1VzZXJJZDpCc2JwMnlzSUFEZ0g=";
    public static final String expectedJenkinsUID = "jenkinsUserId";

    @Test
    public void apiKeyToUIDTest() {
        String result = Converter.apiKeyToUID(jenkinsApiKey);

        assertThat("The converted UID from api key is incorrect.", result, is(expectedJenkinsUID));
    }
}
