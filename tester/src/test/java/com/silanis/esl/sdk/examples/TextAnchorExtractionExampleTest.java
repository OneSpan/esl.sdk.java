package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.TextAnchorExtractionExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

/**
 * Created by dave on 24/03/14.
 */
public class TextAnchorExtractionExampleTest {
    private static final double MAX_ERROR_AFTER_SCALING = 0.75;

    @Test
    public void verifyResult() {
        TextAnchorExtractionExample example = new TextAnchorExtractionExample( Props.get() );
        example.run();

        Document document = example.retrievedPackage.getDocument( DOCUMENT_NAME );

        for ( Signature signature : document.getSignatures() ) {
            for ( Field field : signature.getFields() ) {
                assertThat( "Field's width was incorrectly returned.", field.getWidth(), closeTo(FIELD_WIDTH, MAX_ERROR_AFTER_SCALING) );
                assertThat( "Field's height was incorrectly returned.", field.getHeight(), closeTo(FIELD_HEIGHT, MAX_ERROR_AFTER_SCALING) );
            }
        }
    }
}
