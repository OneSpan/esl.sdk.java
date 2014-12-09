package com.silanis.esl.sdk.examples;

import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExampleTest {
    @Test
    public void verifyResult() {
        SignatureManipulationExample signatureManipulationExample = new SignatureManipulationExample(Props.get());
        signatureManipulationExample.run();

        // Test if all signatures are added properly
        Map<String,Signature> signatureMap = convertListToMap(signatureManipulationExample.addedSignatures);

        assertThat("Signature 1 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email1), is(true));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email2), is(true));
        assertThat("Signature 3 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email3), is(true));

        // Test if signature1 is deleted properly
        signatureMap = convertListToMap(signatureManipulationExample.deletedSignatures);

        assertThat("Signature 1 was not deleted correctly", signatureMap.containsKey(signatureManipulationExample.email1), is(false));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email2), is(true));
        assertThat("Signature 3 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email3), is(true));

        // Test if signature3 is updated properly and is assigned to signer1
        signatureMap = convertListToMap(signatureManipulationExample.modifiedSignatures);

        assertThat("Signature 1 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email1), is(true));
        assertThat("Signature 2 was not set correctly", signatureMap.containsKey(signatureManipulationExample.email2), is(true));
        assertThat("Signature 3 was not modified correctly", signatureMap.containsKey(signatureManipulationExample.email3), is(false));

        // Test if the signatures were updated with the new list of signatures
        signatureMap = convertListToMap(signatureManipulationExample.updatedSignatures);

        assertThat("Signature 1 was not updated correctly", signatureMap.containsKey(signatureManipulationExample.email1), is(false));
        assertThat("Signature 2 was not updated correctly", signatureMap.containsKey(signatureManipulationExample.email2), is(true));
        assertThat("Signature 3 was not updated correctly", signatureMap.containsKey(signatureManipulationExample.email3), is(true));
        assertThat("Signature 2 was not updated with the name field", Iterables.get(signatureMap.get(signatureManipulationExample.email2).getFields(), 0).getStyle(), is(FieldStyle.BINDING_NAME));
    }

    private Map<String, Signature> convertListToMap(Collection<Signature> signatures){
        Map<String,Signature> signatureMap = new HashMap<String, Signature>();
        for (Signature signature : signatures){
            signatureMap.put(signature.getSignerEmail(), signature);
        }

        return signatureMap;
    }
}
