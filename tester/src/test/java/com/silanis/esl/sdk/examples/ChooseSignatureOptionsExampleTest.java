package com.silanis.esl.sdk.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.silanis.esl.sdk.ChooseSignatureStyleType;

public class ChooseSignatureOptionsExampleTest {

    @Test
    public void verifyResult() {

        ChooseSignatureOptionsExample example = new ChooseSignatureOptionsExample();
        example.run();

        //assert Choose Signature Settings after patch
        assertFalse("'allowStyling' should be false", example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getAllowStyling());
        assertFalse("'allowMobileSigning' should be false", example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getAllowMobileSigning());
        assertEquals(ChooseSignatureStyleType.DRAW, example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getDefaultSignatureType());
        assertEquals(2, example.chooseSignatureSettingsAfterPatch.getChooseSignatureOptions().getFontsPerWritingSystem()
                .get("latin")
                .size());

    }
}