package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.OverviewOptions;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class OverviewOptionsBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        OverviewOptionsBuilder builder = OverviewOptionsBuilder.newOverviewOptions();
        builder.withTitle();
        builder.withBody();
        builder.withDocumentSection();
        builder.withUploadSection();

        OverviewOptions result = builder.build();
        assertThat( "build returned a null object", result, is( notNullValue() ) );
        assertTrue( "'title' was not set correctly", result.getTitle());
        assertTrue( "'body' was not set correctly", result.getBody());
        assertTrue( "'documentSection' was not set correctly", result.getDocumentSection());
        assertTrue( "'uploadSection' was not set correctly", result.getUploadSection());

    }
}
