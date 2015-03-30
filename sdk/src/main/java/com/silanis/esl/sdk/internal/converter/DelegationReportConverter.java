package com.silanis.esl.sdk.internal.converter;

/**
 * Created by schoi on 3/25/15.
 */
public class DelegationReportConverter {

    private com.silanis.esl.sdk.DelegationReport sdkDelegationReport = null;
    private com.silanis.esl.api.model.DelegationReport apiDelegationReport = null;

    /**
     * Construct with API DelegationReport object involved in conversion.
     *
     * @param apiDelegationReport
     */
    public DelegationReportConverter(com.silanis.esl.api.model.DelegationReport apiDelegationReport) {
        this.apiDelegationReport = apiDelegationReport;
    }

    /**
     * Construct with SDK DelegationReport object involved in conversion.
     *
     * @param sdkDelegationReport
     */
    public DelegationReportConverter(com.silanis.esl.sdk.DelegationReport sdkDelegationReport) {
        this.sdkDelegationReport = sdkDelegationReport;
    }

    public com.silanis.esl.sdk.DelegationReport toSDKDelegationReport() {
        if (apiDelegationReport == null) {
            return sdkDelegationReport;
        }

        com.silanis.esl.sdk.DelegationReport result = new com.silanis.esl.sdk.DelegationReport();

        for(com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport : apiDelegationReport.getDelegationEventReports()) {
            result.addDelegationEventReports(new DelegationEventReportConverter(apiDelegationEventReport).toSDKDelegationEventReport());
        }
        result.setFrom(apiDelegationReport.getFrom());
        result.setTo(apiDelegationReport.getTo());
        return result;
    }

    public com.silanis.esl.api.model.DelegationReport toAPIDelegationReport() {
        if (sdkDelegationReport == null) {
            return apiDelegationReport;
        }

        com.silanis.esl.api.model.DelegationReport result = new com.silanis.esl.api.model.DelegationReport();

        for(com.silanis.esl.sdk.DelegationEventReport sdkDelegationEventReport : sdkDelegationReport.getDelegationEventReports()) {
            result.addDelegationEventReport(new DelegationEventReportConverter(sdkDelegationEventReport).toAPIDelegationEventReport());
        }
        result.setFrom(sdkDelegationReport.getFrom());
        result.setTo(sdkDelegationReport.getTo());
        return result;
    }
}
