package com.silanis.esl.sdk.internal.converter;

public class SystemAlertConverter {

    private com.silanis.esl.sdk.SystemAlert sdkSystemAlert = null;
    private com.silanis.esl.api.model.SystemAlert apiSystemAlert = null;

    /**
     * Construct with API SystemAlert object involved in conversion.
     *
     * @param apiSystemAlert
     */
    public SystemAlertConverter(com.silanis.esl.api.model.SystemAlert apiSystemAlert) {
        this.apiSystemAlert = apiSystemAlert;
    }

    /**
     * Construct with SDK SystemAlert object involved in conversion.
     *
     * @param sdkSystemAlert
     */
    public SystemAlertConverter(com.silanis.esl.sdk.SystemAlert sdkSystemAlert) {
        this.sdkSystemAlert = sdkSystemAlert;
    }

    public com.silanis.esl.sdk.SystemAlert toSDKSystemAlert() {
        if (apiSystemAlert == null) {
            return sdkSystemAlert;
        }

        com.silanis.esl.sdk.SystemAlert result = new com.silanis.esl.sdk.SystemAlert();
        if (apiSystemAlert.getSeverityLevel() != null) {
            result.setSeverityLevel(com.silanis.esl.sdk.SystemAlert.SeverityLevel.valueOf(apiSystemAlert.getSeverityLevel().name()));
        }
        result.setCode(apiSystemAlert.getCode());
        result.setDefaultMessage(apiSystemAlert.getDefaultMessage());
        result.setParameters(apiSystemAlert.getParameters());
        return result;
    }

    public com.silanis.esl.api.model.SystemAlert toAPISystemAlert() {
        if (sdkSystemAlert == null) {
            return apiSystemAlert;
        }

        com.silanis.esl.api.model.SystemAlert result = new com.silanis.esl.api.model.SystemAlert();
        if (sdkSystemAlert.getSeverityLevel() != null) {
            result.setSeverityLevel(com.silanis.esl.api.model.SystemAlert.SeverityLevel.valueOf(sdkSystemAlert.getSeverityLevel().name()));
        }
        result.setCode(sdkSystemAlert.getCode());
        result.setDefaultMessage(sdkSystemAlert.getDefaultMessage());
        result.setParameters(sdkSystemAlert.getParameters());
        return result;
    }

}
