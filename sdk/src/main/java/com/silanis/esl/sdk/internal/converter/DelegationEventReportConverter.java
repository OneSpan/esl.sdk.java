package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 3/25/15.
 */
public class DelegationEventReportConverter {

    private com.silanis.esl.sdk.DelegationEventReport sdkDelegationEventReport = null;
    private com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport = null;

    /**
     * Construct with API DelegationEventReport object involved in conversion.
     *
     * @param apiDelegationEventReport
     */
    public DelegationEventReportConverter(com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport) {
        this.apiDelegationEventReport = apiDelegationEventReport;
    }

    /**
     * Construct with SDK DelegationEventReport object involved in conversion.
     *
     * @param sdkDelegationEventReport
     */
    public DelegationEventReportConverter(com.silanis.esl.sdk.DelegationEventReport sdkDelegationEventReport) {
        this.sdkDelegationEventReport = sdkDelegationEventReport;
    }

    public com.silanis.esl.sdk.DelegationEventReport toSDKDelegationEventReport() {
        if (apiDelegationEventReport == null) {
            return sdkDelegationEventReport;
        }

        com.silanis.esl.sdk.DelegationEventReport result = new com.silanis.esl.sdk.DelegationEventReport();
        result.setEventDate(apiDelegationEventReport.getEventDate());
        result.setEventDescription(apiDelegationEventReport.getEventDescription());
        result.setEventType(apiDelegationEventReport.getEventType());
        result.setEventUser(apiDelegationEventReport.getEventUser());
        return result;
    }

    public com.silanis.esl.api.model.DelegationEventReport toAPIDelegationEventReport() {
        if (sdkDelegationEventReport == null) {
            return apiDelegationEventReport;
        }

        com.silanis.esl.api.model.DelegationEventReport result = new com.silanis.esl.api.model.DelegationEventReport();
        result.setEventDate(sdkDelegationEventReport.getEventDate());
        result.setEventDescription(sdkDelegationEventReport.getEventDescription());
        result.setEventType(sdkDelegationEventReport.getEventType());
        result.setEventUser(sdkDelegationEventReport.getEventUser());
        return result;
    }
}
