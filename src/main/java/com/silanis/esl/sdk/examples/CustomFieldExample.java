package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
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

    public final String defaultValue = "#12345";
    public final String englishLanguage = "en";
    public final String englishName = "The Bay";
    public final String englishDescription = "The Bay store";
    public final String frenchLanguage = "fr";
    public final String frenchName = "La Baie";
    public final String frenchDescription = "Le magasin La Baie";
    public final String fieldValue = "le woah";
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
                        .withDefaultValue( defaultValue )
                        .withTranslation( newTranslation( englishLanguage ).
                                withName( englishName ).
                                withDescription( englishDescription ) )
                        .withTranslation( newTranslation( frenchLanguage ).
                                withName( frenchName ).
                                withDescription( frenchDescription ) )
                        .build()
                );

        CustomFieldValue customFieldValue = eslClient.getCustomFieldService()
                .submitCustomFieldValue( CustomFieldValueBuilder.customFieldValueWithId( customField.getId() )
                        .withValue( fieldValue )
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
                                .withField( FieldBuilder.customField()
                                        .onPage( 0 )
                                        .atPosition( 400, 200 )
                                        .withName( customFieldValue.getId() ) ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }

}
