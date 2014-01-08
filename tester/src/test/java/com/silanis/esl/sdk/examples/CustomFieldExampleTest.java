package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 13/12/13
 * Time: 4:51 PM
 *
 * Test CustomFieldExample.
 */
public class CustomFieldExampleTest {

    @Test
    public void verifyResult() {
        CustomFieldExample customFieldExample = new CustomFieldExample( Props.get() );
        customFieldExample.run();

        DocumentPackage documentPackage = customFieldExample.getEslClient().getPackage(customFieldExample.getPackageId());

        assertThat("Customer field not set correctly.", customFieldExample.getEslClient().getCustomFieldService().doesCustomFieldExist(customFieldExample.getCustomFieldId()), is(true));
        assertThat("Customer value not set correctly.", customFieldExample.getEslClient().getCustomFieldService().doesCustomFieldValueExist(customFieldExample.getCustomFieldId()), is(true));

        Collection<Signature> signatures = documentPackage.getDocument("First Document").getSignatures();
        Iterator<Signature> signatureIterator = signatures.iterator();
        assertThat("Signatures not set correctly.", signatures.iterator().hasNext(), is(true));

        Signature signature = signatureIterator.next();
        assertThat("Signature email not set correctly.", signature.getSignerEmail(), is(customFieldExample.email1));

        Collection<Field> fields = signature.getFields();
        assertThat("Fields not set correctly.", fields.iterator().hasNext(), is(true));

    }
}



