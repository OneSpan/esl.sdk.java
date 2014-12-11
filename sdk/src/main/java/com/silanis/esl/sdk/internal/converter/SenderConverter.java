package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Sender;

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
     * @param sdkSender
     */
    public SenderConverter(com.silanis.esl.sdk.Sender sdkSender) {
        this.sdkSender = sdkSender;
    }

    /**
     * Construct with SDK senderInfo object involved in conversion.
     *
     * @param sdkSenderInfo
     */
    public SenderConverter(com.silanis.esl.sdk.SenderInfo sdkSenderInfo) {
        this.sdkSenderInfo = sdkSenderInfo;
        if(sdkSenderInfo != null) {
            sdkSender = new Sender();
            sdkSender.setEmail(sdkSenderInfo.getEmail());
            sdkSender.setFirstName(sdkSenderInfo.getFirstName());
            sdkSender.setLastName(sdkSenderInfo.getLastName());
            sdkSender.setCompany(sdkSenderInfo.getCompany());
            sdkSender.setTitle(sdkSenderInfo.getTitle());
        }
    }

    /**
     * Convert from SDK Sender to API Sender.
     *
     * @return an API Sender object.
     */
    public com.silanis.esl.api.model.Sender toAPISender() {

        if (sdkSender == null) {
            return apiSender;
        }

        com.silanis.esl.api.model.Sender result = new com.silanis.esl.api.model.Sender();

        if(sdkSender.getEmail() != null)
            result.setEmail(sdkSender.getEmail());
        if(sdkSender.getId() != null)
            result.setId(sdkSender.getId());
        if(sdkSender.getFirstName() != null)
            result.setFirstName(sdkSender.getFirstName());
        if(sdkSender.getLastName() != null)
            result.setLastName(sdkSender.getLastName());
        if(sdkSender.getCompany() != null)
            result.setCompany(sdkSender.getCompany());
        if(sdkSender.getCreated() != null)
            result.setCreated(sdkSender.getCreated());
        if(sdkSender.getLanguage() != null)
            result.setLanguage(sdkSender.getLanguage());
        if (sdkSender.getName() != null)
            result.setName(sdkSender.getName());
        if (sdkSender.getPhone() != null)
            result.setPhone(sdkSender.getPhone());
        if (sdkSender.getStatus() != null)
            result.setStatus(new SenderStatusConverter(sdkSender.getStatus()).toAPISenderStatus());
        if (sdkSender.getType() != null)
            result.setType(new SenderTypeConverter(sdkSender.getType()).toAPISenderType());
        if (sdkSender.getTitle() != null)
            result.setTitle(sdkSender.getTitle());
        if (sdkSender.getUpdated() != null)
            result.setUpdated(sdkSender.getUpdated());
        if (sdkSender.getExternal() != null)
            result.setExternal(new ExternalConverter(sdkSender.getExternal()).toAPIExternal());

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
        result.setStatus(new SenderStatusConverter(apiSender.getStatus()).toSDKSenderStatus());
        result.setType(new SenderTypeConverter(apiSender.getType()).toSDKSenderType());
        result.setTitle(apiSender.getTitle());
        result.setUpdated(apiSender.getUpdated());
        result.setExternal(new ExternalConverter(apiSender.getExternal()).toSDKExternal());

        return result;
    }
}
