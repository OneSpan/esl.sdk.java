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

    private AuthenticationClient authenticationClient;
    private String packageSender;
    private InputStream documentInputStream;
    private String generatedLinkToPackageViewForSender;

    public PackageViewRedirectForPackageSenderExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ),
              props.getProperty( "1.email" ));
    }

    public PackageViewRedirectForPackageSenderExample( String apiKey, String apiUrl, String webpageUrl, String packageSender ) {
        super( apiKey, apiUrl );
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
        this.packageSender = packageSender;
    }

    @Override
    void execute() {

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

        final String senderAuthenticationToken = eslClient.getAuthenticationTokensService().createSenderAuthenticationToken(packageId.getId());


        generatedLinkToPackageViewForSender = authenticationClient.buildRedirectToPackageViewForSender(senderAuthenticationToken, packageId.getId());


        logger.info(generatedLinkToPackageViewForSender);
    }

    private void createPackageWithCustomSender() {
        DocumentPackage superDuperPackage = newPackageNamed( "PackageViewRedirectForPackageSenderExample " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
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

    public String getGeneratedLinkToPackageViewForSender() {
        return generatedLinkToPackageViewForSender;
    }



}
