package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signatureDate;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerCompany;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signerTitle;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
/*
 * How to add bound fields on a document and their behaviour once the signer has completed the ceremony
 */
public class SignerBoundFieldsExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed("Policy " + format.format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(props.getProperty("1.email"))
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(signatureDate()
                                        .onPage(0)
                                        .atPosition(400, 200))
                                .withField(signerName()
                                        .onPage(0)
                                        .atPosition(400, 300))
                                .withField(signerTitle()
                                        .onPage(0)
                                        .atPosition(400, 400))
                                .withField(signerCompany()
                                        .onPage(0)
                                        .atPosition(400, 500))))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}