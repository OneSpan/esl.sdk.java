package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.silanis.esl.sdk.DelegationEventReport;

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

        final Map<String, List<DelegationEventReport>> sdkDelegationEventReports =
            Maps.transformEntries(apiDelegationReport.getDelegationEvents(), new Maps.EntryTransformer<String, Collection<com.silanis.esl.api.model.DelegationEventReport>, List<DelegationEventReport>>() {
            @Override
            public List<DelegationEventReport> transformEntry(String key, Collection<com.silanis.esl.api.model.DelegationEventReport> value) {
                return getSDKDelegationEventReportList(value);
            }
        });

        result.getDelegationEventReports().putAll(sdkDelegationEventReports);
        result.setFrom(apiDelegationReport.getFrom());
        result.setTo(apiDelegationReport.getTo());
        return result;
    }

    private List<com.silanis.esl.sdk.DelegationEventReport> getSDKDelegationEventReportList(Collection<com.silanis.esl.api.model.DelegationEventReport> apiDelegationEventReportCollection) {
        return Lists.newArrayList(Iterables.transform(apiDelegationEventReportCollection, new Function<com.silanis.esl.api.model.DelegationEventReport, DelegationEventReport>() {
                @Override
                public DelegationEventReport apply(final com.silanis.esl.api.model.DelegationEventReport input) {
                    return new DelegationEventReportConverter(input).toSDKDelegationEventReport();
                }
            }));
    }

    public com.silanis.esl.api.model.DelegationReport toAPIDelegationReport() {
        if (sdkDelegationReport == null) {
            return apiDelegationReport;
        }

        com.silanis.esl.api.model.DelegationReport result = new com.silanis.esl.api.model.DelegationReport();

        final Map<String, Collection<com.silanis.esl.api.model.DelegationEventReport>> apiDelegationEventReports =
            Maps.transformEntries(sdkDelegationReport.getDelegationEventReports(), new Maps.EntryTransformer<String, List<DelegationEventReport>, Collection<com.silanis.esl.api.model.DelegationEventReport>>() {
                @Override
                public Collection<com.silanis.esl.api.model.DelegationEventReport> transformEntry(String key, List<DelegationEventReport> value) {
                    return getAPIDelegationEventReportCollection(value);
                }
            });

        result.getDelegationEvents().putAll(apiDelegationEventReports);
        result.setFrom(sdkDelegationReport.getFrom());
        result.setTo(sdkDelegationReport.getTo());
        return result;
    }

    private Collection<com.silanis.esl.api.model.DelegationEventReport> getAPIDelegationEventReportCollection(List<com.silanis.esl.sdk.DelegationEventReport> sdkDelegationEventReportList) {
        return Lists.newArrayList(Iterables.transform(sdkDelegationEventReportList, new Function<DelegationEventReport, com.silanis.esl.api.model.DelegationEventReport>() {
            @Override
            public com.silanis.esl.api.model.DelegationEventReport apply(final DelegationEventReport input) {
                return new DelegationEventReportConverter(input).toAPIDelegationEventReport();
            }
        }));
    }
}
