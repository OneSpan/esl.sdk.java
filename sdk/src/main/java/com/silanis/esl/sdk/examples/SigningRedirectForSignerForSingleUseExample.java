package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.util.UUID;
import java.util.logging.Logger;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 9/12/16.
 */
public class SigningRedirectForSignerForSingleUseExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(SigningRedirectForSignerForSingleUseExample.class.getName());

    public static void main( String... args ) {
        new SigningRedirectForSignerForSingleUseExample().run();
    }

    private AuthenticationClient authenticationClient;

    public String generatedLinkToSigningForSigner;

    public SigningRedirectForSignerForSingleUseExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    void execute() {
        String signerId = UUID.randomUUID().toString();
        DocumentPackage packageToCreate = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the e-SignLive SDK")
            .withSigner(newSignerWithEmail(email1)
                            .withCustomId("Client1")
                            .withFirstName("John")
                            .withLastName("Smith")
                            .withTitle("Managing Director")
                            .withCompany("Acme Inc.")
                            .withCustomId(signerId)
            )
            .withDocument(newDocumentWithName("First Document")
                              .fromStream(documentInputStream1, DocumentType.PDF)
                              .withSignature(signatureFor(email1)
                                                 .onPage(0)
                                                 .atPosition(100, 100)))
            .build();
        packageId = eslClient.createPackage(packageToCreate);
        eslClient.sendPackage( packageId );

        final String signerAuthenticationToken = eslClient.getAuthenticationTokensService().createSignerAuthenticationTokenForSingleUse(packageId.getId(), signerId, null);

        generatedLinkToSigningForSigner = authenticationClient.buildRedirectToSigningForSigner(signerAuthenticationToken, packageId.getId());

        logger.info(generatedLinkToSigningForSigner);

    }
}
