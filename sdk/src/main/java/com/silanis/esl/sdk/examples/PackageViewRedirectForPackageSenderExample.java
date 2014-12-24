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
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(PackageViewRedirectForPackageSenderExample.class.getName());

    public static void main( String... args ) {
        new PackageViewRedirectForPackageSenderExample( Props.get() ).run();
    }
    public String generatedLinkToPackageViewForSender;

    private AuthenticationClient authenticationClient;
    private String senderEmail;
    private InputStream documentInputStream;

    public PackageViewRedirectForPackageSenderExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "sender.email" ));
    }

    public PackageViewRedirectForPackageSenderExample( String apiKey, String apiUrl, String webpageUrl, String senderEmail ) {
        super( apiKey, apiUrl );
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.senderEmail = senderEmail;
    }

    @Override
    void execute() {

        eslClient.getAccountService().inviteUser(
                AccountMemberBuilder.newAccountMember(senderEmail)
                                    .withFirstName("firstName")
                                    .withLastName("lastName")
                                    .withCompany("company")
                                    .withTitle("title")
                                    .withPhoneNumber("phoneNumber")
                                    .build() );

        DocumentPackage superDuperPackage = newPackageNamed( "PackageViewRedirectForPackageSenderExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .withSenderInfo(SenderInfoBuilder.newSenderInfo(senderEmail)
                                                 .withName("firstName", "lastName")
                                                 .withTitle("title")
                                                 .withCompany("company"))
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream, DocumentType.PDF)
                                      .withSignature(signatureFor(senderEmail)
                                                             .onPage(0)
                                                             .atPosition(100, 100))
                )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        final String senderAuthenticationToken = eslClient.getAuthenticationTokensService().createSenderAuthenticationToken(packageId.getId());

        generatedLinkToPackageViewForSender = authenticationClient.buildRedirectToPackageViewForSender(senderAuthenticationToken, packageId.getId());

        logger.info(generatedLinkToPackageViewForSender);
    }
}
