package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example of document workflow (signing order)
 */
public class DocumentWorkflowExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );


    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed("Policy " + format.format(new Date()))
                .describedAs("This is a package with a document workflow created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(props.getProperty("1.email"))
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("Second Document")
                        .fromFile("src/main/resources/document.pdf")
                        .atIndex(2)
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(100, 100)))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .atIndex(1)
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}