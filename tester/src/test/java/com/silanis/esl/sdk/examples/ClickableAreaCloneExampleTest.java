package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ClickableAreaAlignment;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.ClickableAreaCloneExample.CHECKBOX_ID;
import static com.silanis.esl.sdk.examples.ClickableAreaCloneExample.CLICKABLE_AREA_HEIGHT;
import static com.silanis.esl.sdk.examples.ClickableAreaCloneExample.CLICKABLE_AREA_WIDTH;
import static com.silanis.esl.sdk.examples.ClickableAreaCloneExample.DOCUMENT_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClickableAreaCloneExampleTest {
    @Test
    public void verifyResult() {
        ClickableAreaCloneExample example = new ClickableAreaCloneExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if (field.getId().toString().equals(CHECKBOX_ID)) {
                    assertThat(field.getClickableArea().getWidth(), is(CLICKABLE_AREA_WIDTH));
                    assertThat(field.getClickableArea().getHeight(), is(CLICKABLE_AREA_HEIGHT));
                    assertThat(field.getClickableArea().getAlignment(), is(ClickableAreaAlignment.CENTER));
                }
            }
        }
    }
}
