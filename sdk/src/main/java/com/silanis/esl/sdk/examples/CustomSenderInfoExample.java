package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SenderStatus;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class CustomSenderInfoExample extends SDKSample {

    public static final String SENDER_FIRST_NAME = "Rob";
    public static final String SENDER_SECOND_NAME = "Mason";
    public static final String SENDER_TITLE = "Chief Vizier";
    public static final String SENDER_COMPANY = "The Masons";

    public static void main( String... args ) {
        new CustomSenderInfoExample().run();
    }

    public void execute() {
        senderEmail = getRandomEmail();

        // Note on the custom sender information:
        //
        // The custom sender information is disregarded if the sender is one of the signers for the process.
        // The custom sender must already be a member of the account
        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(senderEmail)
                        .withFirstName("firstName")
                        .withLastName("lastName")
                        .withCompany("company")
                        .withTitle("title")
                        .withLanguage("fr")
                        .withPhoneNumber("phoneNumber")
                        .withStatus(SenderStatus.ACTIVE)
                        .build());

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSenderInfo( SenderInfoBuilder.newSenderInfo( senderEmail )
                        .withName( SENDER_FIRST_NAME, SENDER_SECOND_NAME )
                        .withTitle( SENDER_TITLE )
                        .withCompany( SENDER_COMPANY ) )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "Patty" )
                        .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( "Second Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( senderEmail )
                                .onPage( 0 )
                                .atPosition( 100, 100 ) )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 200 )
                        )
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
