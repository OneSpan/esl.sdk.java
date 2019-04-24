package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Challenge;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class ChallengeConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.Challenge sdkChallenge = null;
    private com.silanis.esl.api.model.AuthChallenge apiChallenge = null;
    private ChallengeConverter converter;

    public static final String QUESTION = "What's your favorite sport? (answer: golf)";
    public static final String ANSWER = "golf";

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkChallenge = null;
        converter = new ChallengeConverter(sdkChallenge);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIChallenge(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiChallenge = null;
        converter = new ChallengeConverter(apiChallenge);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKChallenge(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkChallenge = null;
        converter = new ChallengeConverter(sdkChallenge);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKChallenge(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiChallenge = null;
        converter = new ChallengeConverter(apiChallenge);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIChallenge(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkChallenge = createTypicalSDKChallenge();
        sdkChallenge = new ChallengeConverter(sdkChallenge).toSDKChallenge();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkChallenge, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkChallenge, is(sdkChallenge));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiChallenge = createTypicalAPIAuthChallenge();
        apiChallenge = new ChallengeConverter(apiChallenge).toAPIChallenge();

        assertThat("Converter returned a null api object for a non null api object", apiChallenge, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiChallenge, is(apiChallenge));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiChallenge = createTypicalAPIAuthChallenge();
        sdkChallenge = new ChallengeConverter(apiChallenge).toSDKChallenge();

        assertThat("Converter returned a null sdk object for a non null api object", sdkChallenge, notNullValue());
        assertThat("Question was not correctly set", sdkChallenge.getQuestion(), is(apiChallenge.getQuestion()));
        assertThat("Answer was not correctly set", sdkChallenge.getAnswer(), is(apiChallenge.getAnswer()));
        assertThat("MaskOption was not correctly set", sdkChallenge.getMaskOption(), is(Challenge.MaskOptions.None));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkChallenge = createTypicalSDKChallenge();
        apiChallenge = new ChallengeConverter(sdkChallenge).toAPIChallenge();

        assertThat("Converter returned a null api object for a non null sdk object", apiChallenge, notNullValue());
        assertThat("Question was not correctly set", apiChallenge.getQuestion(), is(sdkChallenge.getQuestion()));
        assertThat("Answer was not correctly set", apiChallenge.getAnswer(), is(sdkChallenge.getAnswer()));
        assertThat("MaskOption was not correctly set", apiChallenge.getMaskInput(), is(false));
    }

    private com.silanis.esl.sdk.Challenge createTypicalSDKChallenge() {
        return new Challenge(QUESTION, ANSWER);
    }

    private com.silanis.esl.api.model.AuthChallenge createTypicalAPIAuthChallenge() {
        com.silanis.esl.api.model.AuthChallenge apiAuthChallenge = new com.silanis.esl.api.model.AuthChallenge();
        apiAuthChallenge.setQuestion(QUESTION);
        apiAuthChallenge.setAnswer(ANSWER);
        apiAuthChallenge.setMaskInput(false);

        return apiAuthChallenge;
    }
}
