package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.AuthChallenge;
import com.silanis.esl.sdk.Authentication;
import com.silanis.esl.sdk.Challenge;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 22/11/13
 * Time: 11:14 AM
 *
 * Tests for Authentication Converter.
 */
public class AuthenticationConverterTest implements ConverterTest{

    @Override
    @Test
    public void convertNullAPIToSDK() {
        // Not applicable.
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        // Not applicable.
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        // Not applicable.
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        // Not applicable.
    }

    @Test
    public void convertNullSDKToAPI() {
        Authentication authentication = null;
        AuthenticationConverter converter = new AuthenticationConverter( authentication );
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIAuthentication(), is( nullValue() ) );
    }

    @Test
    public void convertNullAPIToAPI() {
        com.silanis.esl.api.model.Auth authentication = null;
        AuthenticationConverter converter = new AuthenticationConverter( authentication );
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIAuthentication(), is( nullValue() ) );

    }

    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.Auth authentication = createTypicalAPIAuthentication();
        AuthenticationConverter converter = new AuthenticationConverter( authentication );
        com.silanis.esl.api.model.Auth result = converter.toAPIAuthentication();
        assertThat( "Converter returned a null api object for a non null api object", result, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", result, is( equalTo( authentication ) ) );
    }

    @Test
    public void convertSDKToAPI() {
        Authentication authentication = createTypicalSDKAuthentication();
        AuthenticationConverter converter = new AuthenticationConverter( authentication );
        com.silanis.esl.api.model.Auth result = converter.toAPIAuthentication();
        assertThat( "Converter returned a null api object for a non null sdk object", result, is( notNullValue() ) );
        assertThat( "scheme was not set correctly", result.getScheme(), is( equalTo(authentication.getMethod().toString())));
        assertThat("Challenge question was not set correctly", result.getChallenges().get(0).getQuestion(), is(equalTo(authentication.getChallenges().get(0).getQuestion())));
    }

    private Authentication createTypicalSDKAuthentication() {

        List<Challenge> sdkChallenges = new ArrayList<Challenge>();
        sdkChallenges.add(new Challenge("What is the name of your dog?", "John"));
        Authentication result = new Authentication(sdkChallenges);
        return result;
    }

    private com.silanis.esl.api.model.Auth createTypicalAPIAuthentication() {
        com.silanis.esl.api.model.Auth result = new com.silanis.esl.api.model.Auth();
        com.silanis.esl.api.model.AuthChallenge authChallenge = new com.silanis.esl.api.model.AuthChallenge();
        authChallenge.setQuestion("What is the name of your dog?");
        authChallenge.setAnswer("John");
        List<com.silanis.esl.api.model.AuthChallenge> authChallenges = new ArrayList<AuthChallenge>();
        authChallenges.add(authChallenge);

        result.setChallenges(authChallenges);
        return result;
    }

}
