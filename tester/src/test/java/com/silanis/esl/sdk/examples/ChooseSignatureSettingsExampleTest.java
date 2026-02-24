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
        assertFalse("'allowStyling' should be false", example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getAllowStyling());
        assertFalse("'allowMobileSigning' should be false", example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getAllowMobileSigning());
        assertEquals(ChooseSignatureStyleType.DRAW, example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getDefaultSignatureType());
        assertEquals(2, example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getFontsPerWritingSystem()
                .get("latin")
                .size());

        //assert Choose Signature Settings after delete
        assertTrue("'allowStyling' should be false", example.chooseSignatureSettingsAfterDelete.getChooseSignatureOptions().getAllowStyling());
        assertTrue("'allowMobileSigning' should be false", example.chooseSignatureSettingsAfterDelete.getChooseSignatureOptions().getAllowMobileSigning());
        assertEquals(ChooseSignatureStyleType.STYLE, example.chooseSignatureSettingsAfterDelete.getChooseSignatureOptions().getDefaultSignatureType());
        assertEquals(13, example.chooseSignatureSettingsAfterDelete.getChooseSignatureOptions().getFontsPerWritingSystem()
                .get("latin")
                .size());

    }
}