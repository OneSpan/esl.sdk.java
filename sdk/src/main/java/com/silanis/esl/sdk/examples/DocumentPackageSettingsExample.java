package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.CeremonyLayoutSettingsBuilder.newCeremonyLayoutSettings;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder.newDocumentPackageSettings;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class DocumentPackageSettingsExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream;
    public static final String optOutReason1 = "Reason One";
    public static final String optOutReason2 = "Reason Two";
    public static final String optOutReason3 = "Reason Three";
    public static final String handOverLinkHref = "http://www.google.ca";
    public static final String handOverLinkText = "click here";
    public static final String handOverLinkTooltip = "link tooltip";

    public static void main( String... args ) {
        new DocumentPackageSettingsExample( Props.get() ).run();
    }

    public DocumentPackageSettingsExample( Properties properties ) {
        this(properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public DocumentPackageSettingsExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "DocumentPackageSettingsExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSettings( newDocumentPackageSettings()
                        .withInPerson()
                        .withoutDecline()
                        .withOptOut()
                        .withOptOutReason( optOutReason1 )
                        .withOptOutReason( optOutReason2 )
                        .withOptOutReason( optOutReason3 )
                        .withHandOverLinkHref( handOverLinkHref )
                        .withHandOverLinkText( handOverLinkText )
                        .withHandOverLinkTooltip( handOverLinkTooltip )
                        .withDialogOnComplete()

                        .withCeremonyLayoutSettings( newCeremonyLayoutSettings()
                                .withoutGlobalDownloadButton()
                                .withoutGlobalConfirmButton()
                                .withoutGlobalSaveAsLayoutButton()
                        )
                )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}
