package com.silanis.esl.sdk.examples;

import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.silanis.esl.sdk.FieldStyle.BOUND_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chi-wing on 6/19/14.
 */
public class SignatureManipulationExampleTest {
    @Test
    public void verifyResult() {
        SignatureManipulationExample example = new SignatureManipulationExample();
        example.run();

        // Test if all signatures are added properly
        Map<String,Signature> signatureMap = convertListToMap(example.addedSignatures);

        assertTrue("Signature 1 was not set correctly", signatureMap.containsKey(example.email1));
        assertTrue("Signature 2 was not set correctly", signatureMap.containsKey(example.email2));
        assertTrue("Signature 3 was not set correctly", signatureMap.containsKey(example.email3));

        // Test if signature1 is deleted properly
        signatureMap = convertListToMap(example.deletedSignatures);

        assertFalse("Signature 1 was not deleted correctly", signatureMap.containsKey(example.email1));
        assertTrue("Signature 2 was not set correctly", signatureMap.containsKey(example.email2));
        assertTrue("Signature 3 was not set correctly", signatureMap.containsKey(example.email3));

        // Test if signature3 is updated properly and is assigned to signer1
        signatureMap = convertListToMap(example.modifiedSignatures);

        assertTrue("Signature 1 was not set correctly", signatureMap.containsKey(example.email1));
        assertTrue("Signature 2 was not set correctly", signatureMap.containsKey(example.email2));
        assertFalse("Signature 3 was not modified correctly", signatureMap.containsKey(example.email3));

        // Test if the signatures were updated with the new list of signatures
        signatureMap = convertListToMap(example.updatedSignatures);

        assertFalse("Signature 1 was not updated correctly", signatureMap.containsKey(example.email1));
        assertTrue("Signature 2 was not updated correctly", signatureMap.containsKey(example.email2));
        assertTrue("Signature 3 was not updated correctly", signatureMap.containsKey(example.email3));
        assertThat("Signature 2 was not updated with the name field", Iterables.get(signatureMap.get(example.email2).getFields(), 0).getStyle(), is(BOUND_NAME));
    }

    private Map<String, Signature> convertListToMap(Collection<Signature> signatures){
        Map<String,Signature> signatureMap = new HashMap<String, Signature>();
        for (Signature signature : signatures){
            signatureMap.put(signature.getSignerEmail(), signature);
        }

        return signatureMap;
    }
}
