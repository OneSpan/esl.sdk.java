package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.io.Files;

public class DownloadDocumentsExample {

    public static final String API_KEY = "Q2xubnp5Y2dIQ3lROnNlY3JldA==";
    public static final String API_URL = "https://sandbox.e-signlive.com/api";

    public static void main( String... args ) {
        EslClient esl = new EslClient( API_KEY, API_URL );

        PackageId packageId = new PackageId("MLaNGRrUahYQcSgLahJAhPAGJDYC");
        byte[] documentContent = esl.downloadDocument(packageId, "testing");

        Files.saveTo(documentContent, "downloaded.pdf");

        byte[] evidenceContent = esl.downloadEvidenceSummary(packageId);
        Files.saveTo(evidenceContent, "evidence.pdf");

        byte[] zip = esl.downloadZippedDocuments(packageId);
        Files.saveTo(zip, "package.zip");
    }
}