package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder;
import com.silanis.esl.sdk.examples.Props;
import com.silanis.esl.sdk.service.PackageService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class LoggingTest {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    @Before
    public void configureLogging() throws IOException {
        LogManager.getLogManager().readConfiguration(getClass().getResourceAsStream("/logging.properties"));
    }

    @Test
    @Ignore
    public void checkingLogging() {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed("Policy")
                .describedAs("Checking logging...")
                .withSigner(newSignerWithEmail(props.getProperty("1.email"))
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(500, 100)))
                .withAttributes( DocumentPackageAttributesBuilder.newDocumentPackageAttributes()
                        .withAttribute("age", "29")
                        .withAttribute("name", "first name"))
                .build();


        PackageId id = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage(id);
    }

    @Test
    @Ignore
    public void checkingLoggingForUpdatePackage() {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed("Policy")
                .describedAs("Checking logging...")
                .withSigner(newSignerWithEmail(props.getProperty("1.email"))
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor(props.getProperty("1.email"))
                                .onPage(0)
                                .atPosition(500, 100)))
                .build();

        PackageId id = eslClient.createPackage( superDuperPackage );

        PackageService packageService = eslClient.getPackageService();
        Package aPackage = packageService.getPackage(id);
        aPackage.setDescription("checking change");
        packageService.updatePackage(id, aPackage);

    }


}