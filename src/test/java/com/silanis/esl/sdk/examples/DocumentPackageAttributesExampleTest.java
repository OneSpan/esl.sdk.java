package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentPackageAttributes;
import org.junit.Test;

import java.util.Map;

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

        assert (attributeMap.containsKey(documentPackageAttributesExample.attributeKey1));
        assert (attributeMap.containsKey(documentPackageAttributesExample.attributeKey2));
        assert (attributeMap.containsKey(documentPackageAttributesExample.attributeKey3));

        assert (attributeMap.get(documentPackageAttributesExample.attributeKey1).toString().equals(documentPackageAttributesExample.attribute1));
        assert (attributeMap.get(documentPackageAttributesExample.attributeKey2).toString().equals(documentPackageAttributesExample.attribute2));
        assert (attributeMap.get(documentPackageAttributesExample.attributeKey3).toString().equals(documentPackageAttributesExample.attribute3));

    }
}
