package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountSignatureLayoutExampleTest {

    @Test
    public void verifyResult() {

        AccountSignatureLayoutExample example = new AccountSignatureLayoutExample();
        example.run();

        assertNotNull(example.patchedSignatureLayout.getLogo().getImage());
        assertEquals(example.SIGNATURE_LOGO_IMAGE_BASE64,example.patchedSignatureLayout.getLogo().getImage());

    }
}
