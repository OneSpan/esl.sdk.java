package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.PackageId;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentPackageTemplate {
    private static class CreateDocumentPackageFromTemplate {
        public static final String API_KEY = "<YOUR API KEY>";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";
        public static final String TEMPLATE_NAME = "<YOUR TEMPLATE NAME>";
        public static final String TEMPLATE_ID = "<YOUR TEMPLATE ID>";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage documentPackage = newPackageNamed( "Template Instance" ).build();

            PackageId packageId = new PackageId( TEMPLATE_ID );
            packageId = eslClient.createPackageFromTemplate( documentPackage, packageId );
        }
    }

    private static class Sample {
        public static final String API_KEY = "<YOUR API KEY HERE>";
        public static final String API_URL = "https://stage-api.e-signlive.com:8080";
        public static final String TEMPLATE_NAME = "Template Name";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage originalPackage = newPackageNamed( "Sample Insurance policy" )
                    .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
                            .withFirstName( "John" )
                            .withLastName( "Smith" ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/document.pdf" )
                            .withSignature( signatureFor( "dlawson@silanis.com" )
                                    .withName( "sig1" )
                                    .withField( newField()
                                            .withExtraction()
                                            .withName( "date1" )
                                            .withStyle( FieldStyle.BOUND_DATE ) ) ) )
                    .build();

            PackageId packageId = eslClient.createPackage( originalPackage );
//            eslClient.getPackageService().createTemplateFromPackage( packageId, TEMPLATE_NAME );

            DocumentPackage templateInstance = newPackageNamed( "Package from Template" ).build();
            PackageId templateInstanceId = eslClient.createPackageFromTemplate( templateInstance, packageId );
        }
    }
}
