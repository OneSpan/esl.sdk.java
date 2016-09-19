package com.silanis.esl.sdk.examples;

/**
 * User: jessica
 * Date: 07/11/13
 * Time: 11:45 AM
 */

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.builder.FieldBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Example to show how to upload a document to a package.
 */
public class DocumentUploadExample extends SDKSample{

    public static final String UPLOADED_DOCUMENT_NAME = "First Document";
    public Document document;
    public Document uploadedDocument;

    public static void main( String... args ) {
        new DocumentUploadExample().run();
    }

    public void execute() {

        // 1. Create a package
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs( "This is a package created using the eSignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withCustomId( "Client1" )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                        .build();

        packageId = eslClient.createPackage( superDuperPackage );

        superDuperPackage.setId(packageId);

        // 2. Construct a document
        document = newDocumentWithName(UPLOADED_DOCUMENT_NAME)
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .withField(FieldBuilder.checkBox()
                                .onPage(0)
                                .atPosition(400, 200)
                                .withValue(FieldBuilder.CHECKBOX_CHECKED))
                        .atPosition(100, 100))
                .build();

        // 3. Attach the document to the created package by uploading the document.
        uploadedDocument = eslClient.uploadDocument(document.getFileName(), document.getContent(), document, superDuperPackage);

        eslClient.sendPackage(superDuperPackage.getId());

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( superDuperPackage.getId().toString(), "Client1" );
    }
}

