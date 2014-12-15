package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.AuthenticationMethod;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 10:14 AM
 */
public class AuthenticationMethodConverter {

    private com.silanis.esl.sdk.AuthenticationMethod sdkAuthMethod = null;
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
    public AuthenticationMethodConverter(com.silanis.esl.sdk.AuthenticationMethod sdkAuthMethod) {
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

        return sdkAuthMethod.toString();
    }

    /**
     * Convert from API authentication method to SDK authentication method.
     *
     * @return an SDK Authentication Method object.
     */
    public com.silanis.esl.sdk.AuthenticationMethod toSDKAuthMethod() {

        if (apiAuthMethod == null) {
            return sdkAuthMethod;
        }

        sdkAuthMethod = AuthenticationMethod.values().get(apiAuthMethod);
        if (sdkAuthMethod == null)
            return AuthenticationMethod.UNRECOGNIZED(apiAuthMethod);
        else
            return sdkAuthMethod;
    }
    
}
