package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageAttributes;
import org.junit.Test;

import java.util.Map;

import static com.silanis.esl.sdk.examples.DocumentPackageAttributesExample.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 5:07 PM
 * 
 * Test DocumentPackageAttributesExample
 */
public class DocumentPackageAttributesExampleTest {

    private static final String ORIGIN_KEY = "origin";

    @Test
    public void verifyResult() {
        DocumentPackageAttributesExample example = new DocumentPackageAttributesExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();
        DocumentPackageAttributes documentPackageAttributes = documentPackage.getAttributes();
        Map<String, Object> attributeMap = documentPackageAttributes.getContents();

        assertThat("Origin key is not setup correctly.", attributeMap.containsKey(ORIGIN_KEY));
        assertThat("Attribute key 1 is not setup correctly.", attributeMap.containsKey(ATTRIBUTE_KEY_1));
        assertThat("Attribute key 2 is not setup correctly.", attributeMap.containsKey(ATTRIBUTE_KEY_2));
        assertThat("Attribute key 3 is not setup correctly.", attributeMap.containsKey(ATTRIBUTE_KEY_3));

        assertThat("Attribute 1 is not setup correctly.", attributeMap.get(ORIGIN_KEY).toString().equals(DYNAMICS_2015));
        assertThat("Attribute 1 is not setup correctly.", attributeMap.get(ATTRIBUTE_KEY_1).toString().equals(ATTRIBUTE_1));
        assertThat("Attribute 2 is not setup correctly.", attributeMap.get(ATTRIBUTE_KEY_2).toString().equals(ATTRIBUTE_2));
        assertThat("Attribute 3 is not setup correctly.", attributeMap.get(ATTRIBUTE_KEY_3).toString().equals(ATTRIBUTE_3));
    }
}
