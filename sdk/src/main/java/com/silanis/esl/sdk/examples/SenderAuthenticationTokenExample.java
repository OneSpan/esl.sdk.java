package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * A simple example that explains how to create a sender authentication token for the sender of a particular package
 * and then use that token to obtain a sender session.
 * For a more typical (and complex) example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForPackageSenderExample}
 * Created by mpoitras on 22/04/14.
 */
public class SenderAuthenticationTokenExample extends SDKSample {

    public static void main( String... args ) {
        new SenderAuthenticationTokenExample( Props.get() ).run();
    }

    private AuthenticationClient authenticationClient;
    private String email1;
    private final InputStream documentInputStream;
    public String sessionIdForSender;

    public SenderAuthenticationTokenExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "1.email" ));
    }

    public SenderAuthenticationTokenExample(String apiKey, String apiUrl, String webpageUrl, String email1) {
        super( apiKey, apiUrl);
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.email1 = email1;
    }

    @Override
    void execute() {
        DocumentPackage packageToCreate = newPackageNamed("Designer Package " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner( newSignerWithEmail( email1 )
                                     .withCustomId( "Client1" )
                                     .withFirstName( "John" )
                                     .withLastName("Smith")
                                     .withTitle("Managing Director")
                                     .withCompany("Acme Inc.") )
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream, DocumentType.PDF))
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
