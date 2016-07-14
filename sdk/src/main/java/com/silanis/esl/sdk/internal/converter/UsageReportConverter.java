package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.sdk.SenderUsageReport;
import com.silanis.esl.sdk.UsageReportCategory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lena on 2014-07-25.
 *
 * Converter for API UsageReport to SDK UsageReport.
 */
public class UsageReportConverter {

    private com.silanis.esl.sdk.UsageReport sdkUsageReport = null;
    private com.silanis.esl.api.model.UsageReport apiUsageReport = null;

    /**
     * Construct with API UsageReport object involved in conversion.
     *
     * @param apiUsageReport
     */
    public UsageReportConverter(com.silanis.esl.api.model.UsageReport apiUsageReport) {
        this.apiUsageReport = apiUsageReport;
    }

    public com.silanis.esl.sdk.UsageReport toSDKUsageReport() {
        if (apiUsageReport == null) {
            return sdkUsageReport;
        }

        List<com.silanis.esl.api.model.SenderUsageReport> senderUsageReportList = apiUsageReport.getSenders();

        if (senderUsageReportList.size() != 0) {
            com.silanis.esl.sdk.UsageReport result = new com.silanis.esl.sdk.UsageReport();
            result.setFrom(apiUsageReport.getFrom());
            result.setTo(apiUsageReport.getTo());
            result.setSenderUsageReports(Lists.newArrayList(Iterables.transform(senderUsageReportList, new Function<com.silanis.esl.api.model.SenderUsageReport, SenderUsageReport>() {
                @Override
                public SenderUsageReport apply(final com.silanis.esl.api.model.SenderUsageReport input) {
                    return toSDKSenderUsageReport(input);
                }
            })));

            return result;
        }

        return sdkUsageReport;
    }

    private com.silanis.esl.sdk.SenderUsageReport toSDKSenderUsageReport(com.silanis.esl.api.model.SenderUsageReport apiSenderUsageReport) {
        com.silanis.esl.sdk.SenderUsageReport sdkSenderUsageReport = new com.silanis.esl.sdk.SenderUsageReport();
        sdkSenderUsageReport.setSender(new SenderConverter(apiSenderUsageReport.getSender()).toSDKSender());

        EnumMap<UsageReportCategory, Integer> categoryCount = new EnumMap<UsageReportCategory, Integer>(UsageReportCategory.class);
        for (Map.Entry<String, Object> entry : apiSenderUsageReport.getPackages().entrySet()) {
            UsageReportCategory usageReportCategory = UsageReportCategory.valueOf(entry.getKey().toUpperCase());
            categoryCount.put(usageReportCategory, (Integer) entry.getValue());
        }
        sdkSenderUsageReport.setCountByUsageReportCategory(categoryCount);

        return sdkSenderUsageReport;
    }
}
