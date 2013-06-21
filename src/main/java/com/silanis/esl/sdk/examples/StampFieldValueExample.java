package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.label;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class StampFieldValueExample {

    public static final String API_KEY = "Q2xubnp5Y2dIQ3lROnNlY3JldA==";
    public static final String API_URL = "http://localhost:8080";

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed("Sample Insurance policy")
                .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document-with-fields.pdf")
                        .enableExtraction()
                        .withSignature(signatureFor("etienne_hardy@silanis.com")
                                .withName("AGENT_SIG_1")
                                .enableExtraction())
                        .withInjectedField( label()
                                .withName( "AGENT_SIG_2" )
                                .withValue( "Value to be stamped" ) ))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}