package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

/**
 * A simple example that explains how to create a sender authentication token for the sender of a particular package using OAuth2 authentication
 * and then use that token to obtain a sender session.
 * For a more typical (and complex) example usage: {@link DesignerRedirectForPackageSenderExample}
 */
public class SenderAuthenticationTokenOauth2Example extends OAuth2SDKSample {

    private final AuthenticationClient authenticationClient;
    public String sessionIdForSender;

    public static void main( String... args ) {
        new SenderAuthenticationTokenOauth2Example().run();
    }

    public SenderAuthenticationTokenOauth2Example() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    public void execute() {
        DocumentPackage packageToCreate = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withSigner( newSignerWithEmail( email1 )
                                     .withCustomId( "Client1" )
                                     .withFirstName( "John" )
                                     .withLastName("Smith")
                                     .withTitle("Managing Director")
                                     .withCompany("Acme Inc.") )
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF))
                .build();
        packageId = eslClient.createPackage( packageToCreate );

         /* Note about the sender authentication token:
          * This is a single use token, limited to a time period (30 minutes). Trying to reuse it or to use it will cause an unauthorized error.
          * Trying to access pages unrelated to the package for which it was created will cause an unauthorized error
          */
        final String senderAuthenticationToken = eslClient.getAuthenticationTokensService().createSenderAuthenticationToken(packageId.getId());

        /* This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser).
         * It is a session valid in the same way as a normal login except it is limited to operations on the package for which
         * it was created (prepare package, modify package, download documents)
         */
        sessionIdForSender = authenticationClient.getSessionIdForSenderAuthenticationToken(senderAuthenticationToken);
    }
}
