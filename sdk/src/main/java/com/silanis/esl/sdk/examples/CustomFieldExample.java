package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CustomField;
import com.silanis.esl.sdk.CustomFieldValue;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.CustomFieldValueBuilder;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.CustomFieldBuilder.customFieldWithId;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.TranslationBuilder.newTranslation;

public class CustomFieldExample extends SDKSample {

    public final String email1;
    private InputStream documentInputStream1;

    public static final String DEFAULT_VALUE = "#12345";
    public static final String ENGLISH_LANGUAGE = "en";
    public static final String ENGLISH_NAME = "Player Number";
    public static final String ENGLISH_DESCRIPTION = "The number on your team jersey";
    public static final String FRENCH_LANGUAGE = "fr";
    public static final String FRENCH_NAME = "Numéro du Joueur";
    public static final String FRENCH_DESCRIPTION = "Le numéro dans le dos de votre chandail d'équipe";
    public static final String FIELD_VALUE = "99";
    private String customFieldId;

    public static void main( String... args ) {
        new CustomFieldExample( Props.get() ).run();
    }

    public CustomFieldExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public CustomFieldExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document-with-fields.pdf" );

    }

    public String getCustomFieldId() {
        return customFieldId;
    }

    @Override
    public void execute() {

        customFieldId = UUID.randomUUID().toString().replaceAll( "-", "" );
        CustomField customField = eslClient.getCustomFieldService()
                .createCustomField( customFieldWithId( customFieldId )
                        .withDefaultValue(DEFAULT_VALUE)
                        .withTranslation( newTranslation(ENGLISH_LANGUAGE)
                                .withName(ENGLISH_NAME)
                                .withDescription(ENGLISH_DESCRIPTION) )
                        .withTranslation( newTranslation(FRENCH_LANGUAGE)
                                .withName(FRENCH_NAME)
                                .withDescription(FRENCH_DESCRIPTION) )
                        .build()
                );

        CustomFieldValue customFieldValue = eslClient.getCustomFieldService()
                .submitCustomFieldValue( CustomFieldValueBuilder.customFieldValueWithId( customField.getId() )
                        .withValue(FIELD_VALUE)
                        .build()
                );

        DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 )
                                .withField( FieldBuilder.customField( customFieldValue.getId() )
                                        .onPage( 0 )
                                        .atPosition( 400, 200 ) ) )
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }

}
