package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageAttributes;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 5:07 PM
 * 
 * Test DocumentPackageAttributesExample.
 */
public class DocumentPackageAttributesExampleTest {
    @Test
    public void verifyResult() {
        DocumentPackageAttributesExample documentPackageAttributesExample = new DocumentPackageAttributesExample( Props.get() );
        documentPackageAttributesExample.run();

        DocumentPackage documentPackage = documentPackageAttributesExample.getRetrievedPackage();
        DocumentPackageAttributes documentPackageAttributes = documentPackage.getAttributes();
        Map<String, Object> attributeMap = documentPackageAttributes.getContents();
        assertThat("Attribute key 1 is not setup correctly.", attributeMap.containsKey(DocumentPackageAttributesExample.ATTRIBUTE_KEY_1));
        assertThat("Attribute key 2 is not setup correctly.", attributeMap.containsKey(DocumentPackageAttributesExample.ATTRIBUTE_KEY_2));
        assertThat("Attribute key 3 is not setup correctly.", attributeMap.containsKey(DocumentPackageAttributesExample.ATTRIBUTE_KEY_3));

        assertThat("Attribute 1 is not setup correctly.", attributeMap.get(DocumentPackageAttributesExample.ATTRIBUTE_KEY_1).toString().equals(DocumentPackageAttributesExample.ATTRIBUTE_1));
        assertThat("Attribute 2 is not setup correctly.", attributeMap.get(DocumentPackageAttributesExample.ATTRIBUTE_KEY_2).toString().equals(DocumentPackageAttributesExample.ATTRIBUTE_2));
        assertThat("Attribute 3 is not setup correctly.", attributeMap.get(DocumentPackageAttributesExample.ATTRIBUTE_KEY_3).toString().equals(DocumentPackageAttributesExample.ATTRIBUTE_3));
    }
}
