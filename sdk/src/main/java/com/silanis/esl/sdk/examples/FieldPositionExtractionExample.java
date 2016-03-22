package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signatureDate;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Extracts the position of a field from the uploaded document
 */
public class FieldPositionExtractionExample extends SDKSample {

    public static void main( String... args ) {
        new FieldPositionExtractionExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .enableExtraction()
                        .withSignature( signatureFor( email1 )
                                .withName( "AGENT_SIG_1" )
                                .withPositionExtracted()
                                .withField( signatureDate()
                                        .withPositionExtracted()
                                        .withName( "AGENT_SIG_2" ) ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
