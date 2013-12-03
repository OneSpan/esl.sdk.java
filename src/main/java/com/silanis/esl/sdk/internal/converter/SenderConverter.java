package com.silanis.esl.sdk.internal.converter;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 9:16 AM
 *
 * Converter between SDK Sender and API Sender.
 */
public class SenderConverter {

    private com.silanis.esl.sdk.SenderInfo sdkSender = null;
    private com.silanis.esl.api.model.Sender apiSender = null;

    /**
     * Construct with API sender object involved in conversion.
     *
     * @param apiSender
     */
    public SenderConverter(com.silanis.esl.api.model.Sender apiSender) {
        this.apiSender = apiSender;
    }

    /**
     * Construct with SDK sender object involved in conversion.
     *
     * @param sdkSender
     */
    public SenderConverter(com.silanis.esl.sdk.SenderInfo sdkSender) {
        this.sdkSender = sdkSender;
    }

    /**
     * Convert from SDK sender to API sender.
     *
     * @return an API Sender object.
     */
    public com.silanis.esl.api.model.Sender toAPISender() {

        if (sdkSender == null) {
            return apiSender;
        }

        com.silanis.esl.api.model.Sender result = new com.silanis.esl.api.model.Sender();
        if (sdkSender.getFirstName() != null ) {
            result.setFirstName(sdkSender.getFirstName());
        }
        if (sdkSender.getLastName() != null ) {
            result.setLastName( sdkSender.getLastName() );
        }
        if ( sdkSender.getCompany() != null ) {
            result.setCompany( sdkSender.getCompany() );
        }
        if ( sdkSender.getTitle() != null ) {
            result.setTitle( sdkSender.getTitle() );
        }
        return result;
    }

    /**
     * Convert from API sender to SDK sender.
     *
     * @return an SDK Sender object.
     */
    public com.silanis.esl.sdk.SenderInfo toSDKSender() {

        if (apiSender == null) {
            return sdkSender;
        }
        com.silanis.esl.sdk.SenderInfo result = new com.silanis.esl.sdk.SenderInfo();

        result.setFirstName(apiSender.getFirstName());
        result.setLastName(apiSender.getLastName());
        result.setCompany(apiSender.getCompany());
        result.setTitle(apiSender.getTitle());

        return result;
    }    
}
