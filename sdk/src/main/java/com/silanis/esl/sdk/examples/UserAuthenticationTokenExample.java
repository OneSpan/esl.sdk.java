package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;
import java.util.Properties;

/**
 * Created by mpoitras on 22/04/14.
 */
public class UserAuthenticationTokenExample extends SDKSample {

    private AuthenticationClient authenticationClient;
    private String email1;

    public UserAuthenticationTokenExample(Properties props) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "auth.url" ),
              props.getProperty( "1.email" ));
    }

    public UserAuthenticationTokenExample(String apiKey, String apiUrl, String authUrl, String email1) {
        super( apiKey, apiUrl);
        authenticationClient = new AuthenticationClient(authUrl);
        this.email1 = email1;
    }

    @Override
    void execute() {
        final String userAuthenticationToken = eslClient.createUserAuthenticationToken();

        //This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser)
        final String sessionIdForUser = authenticationClient.getSessionIdForUserAuthenticationToken(userAuthenticationToken);
    }

}
