package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DocumentLayoutExample {
    public static final String API_KEY = "YjA0ODY5MDItZjM4NC00MTA2LTk0OTgtYWVhNmZkZGQ4YjJlOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";
    public static final String LAYOUT_NAME = "Sample Document Layout";
    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String ...args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .inPerson(true)
                .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withSigner( newSignerWithEmail( "patty.galant@acme.com" )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor("etienne_hardy@silanis.com")
                                .onPage(0)
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName("Second Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor("patty.galant@acme.com")
                                .onPage(1)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        DocumentPackage aPackage = eslClient.getPackage( packageId );

        eslClient.applyDocumentLayout( packageId, aPackage.getDocument( "Second Document" ).getId(), LAYOUT_NAME );
        aPackage = eslClient.getPackage( packageId );
        System.out.println( aPackage.getName() );
    }
}
