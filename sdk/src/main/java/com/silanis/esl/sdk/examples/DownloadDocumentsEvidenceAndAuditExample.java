package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Audit;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.io.DownloadedFile;
import com.silanis.esl.sdk.io.Files;

import java.util.List;
import java.util.Properties;

/**
 * Downloads a document, the evidence summary, and the documents zip file
 */
public class DownloadDocumentsEvidenceAndAuditExample {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static void main( String... args ) {
        EslClient esl = new EslClient( API_KEY, API_URL );

        PackageId packageId = new PackageId("8d086f61-09b6-4da1-a385-b12eb3ac3654");
        DownloadedFile downloadedFile = esl.downloadDocument(packageId, "2579a8b01f0e008e");

        Files.saveTo(downloadedFile.getContents(), "download/downloaded.pdf");

        DownloadedFile evidenceContent = esl.downloadEvidenceSummary(packageId);
        Files.saveTo(evidenceContent.getContents(), "download/evidence.pdf");

        DownloadedFile zip = esl.downloadZippedDocuments(packageId);
        Files.saveTo(zip.getContents(), "download/package.zip");

        List<Audit> auditList = esl.getAuditService().getAudit( packageId );

        System.out.println();
    }
}