package com.silanis.esl.sdk.examples;

import com.silanis.awsng.web.rest.model.Package;
import com.silanis.awsng.web.rest.util.JacksonUtil;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentPackageTemplate {

    public static final String API_KEY = "YjA0ODY5MDItZjM4NC00MTA2LTk0OTgtYWVhNmZkZGQ4YjJlOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";
    public static final String TEMPLATE_NAME = "Template Name";
    public static final String TEMPLATE_ID = "CSsDEL9rhkfMhTGgKG3sdKbU0rID";

    public static void main( String... args ) {

        Package aPackage = new Package();
        aPackage.setName( "New Package Name" );

        String packageJson = JacksonUtil.serializeDirty( aPackage );
        System.out.println( ">>>" + packageJson + "<<<" );


        createDocumentPackageFromTemplate();
//        EslClient eslClient = new EslClient( API_KEY, API_URL );

//        DocumentPackage originalPackage = newPackageNamed( "Sample Insurance policy" )
//                .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
//                        .withFirstName( "John" )
//                        .withLastName( "Smith" ) )
//                .withDocument( newDocumentWithName( "First Document" )
//                        .fromFile( "src/main/resources/document.pdf" )
//                        .withSignature( signatureFor( "dlawson@silanis.com" )
//                                .withSize( 200, 50 )
//                                .atPosition( 100, 100 )
////                                .withName( "sig1" )
//                                .withInjectedField( newField()
//                                        .withSize( 500, 50 )
//                                        .atPosition( 100, 100 )
////                                        .withPositionExtracted()
////                                        .withName( "date1" )
//                                        .withStyle( FieldStyle.BOUND_DATE ) ) ) )
//                .build();

//        PackageId packageId = eslClient.createPackage( originalPackage );
//        eslClient.getPackageService().createTemplateFromPackage( packageId, TEMPLATE_NAME );

//        DocumentPackage templateInstance = newPackageNamed( "Package from Template" ).build();
//        PackageId templateInstanceId = eslClient.getPackageService().createPackageFromTemplate( TEMPLATE_NAME, templateInstance );
    }

    private static void createTemplateFromDocumentPackage() {
        PackageId packageId = new PackageId( TEMPLATE_ID );
        EslClient eslClient = new EslClient( API_KEY, API_URL );

//        eslClient.getPackageService().createTemplateFromPackage( packageId, TEMPLATE_NAME );
    }

    private static void createDocumentPackageFromTemplate() {
        PackageId packageId = new PackageId( TEMPLATE_ID );
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage documentPackage = newPackageNamed( "Package From Template Name" )
                .build();


        packageId = eslClient.createPackageFromTemplate( documentPackage, packageId );


        Package aPackage = eslClient.getPackageService().getPackage( packageId );
        System.out.println( "WTF" );
    }
}
