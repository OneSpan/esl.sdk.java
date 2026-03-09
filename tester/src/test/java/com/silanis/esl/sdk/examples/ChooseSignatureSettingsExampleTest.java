package com.silanis.esl.sdk.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.silanis.esl.sdk.ChooseSignatureStyleType;

public class ChooseSignatureSettingsExampleTest {

    @Test
    public void verifyResult() {

        ChooseSignatureSettingsExample example = new ChooseSignatureSettingsExample();
        example.run();

        //assert Choose Signature Settings after patch
        assertFalse("'allowStyling' should be false", example.chooseSignatureSettingsAfterPatch.getSignature().getAllowStyling());
        assertFalse("'allowMobileSigning' should be false", example.chooseSignatureSettingsAfterPatch.getSignature().getAllowMobileSigning());
        assertEquals(ChooseSignatureStyleType.UPLOAD.name(), example.chooseSignatureSettingsAfterPatch.getSignature().getDefaultSignatureType());
        assertEquals(2, example.chooseSignatureSettingsAfterPatch.getSignature().getFontsPerWritingSystem()
                .get("latin")
                .size());

        //assert Choose Signature Settings after delete
        assertTrue("'allowStyling' should be false", example.chooseSignatureSettingsAfterDelete.getSignature().getAllowStyling());
        assertTrue("'allowMobileSigning' should be false", example.chooseSignatureSettingsAfterDelete.getSignature().getAllowMobileSigning());
        assertEquals(ChooseSignatureStyleType.STYLE.name(), example.chooseSignatureSettingsAfterDelete.getSignature().getDefaultSignatureType());
        assertEquals(13, example.chooseSignatureSettingsAfterDelete.getSignature().getFontsPerWritingSystem()
                .get("latin")
                .size());

    }
}