package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
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

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat("First custom field not set correctly.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.customFieldId1), is(true));
        assertThat("First custom value not set correctly.", example.getEslClient().getCustomFieldService().doesCustomFieldValueExist(example.customFieldId1), is(true));
        assertThat("Second custom field should have been deleted.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.customFieldId2), is(false));

        Collection<Signature> signatures = documentPackage.getDocument("First Document").getSignatures();
        Iterator<Signature> signatureIterator = signatures.iterator();
        assertThat("Signatures not set correctly.", signatures.iterator().hasNext(), is(true));

        Signature signature = signatureIterator.next();
        assertThat("Signature email not set correctly.", signature.getSignerEmail(), is(example.email1));

        Collection<Field> fields = signature.getFields();
        assertThat("Fields not set correctly.", fields.iterator().hasNext(), is(true));

        // Get first custom field
        assertThat("Custom field name was not set correctly.", example.retrieveCustomField.getId(), is(example.customFieldId1));
        assertThat("Custom field default value was not set correctly.", example.retrieveCustomField.getValue(), is(example.DEFAULT_VALUE));
        assertThat("Custom field English translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getName(), is(example.ENGLISH_NAME));
        assertThat("Custom field English translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getLanguage(), is(example.ENGLISH_LANGUAGE));
        assertThat("Custom field English translation description was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getDescription(), is(example.ENGLISH_DESCRIPTION));
        assertThat("Custom field French translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getName(), is(example.FRENCH_NAME));
        assertThat("Custom field French translation language was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getLanguage(), is(example.FRENCH_LANGUAGE));
        assertThat("Custom field French translation description was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getDescription(), is(example.FRENCH_DESCRIPTION));

        // Get entire list of custom fields
        assertThat("There should be at least two custom fields.", example.retrieveCustomFieldList1.size(), is(greaterThan(1)));

        // Get first page of custom field
        assertThat("There should be two custom fields in list.", example.retrieveCustomFieldList2.size(), is(greaterThan(1)));

        // Get the custom field values for this user
        assertThat("Could not get the custom field values for this user.", example.retrieveCustomFieldValueList1.size(), is(greaterThanOrEqualTo(1)));

        // Get the custom field values for this user after deleting 1 user custom field for this user
        assertThat("Could not delete the custom field value for this user.", example.retrieveCustomFieldValueList2.size(), is(example.retrieveCustomFieldValueList1.size() - 1));
    }
}
