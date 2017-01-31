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
 * A typical example showing to create a link that can then be used by a signer to sign its document.
 * For a simpler example usage: {@link com.silanis.esl.sdk.examples.SignerAuthenticationTokenExample}
 * Created by mpoitras on 23/04/14.
 */
public class SigningRedirectForSignerExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(SigningRedirectForSignerExample.class.getName());

    public static void main( String... args ) {
        new SigningRedirectForSignerExample().run();
    }

    private AuthenticationClient authenticationClient;

    public String generatedLinkToSigningForSigner;

    public SigningRedirectForSignerExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    public void execute() {
        String signerId = UUID.randomUUID().toString();
        DocumentPackage packageToCreate = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
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

        final String signerAuthenticationToken = eslClient.getAuthenticationTokensService().createSignerAuthenticationToken(packageId.getId(), signerId);

        generatedLinkToSigningForSigner = authenticationClient.buildRedirectToSigningForSigner(signerAuthenticationToken, packageId.getId());

        //This is an example url that can be used in an iFrame or to open a browser window with a signing session (created from the signer authentication token) and a redirect to the signing page.
        logger.info(generatedLinkToSigningForSigner);
    }
}
