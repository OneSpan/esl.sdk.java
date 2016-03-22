package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.util.logging.Logger;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static org.joda.time.DateMidnight.now;

/**
 * A typical example showing to create a link that can then be used to authenticate the api key user and redirect him to a designer view.
 * For a simpler example usage: {@link com.silanis.esl.sdk.examples.UserAuthenticationTokenExample}
 * Created by mpoitras on 23/04/14.
 */
public class DesignerRedirectForApiKeyExample extends SDKSample {

    private static final Logger logger = Logger.getLogger(DesignerRedirectForApiKeyExample.class.getName());

    public static void main( String... args ) {
        new DesignerRedirectForApiKeyExample().run();
    }

    private AuthenticationClient authenticationClient;
    public String generatedLinkToDesignerForApiKey;

    public DesignerRedirectForApiKeyExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream1, DocumentType.PDF))
                                      .build();
        packageId = eslClient.createPackage( superDuperPackage );

        final String userAuthenticationToken = eslClient.getAuthenticationTokensService().createUserAuthenticationToken();


        generatedLinkToDesignerForApiKey = authenticationClient.buildRedirectToDesignerForUserAuthenticationToken(userAuthenticationToken, packageId.getId());


        //This is an example url that can be used in an iFrame or to open a browser window with a session (created from the user authentication token) and a redirect to the designer page.
        logger.info(generatedLinkToDesignerForApiKey);
    }

}
