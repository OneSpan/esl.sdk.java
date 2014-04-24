package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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
        new DesignerRedirectForApiKeyExample( Props.get() ).run();
    }

    private AuthenticationClient authenticationClient;
    private InputStream documentInputStream;
    private String generatedLinkToDesignerForApiKey;

    public DesignerRedirectForApiKeyExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "webpage.url" ));
    }

    public DesignerRedirectForApiKeyExample(String apiKey, String apiUrl, String webpageUrl) {
        super( apiKey, apiUrl );
        authenticationClient = new AuthenticationClient(webpageUrl);
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    void execute() {
        DocumentPackage superDuperPackage = newPackageNamed("Policy " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
                .describedAs("This is a package created using the e-SignLive SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withDocument(newDocumentWithName("First Document")
                                      .fromStream(documentInputStream, DocumentType.PDF))
                                      .build();
        packageId = eslClient.createPackage( superDuperPackage );

        final String userAuthenticationToken = eslClient.createUserAuthenticationToken();


        generatedLinkToDesignerForApiKey = authenticationClient.buildRedirectToDesignerForUserAuthenticationToken(userAuthenticationToken, packageId.getId());


        //This is an example url that can be used in an iFrame or to open a browser window with a session (created from the user authentication token) and a redirect to the designer page.
        logger.info(generatedLinkToDesignerForApiKey);
    }

    public String getGeneratedLinkToDesignerForApiKey() {
        return generatedLinkToDesignerForApiKey;
    }
}
