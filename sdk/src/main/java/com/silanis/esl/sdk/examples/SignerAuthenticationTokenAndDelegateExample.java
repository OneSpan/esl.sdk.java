package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import java.util.LinkedHashMap;
import java.util.Map;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

/**
 * A simple example demonstrating how to create signing session tokens with delegation.
 * Shows both multi-use and single-use authentication tokens for a delegated signer.
 * For a more typical (and complex) example usage: {@link SigningRedirectForSignerExample}
 */
public class SignerAuthenticationTokenAndDelegateExample extends SDKSample {

    public static void main(String... args) {
        new SignerAuthenticationTokenAndDelegateExample().run();
    }

    private final AuthenticationClient authenticationClient;
    public String signerSessionIdForMultiUse, signerSessionIdForSingleUse;

    public SignerAuthenticationTokenAndDelegateExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    public void execute() {

        String delegateeEmail = props.getProperty("delegatee.email");
        String delegatorEmail = props.getProperty("delegator.email");

        DocumentPackage packageToCreate = newPackageNamed(getPackageName()).describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(delegatorEmail).withFirstName("FirstName")
                        .withLastName("LastName")
                        .withTitle("title")
                        .withCompany("Company"))
                .withDocument(newDocumentWithName("First Document").fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(delegatorEmail).onPage(0)
                                .atPosition(100, 100)))
                .build();
        packageId = eslClient.createPackage(packageToCreate);
        eslClient.sendPackage(packageId);

        /*Note about the signer authentication token:
         * This is a single use token, limited to a time period (30 minutes). Trying to reuse it or to use it will cause an unauthorized error.
         * Trying to access pages not accessible to a signer will cause an unauthorized error
         */
        Map<String, String> signerSessionFields = new LinkedHashMap<>();
        final String signerAuthenticationToken = eslClient.getAuthenticationTokensService()
                .createSignerAuthenticationToken(packageId.getId(), delegatorEmail, delegateeEmail, signerSessionFields);
        final String signerAuthenticationTokenForSingleUse = eslClient.getAuthenticationTokensService()
                .createSignerAuthenticationTokenForSingleUse(packageId.getId(), delegatorEmail, delegateeEmail, signerSessionFields);

        /* This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser).
         * It is a signing session valid in the same way as clicking in an email except it is limited to signing operations on the package for which
         * it was created (accept consent, sign, fill-out fields).
         */
        signerSessionIdForMultiUse = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);
        signerSessionIdForSingleUse = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationTokenForSingleUse);

    }
}
