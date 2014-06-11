package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

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
    public static final String OPT_OUT_REASON_1 = "Reason One";
    public static final String OPT_OUT_REASON_2 = "Reason Two";
    public static final String OPT_OUT_REASON_3 = "Reason Three";
    public static final String HAND_OVER_LINK_HREF = "http://www.google.ca";
    public static final String HAND_OVER_LINK_TEXT = "click here";
    public static final String HAND_OVER_LINK_TOOLTIP = "link tooltip";

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
                        .withoutLanguageDropDown()
                        .hideOwnerInPersonDropDown()
                        .disableFirstAffidavit()
                        .disableSecondAffidavit()
                        .withoutDecline()
                        .withOptOut()
                        .withOptOutReason(OPT_OUT_REASON_1)
                        .withOptOutReason(OPT_OUT_REASON_2)
                        .withOptOutReason(OPT_OUT_REASON_3)
                        .withHandOverLinkHref(HAND_OVER_LINK_HREF)
                        .withHandOverLinkText(HAND_OVER_LINK_TEXT)
                        .withHandOverLinkTooltip(HAND_OVER_LINK_TOOLTIP)
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
