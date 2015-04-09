package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.CompletionReport;
import com.silanis.esl.api.model.DelegationReport;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.CompletionReportConverter;
import com.silanis.esl.sdk.internal.converter.DelegationReportConverter;
import com.silanis.esl.sdk.internal.converter.PackageStatusConverter;
import com.silanis.esl.sdk.internal.converter.UsageReportConverter;

import java.util.Date;

/**
 * Created by schoi on 3/25/15.
 */
public class ReportService {

    private UrlTemplate template;
    private RestClient client;

    public ReportService(RestClient client, String baseUrl) {
        this.client = client;
        template = new UrlTemplate(baseUrl);
    }


    /**
     * Downloads the completion report from a sender
     *
     * @param packageStatus Status of the packages
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The completion report
     * @return The completion report
     */
    public com.silanis.esl.sdk.CompletionReport downloadCompletionReport(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, senderId, from, to);

        try {
            String json = client.get(path);
            CompletionReport apiCompletionReport = Serialization.fromJson(json, CompletionReport.class);
            return new CompletionReportConverter(apiCompletionReport).toSDKCompletionReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the completion report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the completion report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from a sender in csv format.
     *
     * @param packageStatus Status of the packages
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The completion report in csv format
     */
    public String downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus packageStatus, String senderId, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, senderId, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the completion report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the completion report in csv." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from all senders
     *
     * @param packageStatus Status of the packages
     * @param from Starting date
     * @param to Ending date
     * @return The completion report
     * @return The completion report
     */
    public com.silanis.esl.sdk.CompletionReport downloadCompletionReport(com.silanis.esl.sdk.PackageStatus packageStatus, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, from, to);

        try {
            String json = client.get(path);
            CompletionReport apiCompletionReport = Serialization.fromJson(json, CompletionReport.class);
            return new CompletionReportConverter(apiCompletionReport).toSDKCompletionReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the completion report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the completion report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the completion report from all senders in csv format.
     *
     * @param packageStatus Status of the packages
     * @param from Starting date
     * @param to Ending date
     * @return The completion report in csv format
     */
    public String downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus packageStatus, Date from, Date to) {
        String path = buildCompletionReportUrl(packageStatus, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the completion report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the completion report in csv." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the usage report.
     *
     * @param from Starting date
     * @param to Ending date
     * @return The usage report
     */
    public com.silanis.esl.sdk.UsageReport downloadUsageReport(Date from, Date to) {
        String path = buildUsageReportUrl(from, to);

        try {
            String json = client.get(path);
            com.silanis.esl.api.model.UsageReport apiUsageReport = Serialization.fromJson(json, com.silanis.esl.api.model.UsageReport.class);
            return new UsageReportConverter(apiUsageReport).toSDKUsageReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the usage report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the usage report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the usage report in csv format.
     * @param from Starting date
     * @param to Ending date
     * @return The usage report in csv format
     */
    public String downloadUsageReportAsCSV(Date from, Date to) {
        String path = buildUsageReportUrl(from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the usage report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the usage report in csv." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the delegation report.
     *
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The usage report
     */
    public com.silanis.esl.sdk.DelegationReport downloadDelegationReport(String senderId, Date from, Date to) {
        String path = buildDelegationReportUrl(senderId, from, to);

        try {
            String json = client.get(path);
            DelegationReport apiDelegationReport = Serialization.fromJson(json, com.silanis.esl.api.model.DelegationReport.class);
            return new DelegationReportConverter(apiDelegationReport).toSDKDelegationReport();
        }
        catch (RequestException e) {
            throw new EslServerException("Could not download the delegation report.", e);
        }
        catch (Exception e) {
            throw new EslException("Could not download the delegation report." + " Exception: " + e.getMessage());
        }
    }

    /**
     * Downloads the delegation report in csv format.
     * @param senderId Id of the sender
     * @param from Starting date
     * @param to Ending date
     * @return The usage report in csv format
     */
    public String downloadDelegationReportAsCSV(String senderId, Date from, Date to) {
        String path = buildDelegationReportUrl(senderId, from, to);

        try {
            return client.get(path, "text/csv");
        } catch (RequestException e) {
            throw new EslException("Could not download the delegation report in csv.", e);
        } catch (Exception e) {
            throw new EslException("Could not download the delegation report in csv." + " Exception: " + e.getMessage());
        }
    }

    private String buildCompletionReportUrl(PackageStatus packageStatus, String senderId, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.COMPLETION_REPORT_PATH)
                       .replace("{from}", fromDate)
                       .replace("{to}", toDate)
                       .replace("{status}", new PackageStatusConverter(packageStatus).toAPIPackageStatus())
                       .replace("{senderId}", senderId)
                       .build();
    }

    private String buildCompletionReportUrl(PackageStatus packageStatus, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.COMPLETION_REPORT_FOR_ALL_SENDERS_PATH)
                       .replace("{from}", fromDate)
                       .replace("{to}", toDate)
                       .replace("{status}", new PackageStatusConverter(packageStatus).toAPIPackageStatus())
                       .build();
    }

    private String buildUsageReportUrl(Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.USAGE_REPORT_PATH)
                       .replace("{from}", fromDate)
                       .replace("{to}", toDate)
                       .build();
    }

    private String buildDelegationReportUrl(String senderId, Date from, Date to) {
        String toDate = DateHelper.dateToIsoUtcFormat(to);
        String fromDate = DateHelper.dateToIsoUtcFormat(from);

        return template.urlFor(UrlTemplate.DELEGATION_REPORT_PATH)
                       .replace("{senderId}", senderId)
                       .replace("{from}", fromDate)
                       .replace("{to}", toDate)
                       .build();
    }
}
