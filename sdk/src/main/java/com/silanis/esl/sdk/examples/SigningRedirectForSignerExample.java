package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.internal.Support;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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
        new SigningRedirectForSignerExample( Props.get() ).run();
    }

    private AuthenticationClient authenticationClient;
    private String signerEmail;
    private InputStream documentInputStream;
    private final Support support = new Support();
    private String generatedLinkToSigningForSigner;

    public SigningRedirectForSignerExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "1.email" ) );
    }

    public SigningRedirectForSignerExample(String apiKey, String apiUrl, String webpageUrl, String signerEmail) {
        super( apiKey, apiUrl );
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.signerEmail = signerEmail;
    }

    @Override
    void execute() {
        String signerId = UUID.randomUUID().toString();
        DocumentPackage packageToCreate = newPackageNamed("Designer Package " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(signerEmail)
                                    .withCustomId("Client1")
                                    .withFirstName("John")
                                    .withLastName("Smith")
                                    .withTitle("Managing Director")
                                    .withCompany("Acme Inc.")
                                    .withCustomId(signerId)
                )
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream, DocumentType.PDF)
                                      .withSignature(signatureFor(signerEmail)
                                                             .onPage(0)
                                                             .atPosition(100, 100)))
                .build();
        packageId = eslClient.createPackage(packageToCreate);
        eslClient.sendPackage( packageId );

        final String signerAuthenticationToken = eslClient.createSignerAuthenticationToken(packageId.getId(), signerId);


        generatedLinkToSigningForSigner = authenticationClient.buildRedirectToSigningForSigner(signerAuthenticationToken, packageId.getId());


        //This is an example url that can be used in an iFrame or to open a browser window with a signing session (created from the signer authentication token) and a redirect to the signing page.
        logger.info(generatedLinkToSigningForSigner);
    }

    public String getGeneratedLinkToSigningForSigner() {
        return generatedLinkToSigningForSigner;
    }
}
