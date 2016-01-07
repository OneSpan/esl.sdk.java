package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.silanis.esl.sdk.examples.CustomFieldExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertTrue("First custom field not set correctly.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.customFieldId1));
        assertFalse("Second custom field should have been deleted.", example.getEslClient().getCustomFieldService().doesCustomFieldExist(example.customFieldId2));

        Collection<Signature> signatures = documentPackage.getDocument("First Document").getSignatures();
        Iterator<Signature> signatureIterator = signatures.iterator();
        assertTrue("Signatures not set correctly.", signatures.iterator().hasNext());

        Signature signature = signatureIterator.next();
        assertThat("Signature email not set correctly.", signature.getSignerEmail(), is(example.email1));

        Collection<Field> fields = signature.getFields();
        assertTrue("Fields not set correctly.", fields.iterator().hasNext());

        // Get first custom field
        assertThat("Custom field name was not set correctly.", example.retrieveCustomField.getId(), is(example.customFieldId1));
        assertThat("Custom field default value was not set correctly.", example.retrieveCustomField.getValue(), is(DEFAULT_VALUE));
        assertThat("Custom field English translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getName(), is(ENGLISH_NAME));
        assertThat("Custom field English translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getLanguage(), is(ENGLISH_LANGUAGE));
        assertThat("Custom field English translation description was not set correctly.", example.retrieveCustomField.getTranslations().get(0).getDescription(), is(ENGLISH_DESCRIPTION));
        assertThat("Custom field French translation name was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getName(), is(FRENCH_NAME));
        assertThat("Custom field French translation language was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getLanguage(), is(FRENCH_LANGUAGE));
        assertThat("Custom field French translation description was not set correctly.", example.retrieveCustomField.getTranslations().get(1).getDescription(), is(FRENCH_DESCRIPTION));

        // Get entire list of custom fields
        assertThat("There should be at least two custom fields.", example.retrieveCustomFieldList1.size(), greaterThan(1));

        // Get first page of custom field
        assertThat("There should be two custom fields in list.", example.retrieveCustomFieldList2.size(), greaterThan(1));

        // Get the custom field values for this user
        assertThat("Could not get the custom field values for this user.", example.retrieveCustomFieldValueList1.size(), greaterThanOrEqualTo(1));
        assertThat("Could not get the custom field value1 for this user.", example.retrievedCustomFieldValue1.getId(), is(example.customFieldId1));
        assertThat("Could not get the custom field value2 for this user.", example.retrievedCustomFieldValue2.getId(), is(example.customFieldId2));

        // Get the custom field values for this user after deleting 1 user custom field for this user
        assertThat("Could not delete the custom field value for this user.", example.retrieveCustomFieldValueList2.size(), is(example.retrieveCustomFieldValueList1.size() - 1));
    }
}
