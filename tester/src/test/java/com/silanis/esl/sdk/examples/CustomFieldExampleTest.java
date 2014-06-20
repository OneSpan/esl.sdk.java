package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.SignerRestClient;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.CustomFieldBuilder.customFieldWithId;
import static com.silanis.esl.sdk.builder.TranslationBuilder.newTranslation;
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
        CustomFieldExample example = new CustomFieldExample( Props.get() );
        example.run();

        DocumentPackage documentPackage = example.getEslClient().getPackage(example.getPackageId());

        assertThat("First custom field not set correctly.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.getCustomFieldId1()), is(true));
        assertThat("First custom value not set correctly.", example.getEslClient().getCustomFieldService().doesCustomFieldValueExist(example.getCustomFieldId1()), is(true));
        assertThat("Second custom field should have been deleted.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.getCustomFieldId2()), is(false));

        Collection<Signature> signatures = documentPackage.getDocument("First Document").getSignatures();
        Iterator<Signature> signatureIterator = signatures.iterator();
        assertThat("Signatures not set correctly.", signatures.iterator().hasNext(), is(true));

        Signature signature = signatureIterator.next();
        assertThat("Signature email not set correctly.", signature.getSignerEmail(), is(example.email1));

        Collection<Field> fields = signature.getFields();
        assertThat("Fields not set correctly.", fields.iterator().hasNext(), is(true));
    }
}



