package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.CompleteSummaryOptions;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class CompleteSummaryOptionsBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        CompleteSummaryOptionsBuilder builder = CompleteSummaryOptionsBuilder.newCompleteSummaryOptions();
        builder.withFrom();
        builder.withTitle();
        builder.withMessage();
        builder.withDownload();
        builder.withReview();
        builder.withContinue();

        CompleteSummaryOptions result = builder.build();
        assertThat( "build returned a null object", result, is( notNullValue() ) );
        assertTrue( "'from' was not set correctly", result.getFrom());
        assertTrue( "'title' was not set correctly", result.getTitle());
        assertTrue( "'message' was not set correctly", result.getMessage());
        assertTrue( "'download' was not set correctly", result.getDownload());
        assertTrue( "'review' was not set correctly", result.getReview());
        assertTrue( "'continue' was not set correctly", result.getContinue());

    }
}
