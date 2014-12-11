package com.silanis.esl.sdk.internal.converter;

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

        if(sdkAuthMethod.getValue().equals("EMAIL"))
            return "NONE";
        else if (sdkAuthMethod.getValue().equals("CHALLENGE"))
            return "CHALLENGE";
        else if (sdkAuthMethod.getValue().equals("SMS"))
            return "SMS";
        else if (sdkAuthMethod.getValue().equals("UNRECOGNIZED"))
            return sdkAuthMethod.getUnknownValue();
        else
            return "";
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

        if (apiAuthMethod.equals("CHALLENGE"))
            return sdkAuthMethod.CHALLENGE;
        else if (apiAuthMethod.equals("SMS"))
            return sdkAuthMethod.SMS;
        else if (apiAuthMethod.equals("NONE") || apiAuthMethod.equals("PROVIDER"))
            return sdkAuthMethod.EMAIL;
        else
            return sdkAuthMethod.UNRECOGNIZED(apiAuthMethod);
    }
    
}
