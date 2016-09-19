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
    public static final String DYNAMICS_2015 = "dynamics2015";
    public static final String ATTRIBUTE_KEY_1 = "Key 1";
    public static final String ATTRIBUTE_KEY_2 = "Key 2";
    public static final String ATTRIBUTE_KEY_3 = "Key 3";
    public static final String ATTRIBUTE_1 = "Attribute 1";
    public static final String ATTRIBUTE_2 = "Attribute 2";
    public static final String ATTRIBUTE_3 = "Attribute 3";

    public static void main( String... args ) {
        new DocumentPackageAttributesExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
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
                .withOrigin(DYNAMICS_2015)
                .withAttributes(newDocumentPackageAttributes()
                                        .withAttribute(ATTRIBUTE_KEY_1, ATTRIBUTE_1)
                                        .withAttribute(ATTRIBUTE_KEY_2, ATTRIBUTE_2)
                                        .withAttribute(ATTRIBUTE_KEY_3, ATTRIBUTE_3))
            .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( packageId.toString(), "Client1" );
    }
}
