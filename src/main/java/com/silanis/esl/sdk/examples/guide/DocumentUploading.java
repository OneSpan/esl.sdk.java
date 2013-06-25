package com.silanis.esl.sdk.examples.guide;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.examples.CreatePackageFromInputStream;

import java.io.InputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.newField;
import static com.silanis.esl.sdk.builder.FieldBuilder.signatureDate;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentUploading {
    private static class UploadingFromFile {
        public static void main( String... args ) {
            newDocumentWithName( "First Document" )
                    .fromFile( "src/main/resources/document.pdf" );
        }
    }

    private static class UploadingFromStream {
        public static void main( String... args ) {
            InputStream documentStream = CreatePackageFromInputStream.class.getResourceAsStream( "/document.pdf" );
            newDocumentWithName( "First Document" )
                    .fromStream( documentStream, DocumentType.PDF );
        }
    }

    private static class CreateStaticDocument {
        //TODO: needs a sample!
    }

    private static class AddStaticDocument {
        //TODO: needs a sample!
    }

    private static class PositionExtraction {
        public static final String API_KEY = "ZDQ2MzczNmUtMDMyNC00OTkxLTkzNjYtODc3YTNlOWFmYzNjOkJzYnAyeXNJQURnSA==";
        public static final String API_URL = "http://localhost:8080";

        public static void main( String... args ) {
            EslClient eslClient = new EslClient( API_KEY, API_URL );

            DocumentPackage superDuperPackage = newPackageNamed( "Sample Insurance policy" )
                    .withSigner( newSignerWithEmail( "dlawson@silanis.com" )
                            .withFirstName( "John" )
                            .withLastName( "Smith" ) )
                    .withDocument( newDocumentWithName( "First Document" )
                            .fromFile( "src/main/resources/a.pdf" )
                            .enableExtraction()
                            .withSignature( signatureFor( "dlawson@silanis.com" )
                                    .withName( "PAD_sig2_date" )
                                    .withField( newField()
                                            .withPositionExtracted()
                                            .withName( "PAD_sig1_date" )
                                            .withStyle( FieldStyle.BOUND_DATE ) ) ) )
                    .build();

            DocumentPackage superDuperPackage2 = newPackageNamed("Sample Insurance policy")
                    .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                            .withFirstName("John")
                            .withLastName("Smith"))
                    .withDocument(newDocumentWithName("First Document")
                            .fromFile("src/main/resources/document-with-fields.pdf")
                            .enableExtraction()
                            .withSignature(signatureFor("etienne_hardy@silanis.com")
                                    .withName("AGENT_SIG_1")
                                    .withPositionExtracted()
                                    .withField(signatureDate()
                                            .withPositionExtracted()
                                            .withName("AGENT_SIG_2"))))
                    .build();
            PackageId packageId = eslClient.createPackage( superDuperPackage );
            eslClient.sendPackage( packageId );
        }
    }

    private static class AutomaticDynamicBinding {
        //TODO: needs a sample!
    }

    private static class ValueInjection {
        //TODO: needs a sample!
    }
}
