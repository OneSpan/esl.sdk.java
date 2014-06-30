package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExampleTest {
    @Test
    public void verifyResult() {
        SignatureManipulationExample signatureManipulationExample = new SignatureManipulationExample(Props.get());
        signatureManipulationExample.run();
        DocumentPackage documentPackage = signatureManipulationExample.createdPackage;

        Document sdkDocument = documentPackage.getDocument("First Document");
        Signature sdkSignature1 = null;
        Signature sdkSignature2 = null;

        if(sdkDocument != null){
            for(Signature signature : sdkDocument.getSignatures()){
                if(signature.getSignerEmail().equals(signatureManipulationExample.email1)){
                    sdkSignature1 = signature;
                }
                if(signature.getSignerEmail().equals(signatureManipulationExample.email2)){
                    sdkSignature2 = signature;
                }
            }
        }

        assertThat("Document was not uploaded correctly", sdkDocument, is(notNullValue()));
        assertThat("Signature 1 was not deleted correctly", sdkSignature1, is(nullValue()));
        assertThat("Signature 2 was not set correctly", sdkSignature2, is(notNullValue()));
    }
}
