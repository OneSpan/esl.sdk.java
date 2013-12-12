package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.DateMidnight.now;
import static org.junit.Assert.assertEquals;

/**
 * Basic package with in-person mode set at the document package level. Expires in a month.
 */
public class BasicPackageCreationExample extends SDKSample {

    private String email1;
    private String email2;
    private InputStream documentInputStream1;
    private InputStream documentInputStream2;
    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    public static void main( String... args ) {
        new BasicPackageCreationExample(Props.get()).run();
    }

    public BasicPackageCreationExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ) );
    }

    public BasicPackageCreationExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs( "This is a package created using the e-SignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withCustomId( "Client1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withSigner( newSignerWithEmail( email2 )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .withField( FieldBuilder.checkBox()
                                        .onPage( 0 )
                                        .atPosition( 400, 200 )
                                        .withValue( "x" ) )
                                .atPosition( 100, 100 ) ) )
                .withDocument( newDocumentWithName( "Second Document" )
                        .fromStream( documentInputStream2, DocumentType.PDF )
                        .withSignature( signatureFor( email2 )
                                .onPage( 0 )
                                .atPosition( 100, 200 )
                        )
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( packageId.toString(), "Client1" );
    }

    @Override
    void postExecute() {
        // Verify if the package is created correctly.

        DocumentPackage documentPackage = eslClient.getPackage(packageId);
        assertThat( "Package description was not set correctly.", documentPackage.getDescription(), is( "This is a package created using the e-SignLive SDK" ) );
        assertThat( "Package expiry date was not set correctly.", documentPackage.getExpiryDate(), is( now().plusMonths( 1 ).toDate() ) );
        assertThat( "Package message was not set correctly.", documentPackage.getPackageMessage(), is( "This message should be delivered to all signers" ) );

        // Signer 1
        Signer signer = documentPackage.getSigner(email1);

        assertThat( "Signer 1 ID was not set correctly.", signer.getId(), is( "Client1" ) );
        assertThat( "Signer 1 first name was not set correctly.", signer.getFirstName(), is( "John" ) );
        assertThat( "Signer 1 last name was not set correctly.",signer.getLastName(), is( "Smith" ) );
        assertThat( "Signer 1 title was not set correctly.",signer.getTitle(), is( "Managing Director" ) );
        assertThat( "Signer 1 company was not set correctly.",signer.getCompany(), is( "Acme Inc." ) );

        // Signer 2
        signer = documentPackage.getSigner(email2);
        assertThat( "Signer 2 first name was not set correctly.", signer.getFirstName(), is( "Patty" ) );
        assertThat( "Signer 2 last name was not set correctly.",signer.getLastName(), is( "Galant" ) );

        // Document 1
        Document document = documentPackage.getDocument("First Document");

        Iterator<Signature> signatures = document.getSignatures().iterator();
        Signature signature;
        Field field;

        if (signatures.hasNext()) {
            signature = signatures.next();

            assertThat( "Signature's signer Email was not set correctly for First Document.", signature.getSignerEmail(), is( email1 ) );
            assertThat("Signature page was not set correctly for First Document.", signature.getPage(), is(0));
            assertEquals( 100, signature.getX(), DEFAULT_DOUBLE_TOLERANCE );
            assertEquals( 100, signature.getY(), DEFAULT_DOUBLE_TOLERANCE );

            Iterator<Field> fields = signature.getFields().iterator();
            if (fields.hasNext())
            {
                field = fields.next();
                assertThat( "Field style for signature was not set correctly in First Document.", field.getStyle(), is( FieldStyle.UNBOUND_CHECK_BOX ) );
                assertThat( "Field Page number was not set correctly in First Document.", field.getPage(), is( 0 ) );
                assertEquals( 400, field.getX(), DEFAULT_DOUBLE_TOLERANCE );
                assertEquals( 200, field.getY(), DEFAULT_DOUBLE_TOLERANCE );

                assertThat( "Field value of signature was not set correctly in First Document.", field.getValue(), is( "x" ) );
            }
        }

        // Document 2
        document = documentPackage.getDocument("Second Document");
        signatures = document.getSignatures().iterator();

        if (signatures.hasNext()) {
            signature = signatures.next();

            assertThat( "Signature's signer Email was not set correctly for Second Document.", signature.getSignerEmail(), is( email2 ) );
            assertThat( "Signature page was not set correctly for Second Document.", signature.getPage(), is( 0 ) );
            assertEquals( 100, signature.getX(), DEFAULT_DOUBLE_TOLERANCE );
            assertEquals( 200, signature.getY(), DEFAULT_DOUBLE_TOLERANCE );
        }
    }
}
