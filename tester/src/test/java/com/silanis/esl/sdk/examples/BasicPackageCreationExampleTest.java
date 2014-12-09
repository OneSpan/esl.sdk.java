package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.DateMidnight.now;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 4:41 PM
 * 
 * Test BasicPackageCreationExample.
 */
public class BasicPackageCreationExampleTest {
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void verifyResult() {

        BasicPackageCreationExample basicPackageCreationExample = new BasicPackageCreationExample( Props.get() );
        basicPackageCreationExample.run();

        DocumentPackage documentPackage = basicPackageCreationExample.getEslClient().getPackage(basicPackageCreationExample.getPackageId());

        // Verify if the package is created correctly.

        assertThat( "Package description was not set correctly.", documentPackage.getDescription(), is( "This is a package created using the e-SignLive SDK" ) );
        assertThat( "Package expiry date was not set correctly.", documentPackage.getExpiryDate(), is( now().plusMonths( 1 ).toDate() ) );
        assertThat( "Package message was not set correctly.", documentPackage.getPackageMessage(), is( "This message should be delivered to all signers" ) );

        // Verify if the sdk version is set correctly
        assertThat("Package attributes are null", documentPackage.getAttributes(), is(notNullValue()));
        assertThat("Package attributes are empty", documentPackage.getAttributes().getContents(), is(notNullValue()));
        assertThat("SDK version was not set", documentPackage.getAttributes().toMap().containsKey("sdk"), is(true) );
        assertThat("SDK version was not set to the correct value", documentPackage.getAttributes().toMap().get("sdk").toString(), is(equalTo("Java v" + VersionUtil.getVersion()) ) );

        // Signer 1
        Signer signer = documentPackage.getSigner(basicPackageCreationExample.email1);

        assertThat( "Signer 1 ID was not set correctly.", signer.getId(), is( "Client1" ) );
        assertThat( "Signer 1 first name was not set correctly.", signer.getFirstName(), is( "John" ) );
        assertThat( "Signer 1 last name was not set correctly.",signer.getLastName(), is( "Smith" ) );
        assertThat( "Signer 1 title was not set correctly.",signer.getTitle(), is( "Managing Director" ) );
        assertThat( "Signer 1 company was not set correctly.",signer.getCompany(), is( "Acme Inc." ) );

        // Signer 2
        signer = documentPackage.getSigner(basicPackageCreationExample.email2);
        assertThat( "Signer 2 first name was not set correctly.", signer.getFirstName(), is( "Patty" ) );
        assertThat( "Signer 2 last name was not set correctly.",signer.getLastName(), is( "Galant" ) );

        // Document 1
        Document document = documentPackage.getDocument("First Document pdf");

        Iterator<Signature> signatures = document.getSignatures().iterator();
        Signature signature;
        Field field;

        if (signatures.hasNext()) {
            signature = signatures.next();

            assertThat( "Signature's signer Email was not set correctly for First Document.", signature.getSignerEmail(), is( basicPackageCreationExample.email1 ) );
            assertThat("Signature page was not set correctly for First Document.", signature.getPage(), is(0));

            Iterator<Field> fields = signature.getFields().iterator();
            if (fields.hasNext())
            {
                field = fields.next();
                assertThat( "Field style for signature was not set correctly in First Document.", field.getStyle(), is( FieldStyle.CHECKBOX ) );
                assertThat( "Field Page number was not set correctly in First Document.", field.getPage(), is( 0 ) );
                assertThat( "Field value of signature was not set correctly in First Document.", field.getValue(), is( FieldBuilder.RADIO_SELECTED ) );
            }
        }

        // Document 2
        document = documentPackage.getDocument("Second Document PDF");
        signatures = document.getSignatures().iterator();

        if (signatures.hasNext()) {
            signature = signatures.next();

            assertThat( "Signature's signer Email was not set correctly for Second Document.", signature.getSignerEmail(), is( "capitalletters@email.com" ) );
            assertThat( "Signature page was not set correctly for Second Document.", signature.getPage(), is( 0 ) );

            Iterator<Field> fields = signature.getFields().iterator();
            if (fields.hasNext())
            {
                field = fields.next();
                assertThat( "First radio button style for signature was not set correctly in Second Document.", field.getStyle(), is( FieldStyle.RADIO ) );
                assertThat( "First radio button Page number was not set correctly in Second Document.", field.getPage(), is( 0 ) );
                assertThat( "First radio button value of signature was not set correctly in Second Document.", field.getValue(), is( "" ) );
                assertThat( "First radio button group was not set correctly in Second Document.", field.getFieldValidator().getOptions().get(0), equalTo(basicPackageCreationExample.group1));

                field = fields.next();
                assertThat( "Second radio button style for signature was not set correctly in Second Document.", field.getStyle(), is( FieldStyle.RADIO ) );
                assertThat( "Second radio button Page number was not set correctly in Second Document.", field.getPage(), is( 0 ) );
                assertThat( "Second radio button value of signature was not set correctly in Second Document.", field.getValue(), is(FieldBuilder.RADIO_SELECTED) );
                assertThat( "Second radio button group was not set correctly in Second Document.", field.getFieldValidator().getOptions().get(0), equalTo(basicPackageCreationExample.group1));

                field = fields.next();
                assertThat( "Third radio button style for signature was not set correctly in Second Document.", field.getStyle(), is( FieldStyle.RADIO ) );
                assertThat( "Third radio button Page number was not set correctly in Second Document.", field.getPage(), is( 0 ) );
                assertThat( "Third radio button value of signature was not set correctly in Second Document.", field.getValue(), is( FieldBuilder.RADIO_SELECTED ) );
                assertThat( "Third radio button group was not set correctly in Second Document.", field.getFieldValidator().getOptions().get(0), equalTo(basicPackageCreationExample.group2));

                field = fields.next();
                assertThat( "Third radio button style for signature was not set correctly in Second Document.", field.getStyle(), is( FieldStyle.RADIO ) );
                assertThat( "Third radio button Page number was not set correctly in Second Document.", field.getPage(), is( 0 ) );
                assertThat( "Third radio button value of signature was not set correctly in Second Document.", field.getValue(), is( "" ) );
                assertThat( "Third radio button group was not set correctly in Second Document.", field.getFieldValidator().getOptions().get(0), equalTo(basicPackageCreationExample.group2));

            }
        }

    }

}
