package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;

import java.util.logging.Logger;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

/**
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(PackageViewRedirectForPackageSenderExample.class.getName());

    public static void main( String... args ) {
        new PackageViewRedirectForPackageSenderExample().run();
    }
    public String generatedLinkToPackageViewForSender;

    private AuthenticationClient authenticationClient;

    public PackageViewRedirectForPackageSenderExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    public void execute() {

        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(senderEmail)
                                    .withFirstName("firstName")
                                    .withLastName("lastName")
                                    .withCompany("company")
                                    .withTitle("title")
                                    .withPhoneNumber("phoneNumber")
                                    .build() );

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(senderEmail)
                                                 .withName("firstName", "lastName")
                                                 .withTitle("title")
                                                 .withCompany("company"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF)
                                      .withSignature(signatureFor(senderEmail)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        final String userAuthenticationToken = eslClient.getAuthenticationTokensService().createUserAuthenticationToken();

        generatedLinkToPackageViewForSender = authenticationClient.buildRedirectToPackageViewForSender(userAuthenticationToken, packageId.getId());

        logger.info(generatedLinkToPackageViewForSender);
    }
}
