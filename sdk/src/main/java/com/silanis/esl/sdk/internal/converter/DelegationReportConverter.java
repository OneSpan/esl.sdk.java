package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.DelegationEventReport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

        for(Map.Entry<String, Collection<com.silanis.esl.api.model.DelegationEventReport>> apiDelegationEventReportMap : apiDelegationReport.getDelegationEvents().entrySet()) {
            result.getDelegationEventReports().put(apiDelegationEventReportMap.getKey(), getSDKDelegationEventReportList(apiDelegationEventReportMap.getValue()));
        }
        result.setFrom(apiDelegationReport.getFrom());
        result.setTo(apiDelegationReport.getTo());
        return result;
    }

    private List<com.silanis.esl.sdk.DelegationEventReport> getSDKDelegationEventReportList(Collection<com.silanis.esl.api.model.DelegationEventReport> apiDelegationEventReportCollection) {
        List<DelegationEventReport> sdkDelegationEventReportList = new ArrayList<com.silanis.esl.sdk.DelegationEventReport>();
        for(com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport : apiDelegationEventReportCollection) {
            sdkDelegationEventReportList.add(new DelegationEventReportConverter(apiDelegationEventReport).toSDKDelegationEventReport());
        }
        return sdkDelegationEventReportList;
    }

    public com.silanis.esl.api.model.DelegationReport toAPIDelegationReport() {
        if (sdkDelegationReport == null) {
            return apiDelegationReport;
        }

        com.silanis.esl.api.model.DelegationReport result = new com.silanis.esl.api.model.DelegationReport();

        for(Map.Entry<String, List<com.silanis.esl.sdk.DelegationEventReport>> sdkDelegationEventReportMap : sdkDelegationReport.getDelegationEventReports().entrySet()) {
            result.getDelegationEvents().put(sdkDelegationEventReportMap.getKey(), getAPIDelegationEventReportCollection(sdkDelegationEventReportMap.getValue()));
        }
        result.setFrom(sdkDelegationReport.getFrom());
        result.setTo(sdkDelegationReport.getTo());
        return result;
    }

    private Collection<com.silanis.esl.api.model.DelegationEventReport> getAPIDelegationEventReportCollection(List<com.silanis.esl.sdk.DelegationEventReport> sdkDelegationEventReportList) {
        Collection<com.silanis.esl.api.model.DelegationEventReport> apiDelegationEventReportCollection = new ArrayList<com.silanis.esl.api.model.DelegationEventReport>();
        for(com.silanis.esl.sdk.DelegationEventReport sdkDelegationEventReport : sdkDelegationEventReportList) {
            apiDelegationEventReportCollection.add(new DelegationEventReportConverter(sdkDelegationEventReport).toAPIDelegationEventReport());
        }
        return apiDelegationEventReportCollection;
    }
}
