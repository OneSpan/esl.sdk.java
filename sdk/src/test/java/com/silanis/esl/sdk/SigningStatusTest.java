package com.silanis.esl.sdk;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by dave on 23/04/14.
 */
public class SigningStatusTest {
    @Test
    public void signingCompletedAPIToSDK() {
        SigningStatus result = SigningStatus.statusForToken( "SIGNING_COMPLETED" );

        assertThat( SigningStatus.SIGNING_COMPLETE, is( equalTo( result )));
    }
}
