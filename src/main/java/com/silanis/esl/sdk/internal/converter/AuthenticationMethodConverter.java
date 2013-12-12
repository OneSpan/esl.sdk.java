package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.AuthScheme;
import com.silanis.esl.sdk.AuthenticationMethod;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 10:14 AM
 */
public class AuthenticationMethodConverter {

    private com.silanis.esl.sdk.AuthenticationMethod sdkAuthMethod = null;
    private com.silanis.esl.api.model.AuthScheme apiAuthMethod = null;

    /**
     * Construct with API authentication method object involved in conversion.
     *
     * @param apiAuthMethod
     */
    public AuthenticationMethodConverter(com.silanis.esl.api.model.AuthScheme apiAuthMethod) {
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
    public com.silanis.esl.api.model.AuthScheme toAPIAuthMethod() {
        if (sdkAuthMethod == null) {
            return apiAuthMethod;
        }

        switch (sdkAuthMethod) {
            case EMAIL:
                return AuthScheme.NONE;
            case CHALLENGE:
                return AuthScheme.CHALLENGE;
            case SMS:
                return AuthScheme.SMS;
        }

        throw new IllegalStateException("Unknown authentication method");

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

        switch (apiAuthMethod) {
            case CHALLENGE:
                return AuthenticationMethod.CHALLENGE;
            case SMS:
                return AuthenticationMethod.SMS;
            default:
                return AuthenticationMethod.EMAIL;
        }
    }
    
}
