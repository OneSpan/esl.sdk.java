package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by mpoitras on 22/04/14.
 */
public class SignerAuthenticationTokenExample extends SDKSample {

    private AuthenticationClient authenticationClient;
    private String email1;
    private final InputStream documentInputStream;

    public SignerAuthenticationTokenExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "auth.url" ),
              props.getProperty( "1.email" ));
    }

    public SignerAuthenticationTokenExample(String apiKey, String apiUrl, String authUrl, String email1) {
        super( apiKey, apiUrl);
        authenticationClient = new AuthenticationClient(authUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.email1 = email1;
    }

    @Override
    void execute() {
        String signerId = UUID.randomUUID().toString();
        DocumentPackage packageToCreate = newPackageNamed("Designer Package " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                                    .withCustomId("Client1")
                                    .withFirstName("John")
                                    .withLastName("Smith")
                                    .withTitle("Managing Director")
                                    .withCompany("Acme Inc.")
                                    .withCustomId(signerId)
                )
                .withDocument( newDocumentWithName( "First Document" )
                                       .fromStream(documentInputStream, DocumentType.PDF)
                                       .withSignature(signatureFor(email1)
                                                              .onPage(0)
                                                              .atPosition(100, 100)) )
                .build();
        packageId = eslClient.createPackage(packageToCreate);
        eslClient.sendPackage( packageId );

        final String signerAuthenticationToken = eslClient.createSignerAuthenticationToken(packageId.getId(), signerId);

        //This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser)
        //This session is limited to operations on the package for that particular signer
        final String sessionIdForSigner = authenticationClient.getSessionIdForSignerAuthenticationToken(signerAuthenticationToken);
    }

}
