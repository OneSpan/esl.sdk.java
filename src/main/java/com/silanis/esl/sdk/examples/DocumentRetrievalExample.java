package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentRetrievalExample {
    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    private static EslClient eslClient;
    private static PackageId packageId;
    private static String documentId = "myDocumentId";


    public static void main( String... args ) throws IOException {
        eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .describedAs( "This is a package with a document workflow created using the e-SignLive SDK" )
                .withSigner( newSignerWithEmail( props.getProperty( "1.email" ) )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "Second Document" )
                        .fromFile( "src/main/resources/document.pdf" )
                        .withId( documentId )
                        .withSignature( signatureFor( props.getProperty( "1.email" ) )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );

        downloadDocumentById();
    }

    public static void downloadDocumentById() throws IOException {
        byte[] documentBinary = eslClient.downloadDocument( packageId, documentId );
        FileOutputStream output = new FileOutputStream( new File( "myDocument.pdf" ) );
        output.write( documentBinary );
    }

    public static void downloadDocumentsAsZip() throws IOException {
        byte[] documentsZipBinary = eslClient.downloadZippedDocuments( packageId );
        FileOutputStream output = new FileOutputStream( new File( "myDocumentArchive.zip" ) );
        output.write( documentsZipBinary );
    }
}
