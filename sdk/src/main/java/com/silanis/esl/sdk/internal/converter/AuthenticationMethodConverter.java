package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.silanis.esl.sdk.AuthenticationMethod;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

        try {
            return Iterables.find(Arrays.asList(AuthenticationMethod.values()), new Predicate<AuthenticationMethod>() {
                public boolean apply(AuthenticationMethod authenticationMethod) {
                    return apiAuthMethod.equals(authenticationMethod.getApiValue());
                }
            });
        } catch (NoSuchElementException e) {
            return AuthenticationMethod.UNRECOGNIZED(apiAuthMethod);
        }
    }

}
