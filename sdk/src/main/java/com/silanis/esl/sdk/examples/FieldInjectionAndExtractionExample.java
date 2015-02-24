package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class FieldInjectionAndExtractionExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";

    public static void main( String... args ) {
        new FieldInjectionAndExtractionExample( Props.get() ).run();
    }

    public FieldInjectionAndExtractionExample( Properties properties ) {
        this(properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public FieldInjectionAndExtractionExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "field_groups.pdf" );
    }

    @Override
    public void execute() {

        // Note that the field ID for injected field is not a significant for the field injection.
        // InjectedField list is not returned by the esl-backend.
        DocumentPackage superDuperPackage = newPackageNamed( "FieldInjectionAndExtractionExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()) )
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John")
                                    .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withInjectedField(FieldBuilder.textField().withName("Text1").withValue("First Injected Value"))
                                      .withInjectedField(FieldBuilder.textField().withName("Text2").withValue("Second Injected Value"))
                                      .withInjectedField(FieldBuilder.textField().withName("Text3").withValue("Third Injected Value"))
                                      .withInjectedField(FieldBuilder.textField().withName("Text4").withValue("Fourth Injected Value"))
                                      .withInjectedField(FieldBuilder.textField().withName("Text5").withValue("Fifth Injected Value"))
                                      .withInjectedField(FieldBuilder.textField().withName("Text6").withValue("À à Â â Æ æ Ç ç È è É é Ê ë"))
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
