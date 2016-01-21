package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
        new SignerAuthenticationTokenExample( Props.get() ).run();
    }

    private AuthenticationClient authenticationClient;
    private String email1;
    private final InputStream documentInputStream;
    private String sessionIdForSigner;
    private String signerSessionFieldKey = "SDK SignerAuthenticationTokenExample Signer";

    public SignerAuthenticationTokenExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "1.email" ));
    }

    public SignerAuthenticationTokenExample(String apiKey, String apiUrl, String webpageUrl, String email1) {
        super( apiKey, apiUrl);
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.email1 = email1;
    }

    @Override
    void execute() {
        String signerId = UUID.randomUUID().toString();
        DocumentPackage packageToCreate = newPackageNamed("SignerAuthenticationTokenExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
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
                                      .fromStream(documentInputStream, DocumentType.PDF)
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
        final String signerAuthenticationToken = eslClient.getAuthenticationTokensService().createSignerAuthenticationToken(packageId.getId(), signerId);

        /* This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser).
         * It is a signing session valid in the same way as clicking in an email except it is limited to signing operations on the package for which
         * it was created (accept consent, sign, fill-out fields).
         */
        Map<String, String> signerSessionFields = new LinkedHashMap<String, String>();
        signerSessionFields.put(signerSessionFieldKey, email1);
        sessionIdForSigner = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken, signerSessionFields);
    }

    public String getSessionIdForSigner() {
        return sessionIdForSigner;
    }
}
