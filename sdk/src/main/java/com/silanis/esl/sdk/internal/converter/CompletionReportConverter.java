package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.DocumentsCompletionReport;
import com.silanis.esl.api.model.PackageCompletionReport;
import com.silanis.esl.api.model.SenderCompletionReport;
import com.silanis.esl.api.model.SignersCompletionReport;

import java.util.List;

/**
 * Created by lena on 2014-05-28.
 * <p/>
 * Converter for API CompletionReport to SDK CompletionReport.
 */
public class CompletionReportConverter {

    private com.silanis.esl.sdk.CompletionReport sdkCompletionReport = null;
    private com.silanis.esl.api.model.CompletionReport apiCompletionReport = null;

    /**
     * Construct with API CompletionReport object involved in conversion.
     *
     * @param apiCompletionReport
     */
    public CompletionReportConverter(com.silanis.esl.api.model.CompletionReport apiCompletionReport) {
        this.apiCompletionReport = apiCompletionReport;
    }

    /**
     * Convert from API CompletionReport to SDK CompletionReport.
     *
     * @return a SDK CompletionReport object
     */
    public com.silanis.esl.sdk.CompletionReport toSDKCompletionReport() {

        if (apiCompletionReport == null) {
            return sdkCompletionReport;
        }
        List<com.silanis.esl.api.model.SenderCompletionReport> senderCompletionReportList = apiCompletionReport.getSenders();

        if (senderCompletionReportList.size() != 0) {
            com.silanis.esl.sdk.CompletionReport result = new com.silanis.esl.sdk.CompletionReport();
            result.setFrom(apiCompletionReport.getFrom());
            result.setTo(apiCompletionReport.getTo());

            com.silanis.esl.sdk.SenderCompletionReport sdkSenderCompletionReport;
            for (SenderCompletionReport apiSenderCompletionReport : senderCompletionReportList) {
                sdkSenderCompletionReport = toSDKSenderCompletionReport(apiSenderCompletionReport);

                List<PackageCompletionReport> packageCompletionReportList = apiSenderCompletionReport.getPackages();
                com.silanis.esl.sdk.PackageCompletionReport sdkPackageCompletionReport;
                for (PackageCompletionReport apiPackageCompletionReport : packageCompletionReportList) {
                    sdkPackageCompletionReport = toSDKPackageCompletionReport(apiPackageCompletionReport);

                    List<DocumentsCompletionReport> documentCompletionReportList = packageCompletionReportList.get(0).getDocuments();
                    com.silanis.esl.sdk.DocumentsCompletionReport sdkDocumentsCompletionReport;
                    for (DocumentsCompletionReport apiDocumentsCompletionReport : documentCompletionReportList) {
                        sdkDocumentsCompletionReport = toSDKDocumentCompletionReport(apiDocumentsCompletionReport);
                        sdkPackageCompletionReport.addDocument(sdkDocumentsCompletionReport);
                    }

                    List<SignersCompletionReport> signersCompletionReportList = packageCompletionReportList.get(0).getSigners();
                    com.silanis.esl.sdk.SignersCompletionReport sdkSignersCompletionReport;
                    for (SignersCompletionReport apiSignersCompletionReport : signersCompletionReportList) {
                        sdkSignersCompletionReport = toSDKSignersCompletionReport(apiSignersCompletionReport);
                        sdkPackageCompletionReport.addSigner(sdkSignersCompletionReport);
                    }

                    sdkSenderCompletionReport.addPackage(sdkPackageCompletionReport);
                }

                result.addSender(sdkSenderCompletionReport);
            }

            return result;
        }

        return sdkCompletionReport;
    }

    // Convert from API to SDK SenderCompletionReport
    private com.silanis.esl.sdk.SenderCompletionReport toSDKSenderCompletionReport(com.silanis.esl.api.model.SenderCompletionReport apiSenderCompletionReport) {
        com.silanis.esl.sdk.SenderCompletionReport sdkSenderCompletionReport = new com.silanis.esl.sdk.SenderCompletionReport();
        sdkSenderCompletionReport.setSender(new SenderConverter(apiSenderCompletionReport.getSender()).toSDKSender());

        return sdkSenderCompletionReport;
    }

    // Convert from API to SDK PackageCompletionReport
    private com.silanis.esl.sdk.PackageCompletionReport toSDKPackageCompletionReport(com.silanis.esl.api.model.PackageCompletionReport apiPackageCompletionReport) {
        com.silanis.esl.sdk.PackageCompletionReport sdkPackageCompletionReport = new com.silanis.esl.sdk.PackageCompletionReport(apiPackageCompletionReport.getName());
        sdkPackageCompletionReport.setId(apiPackageCompletionReport.getId());
        sdkPackageCompletionReport.setCreated(apiPackageCompletionReport.getCreated());
        sdkPackageCompletionReport.setPackageStatus(apiPackageCompletionReport.getStatus());
        sdkPackageCompletionReport.setTrashed(apiPackageCompletionReport.getTrashed());

        return sdkPackageCompletionReport;
    }

    // Convert from API to SDK DocumentsCompletionReport
    private com.silanis.esl.sdk.DocumentsCompletionReport toSDKDocumentCompletionReport(com.silanis.esl.api.model.DocumentsCompletionReport apiDocumentsCompletionReport) {
        com.silanis.esl.sdk.DocumentsCompletionReport sdkDocumentsCompletionReport = new com.silanis.esl.sdk.DocumentsCompletionReport(apiDocumentsCompletionReport.getName());
        sdkDocumentsCompletionReport.setId(apiDocumentsCompletionReport.getId());

        if (apiDocumentsCompletionReport.getCompleted() != null) {
            sdkDocumentsCompletionReport.setCompleted(apiDocumentsCompletionReport.getCompleted());
        }

        if (apiDocumentsCompletionReport.getFirstSigned() != null) {
            sdkDocumentsCompletionReport.setFirstSigned(apiDocumentsCompletionReport.getFirstSigned());
        }

        if (apiDocumentsCompletionReport.getLastSigned() != null) {
            sdkDocumentsCompletionReport.setLastSigned(apiDocumentsCompletionReport.getLastSigned());
        }

        return sdkDocumentsCompletionReport;
    }

    // Convert from API to SDK SignersCompletionReport
    private com.silanis.esl.sdk.SignersCompletionReport toSDKSignersCompletionReport(com.silanis.esl.api.model.SignersCompletionReport apiSignersCompletionReport) {
        com.silanis.esl.sdk.SignersCompletionReport sdkSignersCompletionReport = new com.silanis.esl.sdk.SignersCompletionReport(apiSignersCompletionReport.getFirstName(), apiSignersCompletionReport.getLastName());
        sdkSignersCompletionReport.setEmail(apiSignersCompletionReport.getEmail());
        sdkSignersCompletionReport.setId(apiSignersCompletionReport.getId());

        if (apiSignersCompletionReport.getCompleted() != null) {
            sdkSignersCompletionReport.setCompleted(apiSignersCompletionReport.getCompleted());
        }

        if (apiSignersCompletionReport.getFirstSigned() != null) {
            sdkSignersCompletionReport.setFirstSigned(apiSignersCompletionReport.getFirstSigned());
        }

        if (apiSignersCompletionReport.getLastSigned() != null) {
            sdkSignersCompletionReport.setLastSigned(apiSignersCompletionReport.getLastSigned());
        }

        return sdkSignersCompletionReport;
    }
}
