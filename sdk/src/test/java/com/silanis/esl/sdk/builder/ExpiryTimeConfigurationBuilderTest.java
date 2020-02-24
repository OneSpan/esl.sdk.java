package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ExpiryTimeConfiguration;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExpiryTimeConfigurationBuilderTest {

    @Test
    public void withSpecifiedValues() {
        ExpiryTimeConfigurationBuilder builder = ExpiryTimeConfigurationBuilder.newExpiryTimeConfiguration()
                .withMaximumRemainingDays(90)
                .withRemainingDays(80);

        ExpiryTimeConfiguration result = builder.build();

        assertThat( "build returned a null object", result, is( Matchers.notNullValue() ) );
        assertThat( "Maximum remaining days value is not set properly", result.getMaximumRemainingDays(), is( 90 ) );
        assertThat( "Remaining days value is not set properly", result.getRemainingDays(), is( 80 ) );
    }
}
