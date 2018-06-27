package com.silanis.esl.sdk.examples;

import com.google.common.base.Optional;
import com.silanis.esl.sdk.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.silanis.esl.sdk.FieldStyle.UNBOUND_CHECK_BOX;
import static com.silanis.esl.sdk.FieldStyle.UNBOUND_RADIO_BUTTON;
import static com.silanis.esl.sdk.VersionUtil.getVersion;
import static com.silanis.esl.sdk.builder.FieldBuilder.RADIO_SELECTED;
import static com.silanis.esl.sdk.examples.BasicPackageCreationExample.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 4:41 PM
 *
 * Test BasicPackageCreationExample.
 */
public class BasicPackageCreationExampleTest {

    private static final String SDK_KEY = "sdk";
    private static final String SDK_VALUE = "Java v" + getVersion();
    private static final String ORIGIN_KEY = "origin";
    private static final String ORIGIN_VALUE = "api";
    private static final String THIRD_PARTY_SIGNER = "THIRD_PARTY_SIGNER";

    @Test
    public void verifyResult() {

        BasicPackageCreationExample example = new BasicPackageCreationExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        // Verify if the package is created correctly.
        assertFalse("Package enableInPerson setting was not set correctly.", documentPackage.getSettings().getEnableInPerson());

        assertThat("Package description was not set correctly.", documentPackage.getDescription(), is(PACKAGE_DESC));
        assertThat( "Package expiry date was not set correctly.", documentPackage.getExpiryDate(), is(PACKAGE_EXPIRY) );
        assertThat( "Package message was not set correctly.", documentPackage.getPackageMessage(), is(PACKAGE_EMAIL_MSG) );

        // Verify if the sdk version is set correctly
        assertThat("Package attributes are null", documentPackage.getAttributes(), notNullValue());
        assertThat("Package attributes are empty", documentPackage.getAttributes().getContents(), notNullValue());
        assertTrue("SDK version was not set", documentPackage.getAttributes().toMap().containsKey(SDK_KEY));
        assertThat("SDK version was not set to the correct value", documentPackage.getAttributes().toMap().get(SDK_KEY).toString(), is(SDK_VALUE));

        // Verify if the origin is set correctly
        assertTrue("Origin was not set", documentPackage.getAttributes().toMap().containsKey(ORIGIN_KEY));
        assertThat("Origin was not set to the correct value", documentPackage.getAttributes().toMap().get(ORIGIN_KEY).toString(), is(ORIGIN_VALUE));

        // Signer 1
        Signer signer = documentPackage.getSigner(example.email1);

        assertThat( "Signer 1 ID was not set correctly.", signer.getId(), is(SIGNER1_CUSTOM_ID) );
        assertThat( "Signer 1 first name was not set correctly.", signer.getFirstName(), is(SIGNER1_FIRST_NAME) );
        assertThat( "Signer 1 last name was not set correctly.", signer.getLastName(), is(SIGNER1_LAST_NAME) );
        assertThat( "Signer 1 title was not set correctly.", signer.getTitle(), is(SIGNER1_TITLE) );
        assertThat( "Signer 1 company was not set correctly.", signer.getCompany(), is(SIGNER1_COMPANY) );
        assertThat( "Signer 1 language was not set correctly.", signer.getLanguage(), is(SIGNER1_LANGUAGE) );
        assertThat( "Signer 1 signer type was not set correctly.", signer.getSignerType(), is(THIRD_PARTY_SIGNER) );

        // Signer 2
        signer = documentPackage.getSigner(example.email2);
        assertThat( "Signer 2 first name was not set correctly.", signer.getFirstName(), is(SIGNER2_FIRST_NAME) );
        assertThat( "Signer 2 last name was not set correctly.", signer.getLastName(), is(SIGNER2_LAST_NAME) );
        assertThat( "Signer 2 signer type was not set correctly.", signer.getSignerType(), is(THIRD_PARTY_SIGNER) );

        // Document 1
        Document document = documentPackage.getDocument(DOCUMENT1_NAME);
        assertThat("The number of pages in the document is not set correctly.", document.getNumberOfPages(), is(7));

        Iterator<Signature> signatures = document.getSignatures().iterator();
        assertTrue("Signature doesn't exist in First Document.", signatures.hasNext());

        Signature signature = signatures.next();
        assertThat( "Signature's signer Email was not set correctly for First Document.", signature.getSignerEmail(), is( example.email1 ) );
        assertThat("Signature page was not set correctly for First Document.", signature.getPage(), is(0));

        Iterator<Field> fields = signature.getFields().iterator();
        assertTrue("Field doesn't exist in First Document.", fields.hasNext());

        Field field = fields.next();
        assertThat( "Field style for signature was not set correctly in First Document.", field.getStyle(), is(UNBOUND_CHECK_BOX) );
        assertThat( "Field Page number was not set correctly in First Document.", field.getPage(), is( 0 ) );
        assertThat( "Field value of signature was not set correctly in First Document.", field.getValue(), is(RADIO_SELECTED) );

        // Document 2
        document = documentPackage.getDocument(DOCUMENT2_NAME);
        assertThat("The number of pages in the document is not set correctly.", document.getNumberOfPages(), is(1));
        signatures = document.getSignatures().iterator();
        assertTrue("Signature doesn't exist in Second Document.", signatures.hasNext());

        signature = signatures.next();
        assertThat( "Signature's signer Email was not set correctly for Second Document.", signature.getSignerEmail(), is( example.email2.toLowerCase() ) );
        assertThat( "Signature page was not set correctly for Second Document.", signature.getPage(), is( 0 ) );


        final Optional<Field> firstFieldOptional = findFieldByName("firstField", signature.getFields());
        assertTrue("Fist radio button doesn't exist in Second Document.", firstFieldOptional.isPresent());

        Field firstField = firstFieldOptional.get();
        assertThat( "First radio button style for signature was not set correctly in Second Document.", firstField.getStyle(), is(UNBOUND_RADIO_BUTTON) );
        assertThat( "First radio button Page number was not set correctly in Second Document.", firstField.getPage(), is(0) );
        assertThat( "First radio button value of signature was not set correctly in Second Document.", firstField.getValue(), is( "" ) );
        assertThat("First radio button group was not set correctly in Second Document.", firstField.getFieldValidator().getOptions().get(0), equalTo(example.group1));

        final Optional<Field> secondFieldOptional = findFieldByName("secondField", signature.getFields());
        assertTrue("Second radio button doesn't exist in Second Document.", secondFieldOptional.isPresent());

        Field secondField = secondFieldOptional.get();
        assertThat("Second radio button style for signature was not set correctly in Second Document.", secondField.getStyle(), is(UNBOUND_RADIO_BUTTON));
        assertThat( "Second radio button Page number was not set correctly in Second Document.", secondField.getPage(), is( 0 ) );
        assertThat( "Second radio button value of signature was not set correctly in Second Document.", secondField.getValue(), is(RADIO_SELECTED) );
        assertThat("Second radio button group was not set correctly in Second Document.", secondField.getFieldValidator().getOptions().get(0), equalTo(example.group1));

        final Optional<Field> thirdFieldOptional = findFieldByName("thirdField", signature.getFields());
        assertTrue("Third radio button doesn't exist in Second Document.", thirdFieldOptional.isPresent());

        Field thirdField = thirdFieldOptional.get();
        assertThat("Third radio button style for signature was not set correctly in Second Document.", thirdField.getStyle(), is(UNBOUND_RADIO_BUTTON));
        assertThat( "Third radio button Page number was not set correctly in Second Document.", thirdField.getPage(), is( 0 ) );
        assertThat("Third radio button value of signature was not set correctly in Second Document.", thirdField.getValue(), is(RADIO_SELECTED));
        assertThat( "Third radio button group was not set correctly in Second Document.", thirdField.getFieldValidator().getOptions().get(0), equalTo(example.group2));

        final Optional<Field> fourthFieldOptional = findFieldByName("fourthField", signature.getFields());
        assertTrue("Fourth radio button doesn't exist in Second Document.", fourthFieldOptional.isPresent());

        Field fourthField = fourthFieldOptional.get();
        assertThat( "Fourth radio button style for signature was not set correctly in Second Document.", fourthField.getStyle(), is(UNBOUND_RADIO_BUTTON) );
        assertThat( "Fourth radio button Page number was not set correctly in Second Document.", fourthField.getPage(), is( 0 ) );
        assertThat( "Fourth radio button value of signature was not set correctly in Second Document.", fourthField.getValue(), is( "" ) );
        assertThat( "Fourth radio button group was not set correctly in Second Document.", fourthField.getFieldValidator().getOptions().get(0), equalTo(example.group2));
    }

    private Optional<Field> findFieldByName(String fieldName, Collection<Field> fields) {
        for (Field field : fields) {
            if(StringUtils.equals(fieldName, field.getName())) {
                return Optional.of(field);
            }
        }
        return Optional.absent();
    }
}
