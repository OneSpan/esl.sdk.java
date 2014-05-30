package com.silanis.esl.sdk.internal.converter;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 9:16 AM
 *
 * Converter between SDK Sender and API Sender.
 */
public class SenderConverter {

    private com.silanis.esl.sdk.SenderInfo sdkSenderInfo = null;
    private com.silanis.esl.sdk.Sender sdkSender = null;
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
     * @param sdkSenderInfo
     */
    public SenderConverter(com.silanis.esl.sdk.SenderInfo sdkSenderInfo) {
        this.sdkSenderInfo = sdkSenderInfo;
    }

    /**
     * Convert from SDK SenderInfo to API Sender.
     *
     * @return an API Sender object.
     */
    public com.silanis.esl.api.model.Sender toAPISender() {

        if (sdkSenderInfo == null) {
            return apiSender;
        }

        com.silanis.esl.api.model.Sender result = new com.silanis.esl.api.model.Sender();
        if (sdkSenderInfo.getEmail() != null ) {
            result.setEmail( sdkSenderInfo.getEmail() );
        }
        if (sdkSenderInfo.getFirstName() != null ) {
            result.setFirstName(sdkSenderInfo.getFirstName());
        }
        if (sdkSenderInfo.getLastName() != null ) {
            result.setLastName( sdkSenderInfo.getLastName() );
        }
        if ( sdkSenderInfo.getCompany() != null ) {
            result.setCompany( sdkSenderInfo.getCompany() );
        }
        if ( sdkSenderInfo.getTitle() != null ) {
            result.setTitle( sdkSenderInfo.getTitle() );
        }
        return result;
    }

    /**
     * Convert from API Sender to SDK SenderInfo.
     *
     * @return an SDK SenderInfo object.
     */
    public com.silanis.esl.sdk.SenderInfo toSDKSenderInfo() {

        if (apiSender == null) {
            return sdkSenderInfo;
        }
        com.silanis.esl.sdk.SenderInfo result = new com.silanis.esl.sdk.SenderInfo();

        result.setEmail( apiSender.getEmail() );
        result.setFirstName(apiSender.getFirstName());
        result.setLastName(apiSender.getLastName());
        result.setCompany(apiSender.getCompany());
        result.setTitle(apiSender.getTitle());

        return result;
    }

    /**
     * Convert from API Sender to SDK Sender.
     *
     * @return a SDK Sender object.
     */
    public com.silanis.esl.sdk.Sender toSDKSender() {
        if (apiSender == null) {
            return sdkSender;
        }
        com.silanis.esl.sdk.Sender result = new com.silanis.esl.sdk.Sender();

        result.setEmail(apiSender.getEmail());
        result.setId(apiSender.getId());
        result.setFirstName(apiSender.getFirstName());
        result.setLastName(apiSender.getLastName());
        result.setCompany(apiSender.getCompany());
        result.setCreated(apiSender.getCreated());
        result.setLanguage(apiSender.getLanguage());
        result.setName(apiSender.getName());
        result.setPhone(apiSender.getPhone());
        result.setSignerType(apiSender.getSignerType());
        result.setStatus(new SenderStatusConverter(apiSender.getStatus()).toSDKSenderStatus());
        result.setType(new SenderTypeConverter(apiSender.getType()).toSDKSenderType());
        result.setTitle(apiSender.getTitle());
        result.setUpdated(apiSender.getUpdated());

        return result;
    }
}
