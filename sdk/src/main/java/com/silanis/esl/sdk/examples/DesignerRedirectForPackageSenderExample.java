package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import com.silanis.esl.sdk.builder.SenderInfoBuilder;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;

/**
 * A typical example showing to create a link that can then be used by a package sender to configure/edit a single package.
 * For a simpler example usage: {@link com.silanis.esl.sdk.examples.SenderAuthenticationTokenExample}
 * Created by mpoitras on 23/04/14.
 */
public class DesignerRedirectForPackageSenderExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(DesignerRedirectForPackageSenderExample.class.getName());

    private AuthenticationClient authenticationClient;
    private String packageSender;
    private InputStream documentInputStream;
    private String generatedLinkToDesignerForSender;

    public DesignerRedirectForPackageSenderExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "auth.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "1.email" ));
    }

    public DesignerRedirectForPackageSenderExample( String apiKey, String apiUrl, String authUrl, String webpageUrl, String packageSender ) {
        super( apiKey, apiUrl );
        authenticationClient = new AuthenticationClient(authUrl, webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.packageSender = packageSender;
    }

    @Override
    void execute() {
        /*Note on Custom Sender and the creation of sender authentication tokens:
        *
        * Only an account manager will be authorized to create a package with a custom sender (the custom sender which needs to be in the same account).
        * In the same way, a sender authentication token can only be created by an account manager.
        */

        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(packageSender)
                        .withFirstName("firstName")
                        .withLastName("lastName")
                        .withCompany("company")
                        .withTitle("title")
                        .withLanguage("language")
                        .withPhoneNumber("phoneNumber")
                        .build() );

        createPackageWithCustomSender();

        final String senderAuthenticationToken = eslClient.createSenderAuthenticationToken(packageId.getId());


        generatedLinkToDesignerForSender = authenticationClient.buildRedirectToDesignerForSender(senderAuthenticationToken, packageId.getId());


        //This is an example url that can be used in an iFrame or to open a browser window with a session (created from the sender authentication token) and a redirect to the designer page.
        logger.info(generatedLinkToDesignerForSender);
    }

    private void createPackageWithCustomSender() {
        DocumentPackage superDuperPackage = newPackageNamed( "DesignerRedirectForPackageSenderExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(packageSender)
                                        .withName("firstName", "lastName")
                                        .withTitle("title")
                                        .withCompany("company"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream, DocumentType.PDF)
                                      .withSignature(signatureFor(packageSender)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
    }

    public String getGeneratedLinkToDesignerForSender() {
        return generatedLinkToDesignerForSender;
    }
}
