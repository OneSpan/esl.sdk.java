package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Auth;
import com.silanis.esl.api.model.AuthScheme;
import com.silanis.esl.sdk.internal.converter.AuthenticationConverter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class AuthenticationTest {
    @Test
    public void constructorWithMethod() {
        for ( AuthenticationMethod authenticationMethod : AuthenticationMethod.values() ) {
            Authentication authentication = new Authentication( authenticationMethod );
            assertThat( "method is not set correctly for " + authenticationMethod.name(), authentication.getMethod(), is( equalTo( authenticationMethod ) ) );
        }
    }

    private List<Challenge> challenges;

    private Authentication newChallengeAuthentication() {
        challenges = new ArrayList<Challenge>();
        challenges.add( new Challenge( "one", "two" ) );
        challenges.add( new Challenge( "three", "four" ) );
        challenges.add( new Challenge( "four", "five" ) );
        return new Authentication( challenges );
    }

    @Test
    public void constructorWithListOfChallenges() {
        Authentication authentication = newChallengeAuthentication();

        assertThat( "method not set correctly", authentication.getMethod(), is( equalTo( AuthenticationMethod.CHALLENGE ) ) );
        assertThat( "challenges list is null", authentication.getChallenges(), is( notNullValue() ) );
        assertThat( "challenges list is wrong size", authentication.getChallenges().size(), is( equalTo( challenges.size() ) ) );
        for ( int i = 0; i < challenges.size(); i++ ) {
            assertThat( "challenge question #" + i + " is not set correctly", authentication.getChallenges().get( i ).getQuestion(), is( equalTo( challenges.get( i ).getQuestion() ) ) );
            assertThat( "challenge answer #" + i + " is not set correctly", authentication.getChallenges().get( i ).getAnswer(), is( equalTo( challenges.get( i ).getAnswer() ) ) );
        }
    }

    private String phoneNumber;

    public Authentication newSMSAuthentication() {
        phoneNumber = "1234567890";
        return new Authentication( phoneNumber );
    }

    @Test
    public void constructorWithTelephoneNumber() {
        Authentication authentication = newSMSAuthentication();

        assertThat( "authentication method is not being set to sms", authentication.getMethod(), is( equalTo( AuthenticationMethod.SMS ) ) );
        assertThat( "phone number is not set correctly", authentication.getPhoneNumber(), is( equalTo( phoneNumber ) ) );
    }

    private Authentication newEmailAuthentication() {
        return new Authentication( AuthenticationMethod.EMAIL );
    }

    @Test
    public void emailAuthToAPIAuth() {
        Authentication authentication = newEmailAuthentication();
        Auth auth = new AuthenticationConverter(authentication).toAPIAuthentication();
        assertThat("Null value was returned by converter", auth, is(notNullValue()));
        assertThat( "AuthScheme was not set to NONE", auth.getScheme(), is( equalTo( AuthScheme.NONE ) ) );
    }

    @Test
    public void challengeAuthToAPIAuth() {
        Authentication authentication = newChallengeAuthentication();
        Auth auth = new AuthenticationConverter(authentication).toAPIAuthentication();
        assertThat("Null value was returned by converter", auth, is(notNullValue()));
        assertThat( "AuthScheme was not set to CHALLENGE", auth.getScheme(), is( equalTo( AuthScheme.CHALLENGE ) ) );
        assertThat( "Challenge list was set to null", auth.getChallenges(), is( notNullValue() ) );
        assertThat( "Challenge list did not contain the expected number of elements", auth.getChallenges().size(), is( equalTo( challenges.size() ) ) );
        for ( int i = 0; i < challenges.size(); i++ ) {
            assertThat( "Challenge question #" + i + " + was not set correctly", auth.getChallenges().get( i ).getQuestion(), is( equalTo( challenges.get( i ).getQuestion() ) ) );
            assertThat( "Challenge answer #" + i + " + was not set correctly", auth.getChallenges().get( i ).getAnswer(), is( equalTo( challenges.get( i ).getAnswer() ) ) );
        }
    }

    @Test
    public void smsAuthToAPIAuth() {
        Authentication authentication = newSMSAuthentication();
        Auth auth = new AuthenticationConverter(authentication).toAPIAuthentication();
        assertThat("AuthScheme was not set to SMS", auth.getScheme(), is(equalTo(AuthScheme.SMS)));
        assertThat( "Challenges list was null (should hold phone number)", auth.getChallenges(), notNullValue() );
        assertThat( "Challenges list was not length 1", auth.getChallenges().size(), is( equalTo( 1 ) ) );
        assertThat( "First challenge item should hold the phone number as question, but didn't", auth.getChallenges().get( 0 ).getQuestion(), is( equalTo( phoneNumber ) ) );
        assertThat( "First challenge answer should be blank", auth.getChallenges().get( 0 ).getAnswer(), is( notNullValue() ) );
    }
}
