package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ClickableAreaAlignment;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.ClickableAreaExample.ALIGNED_CHECKBOX_ID;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ALIGNED_CLICKABLE_AREA_ALIGNMENT;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ALIGNED_CLICKABLE_AREA_HEIGHT;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ALIGNED_CLICKABLE_AREA_WIDTH;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ADDED_CHECKBOX_ID;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ADDED_CLICKABLE_AREA_HEIGHT;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.ADDED_CLICKABLE_AREA_WIDTH;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.CHECKBOX_ID;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.DOCUMENT_NAME;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.MODIFIED_CLICKABLE_AREA_HEIGHT;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.MODIFIED_CLICKABLE_AREA_WIDTH;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.RADIO_CLICKABLE_AREA_HEIGHT;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.RADIO_CLICKABLE_AREA_WIDTH;
import static com.silanis.esl.sdk.examples.ClickableAreaExample.RADIO_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClickableAreaExampleTest {
    @Test
    public void verifyResult() {
        ClickableAreaExample example = new ClickableAreaExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if (field.getId().toString().equals(CHECKBOX_ID)) {
                    // Modified after creation via updateField(); reflects the post-modification size.
                    assertThat(field.getClickableArea().getWidth(), is(MODIFIED_CLICKABLE_AREA_WIDTH));
                    assertThat(field.getClickableArea().getHeight(), is(MODIFIED_CLICKABLE_AREA_HEIGHT));
                    assertThat(field.getClickableArea().getAlignment(), is(ClickableAreaAlignment.CENTER));

                }
                if (field.getId().toString().equals(ALIGNED_CHECKBOX_ID)) {
                    assertThat(field.getClickableArea().getWidth(), is(ALIGNED_CLICKABLE_AREA_WIDTH));
                    assertThat(field.getClickableArea().getHeight(), is(ALIGNED_CLICKABLE_AREA_HEIGHT));
                    assertThat(field.getClickableArea().getAlignment(), is(ALIGNED_CLICKABLE_AREA_ALIGNMENT));
                }
                if (field.getId().toString().equals(RADIO_ID)) {
                    assertThat(field.getClickableArea().getWidth(), is(RADIO_CLICKABLE_AREA_WIDTH));
                    assertThat(field.getClickableArea().getHeight(), is(RADIO_CLICKABLE_AREA_HEIGHT));
                    assertThat(field.getClickableArea().getAlignment(), is(ClickableAreaAlignment.CENTER));
                }
                // Added after creation via addField().
                if (field.getId().toString().equals(ADDED_CHECKBOX_ID)) {
                    assertThat(field.getClickableArea().getWidth(), is(ADDED_CLICKABLE_AREA_WIDTH));
                    assertThat(field.getClickableArea().getHeight(), is(ADDED_CLICKABLE_AREA_HEIGHT));
                    assertThat(field.getClickableArea().getAlignment(), is(ClickableAreaAlignment.CENTER));
                }
            }
        }
    }
}
