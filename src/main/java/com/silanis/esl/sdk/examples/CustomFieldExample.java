package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.CustomField;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.CustomFieldBuilder.accountCustomField;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.TranslationBuilder.createTranslation;
import static com.silanis.esl.sdk.builder.UserCustomFieldBuilder.userCustomField;

public class CustomFieldExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;

    public static void main( String... args ) {
        new CustomFieldExample( Props.get() ).run();
    }

    public CustomFieldExample(Properties properties) {
        this(properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public CustomFieldExample(String apiKey, String apiUrl, String email1) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );
    }

    @Override
    public void execute() {

        CustomField customField = eslClient.getCustomFieldService()
                .createCustomField(accountCustomField("store_id")
                        .withDefaultValue("#12345")
                        .withTranslation(createTranslation()
                                .addTranslation("The Bay", "en", "The Bay store")
                                .addTranslation("La Baie", "fr", "Le magasin La Baie"))
                        .build()
                );

        String customFieldId = eslClient.getCustomFieldService()
                .createUserCustomField(userCustomField(customField.getId())
                        .withValue(customField.getValue())
                        .build()
                );

        DocumentPackage superDuperPackage = newPackageNamed("Sample Insurance policy")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)
                                .withField(FieldBuilder.customField()
                                        .onPage(0)
                                        .atPosition(400, 200)
                                        .withName(customFieldId))))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
