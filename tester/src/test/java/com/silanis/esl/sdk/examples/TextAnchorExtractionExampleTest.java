package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by dave on 24/03/14.
 */
public class TextAnchorExtractionExampleTest {
    private static final double MAX_ERROR_AFTER_SCALING = 0.65;

    @Test
    public void verifyResult() {
        TextAnchorExtractionExample example = new TextAnchorExtractionExample( Props.get() );
        example.run();

        DocumentPackage retrievedPackage = example.getRetrievedPackage();

        Document document = retrievedPackage.getDocument( example.DOCUMENT_NAME );

        for ( Signature signature : document.getSignatures() ) {
            for ( Field field : signature.getFields() ) {
                assertThat( "Field's width was incorrectly returned.", field.getWidth(), is( greaterThanOrEqualTo(-MAX_ERROR_AFTER_SCALING + (double) example.FIELD_WIDTH) ) );
                assertThat( "Field's width was incorrectly returned.", field.getWidth(), is( lessThanOrEqualTo(MAX_ERROR_AFTER_SCALING + (double) example.FIELD_WIDTH) ) );
                assertThat( "Field's height was incorrectly returned.", field.getHeight(), is( greaterThanOrEqualTo(-MAX_ERROR_AFTER_SCALING + (double) example.FIELD_HEIGHT) ) );
                assertThat( "Field's height was incorrectly returned.", field.getHeight(), is( lessThanOrEqualTo(MAX_ERROR_AFTER_SCALING + (double) example.FIELD_HEIGHT) ) );
            }
        }
    }
}
