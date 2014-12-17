package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.AuthenticationMethod;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 10:14 AM
 */
public class AuthenticationMethodConverter {

    private AuthenticationMethod sdkAuthMethod = null;
    private String apiAuthMethod = null;

    /**
     * Construct with API authentication method object involved in conversion.
     *
     * @param apiAuthMethod
     */
    public AuthenticationMethodConverter(String apiAuthMethod) {
        this.apiAuthMethod = apiAuthMethod;
    }

    /**
     * Construct with SDK authentication method object involved in conversion.
     * @param sdkAuthMethod
     */
    public AuthenticationMethodConverter(AuthenticationMethod sdkAuthMethod) {
        this.sdkAuthMethod = sdkAuthMethod;
    }

    /**
     * Convert from SDK authentication method to API authentication method.
     *
     * @return an API Authentication Method object.
     */
    public String toAPIAuthMethod() {
        if (sdkAuthMethod == null) {
            return apiAuthMethod;
        }

        return sdkAuthMethod.getApiValue();
    }

    /**
     * Convert from API authentication method to SDK authentication method.
     *
     * @return an SDK Authentication Method object.
     */
    public AuthenticationMethod toSDKAuthMethod() {

        if (apiAuthMethod == null) {
            return sdkAuthMethod;
        }
        AuthenticationMethod[] authenticationMethods = AuthenticationMethod.values();
        for (AuthenticationMethod authenticationMethod : authenticationMethods) {
            if(apiAuthMethod.equals(authenticationMethod.getApiValue())){
                return authenticationMethod;
            }
        }
        return AuthenticationMethod.UNRECOGNIZED(apiAuthMethod);
    }

}
