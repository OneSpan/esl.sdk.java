package com.silanis.esl.sdk.examples;

/**
 * User: jessica
 * Date: 29/10/13
 * Time: 4:14 PM
 */

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
    private String attributeKey1 = "Key 1";
    private String attributeKey2 = "Key 2";
    private String attributeKey3 = "Key 3";
    private String attribute1 = "Attribute 1";
    private String attribute2 = "Attribute 2";
    private String attribute3 = "Attribute 3";

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
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
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
                                        .withValue("x"))
                                .atPosition(100, 100)))
                .withAttributes(newDocumentPackageAttributes()
                        .withAttribute(attributeKey1, attribute1)
                        .withAttribute(attributeKey2, attribute2)
                        .withAttribute(attributeKey3, attribute3)
                        .build())
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( packageId.toString(), "Client1" );
    }

    @Override
    void postExecute() {
        // Verify if the attributes are created correctly.
        DocumentPackage documentPackage = eslClient.getPackage(packageId);
        DocumentPackageAttributes documentPackageAttributes = documentPackage.getAttributes();

        Map<String, Object> attributeMap = documentPackageAttributes.getContents();

        assert (attributeMap.containsKey(attributeKey1));
        assert (attributeMap.containsKey(attributeKey2));
        assert (attributeMap.containsKey(attributeKey3));

        assert (attributeMap.get(attributeKey1).toString().equals(attribute1));
        assert (attributeMap.get(attributeKey2).toString().equals(attribute2));
        assert (attributeMap.get(attributeKey3).toString().equals(attribute3));
    }
}
