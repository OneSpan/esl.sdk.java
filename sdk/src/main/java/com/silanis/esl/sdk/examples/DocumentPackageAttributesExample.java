package com.silanis.esl.sdk.examples;

/**
 * User: jessica
 * Date: 29/10/13
 * Time: 4:14 PM
 */

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder.newDocumentPackageAttributes;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Package with custom attributes
 */
public class DocumentPackageAttributesExample extends SDKSample{
    private String email1;
    private InputStream documentInputStream1;
    public static final String ATTRIBUTE_KEY_1 = "Key 1";
    public static final String ATTRIBUTE_KEY_2 = "Key 2";
    public static final String ATTRIBUTE_KEY_3 = "Key 3";
    public static final String ATTRIBUTE_1 = "Attribute 1";
    public static final String ATTRIBUTE_2 = "Attribute 2";
    public static final String ATTRIBUTE_3 = "Attribute 3";

    public static void main( String... args ) {
        new DocumentPackageAttributesExample(Props.get()).run();
    }

    public DocumentPackageAttributesExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ));
    }

    public DocumentPackageAttributesExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "DocumentPackageAttributesExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .withField(FieldBuilder.checkBox()
                                        .onPage(0)
                                        .atPosition(400, 200)
                                        .withValue(FieldBuilder.RADIO_SELECTED))
                                .atPosition(100, 100)))
                .withAttributes(newDocumentPackageAttributes()
                        .withAttribute(ATTRIBUTE_KEY_1, ATTRIBUTE_1)
                        .withAttribute(ATTRIBUTE_KEY_2, ATTRIBUTE_2)
                        .withAttribute(ATTRIBUTE_KEY_3, ATTRIBUTE_3)
                        .build())
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( packageId.toString(), "Client1" );
    }
}
