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

        DocumentPackage documentPackage = documentPackageAttributesExample.getEslClient().getPackage(documentPackageAttributesExample.getPackageId());
        DocumentPackageAttributes documentPackageAttributes = documentPackage.getAttributes();
        Map<String, Object> attributeMap = documentPackageAttributes.getContents();
        assertThat("Attribute key 1 is not setup correctly.", attributeMap.containsKey(documentPackageAttributesExample.attributeKey1));
        assertThat("Attribute key 2 is not setup correctly.", attributeMap.containsKey(documentPackageAttributesExample.attributeKey2));
        assertThat("Attribute key 3 is not setup correctly.", attributeMap.containsKey(documentPackageAttributesExample.attributeKey3));

        assertThat("Attribute 1 is not setup correctly.", attributeMap.get(documentPackageAttributesExample.attributeKey1).toString().equals(documentPackageAttributesExample.attribute1));
        assertThat("Attribute 2 is not setup correctly.", attributeMap.get(documentPackageAttributesExample.attributeKey2).toString().equals(documentPackageAttributesExample.attribute2));
        assertThat("Attribute 3 is not setup correctly.", attributeMap.get(documentPackageAttributesExample.attributeKey3).toString().equals(documentPackageAttributesExample.attribute3));

    }
}
