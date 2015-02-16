package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.signatureDate;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class FieldInjectionAndExtractionExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final String INJECTED_FIELD_1_VALUE = "Test injected field Value";

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
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );
    }

    @Override
    public void execute() {

        // Note that the field ID for injected field is not a significant for the field injection.
        //
        DocumentPackage superDuperPackage = newPackageNamed( "FieldInjectionAndExtractionExample " + new SimpleDateFormat("HH:mm:ss").format(new Date()) )
                .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
                .withSigner(newSignerWithEmail(email1)
                                    .withFirstName("John")
                                    .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .enableExtraction()
                                      .withSignature(signatureFor(email1)
                                                             .withPositionExtracted())
                                      .withInjectedField(textField()
                                                                 .withPositionExtracted()
                                                                 .withName("AGENT_SIG_1")
                                                                 .withValue(INJECTED_FIELD_1_VALUE))
                                      .withInjectedField(signatureDate()
                                                                 .withPositionExtracted()))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
