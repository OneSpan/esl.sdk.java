package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * A simple example that explains how to create a signer authentication token for a signer of a particular package
 * and then use that token to obtain a signing session.
 * For a more typical (and complex) example usage: {@link com.silanis.esl.sdk.examples.SigningRedirectForSignerExample}
 * Created by mpoitras on 22/04/14.
 */
public class SignerAuthenticationTokenExample extends SDKSample {

    public static void main( String... args ) {
        new SignerAuthenticationTokenExample().run();
    }

    private AuthenticationClient authenticationClient;
    public String sessionIdForSigner;
    private String signerSessionFieldKey = "SDK SignerAuthenticationTokenExample Signer";

    public SignerAuthenticationTokenExample() {
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

         /*Note about the signer authentication token:
          * This is a single use token, limited to a time period (30 minutes). Trying to reuse it or to use it will cause an unauthorized error.
          * Trying to access pages not accessible to a signer will cause an unauthorized error
          */
        Map<String, String> signerSessionFields = new LinkedHashMap<String, String>();
        signerSessionFields.put(signerSessionFieldKey, email1);
        final String signerAuthenticationToken = eslClient.getAuthenticationTokensService().createSignerAuthenticationToken(packageId.getId(), signerId, signerSessionFields);

        /* This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser).
         * It is a signing session valid in the same way as clicking in an email except it is limited to signing operations on the package for which
         * it was created (accept consent, sign, fill-out fields).
         */
        sessionIdForSigner = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);
    }
}
