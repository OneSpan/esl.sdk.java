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
 * Created by mpoitras on 22/04/14.
 */
public class SenderAuthenticationTokenExample extends SDKSample {

    private AuthenticationClient authenticationClient;
    private String email1;
    private final InputStream documentInputStream;

    public SenderAuthenticationTokenExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "auth.url" ),
              props.getProperty( "1.email" ));
    }

    public SenderAuthenticationTokenExample(String apiKey, String apiUrl, String authUrl, String email1) {
        super( apiKey, apiUrl);
        authenticationClient = new AuthenticationClient(authUrl);
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

        final String senderAuthenticationToken = eslClient.createSenderAuthenticationToken(packageId.getId());

        //This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser)
        //This session is limited to operations on the package for which it was created (prepare package, modify package, download documents)
        final String sessionIdForSender = authenticationClient.getSessionIdForSenderAuthenticationToken(senderAuthenticationToken);
    }

}
