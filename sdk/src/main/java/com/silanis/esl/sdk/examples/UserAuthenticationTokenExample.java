package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AuthenticationClient;

/**
 * A simple example that explains how to create an authentication token for the api key user
 * and then use that token to obtain a session.
 * For a more typical (and complex) example usage: {@link com.silanis.esl.sdk.examples.DesignerRedirectForApiKeyExample}
 * Created by mpoitras on 22/04/14.
 */
public class UserAuthenticationTokenExample extends SDKSample {

    public static void main( String... args ) {
        new UserAuthenticationTokenExample().run();
    }

    private AuthenticationClient authenticationClient;
    public String sessionIdForUser;

    public UserAuthenticationTokenExample() {
        authenticationClient = new AuthenticationClient(webpageUrl);
    }

    @Override
    public void execute() {
        final String userAuthenticationToken = eslClient.getAuthenticationTokensService().createUserAuthenticationToken();
         /* Note about the user authentication token:
          * This is a single use token, limited to a time period (30 minutes). Trying to reuse it or to use it will cause an unauthorized error.
          * This can only be created for the user linked to the api key provided. It is not possible to obtain a token for another user. For exception to this,
          * please refer to the SenderAuthenticationTokenExample
          */

        //This value is ready to be used in a cookie header (or alternatively set as a cookie on the browser). It is a full fledged session valid in the same way as a normal login.
        sessionIdForUser = authenticationClient.getSessionIdForUserAuthenticationToken(userAuthenticationToken);
    }
}
