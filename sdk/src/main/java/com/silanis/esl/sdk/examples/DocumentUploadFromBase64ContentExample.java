package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.util.Arrays;
import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

/**
 * Example to show how to upload a document with base64 content of Json format to a package.
 */
public class DocumentUploadFromBase64ContentExample extends SDKSample {

    public static final String DOCUMENT1_NAME = "First Document";
    public static final String DOCUMENT2_NAME = "Second Document";
    public static final String DOCUMENT1_ID = "documentId1";
    public static final String DOCUMENT2_ID = "documentId12";
    public static final String BASE64_CONTENT = "JVBERi0xLjUKJcOkw7zDtsOfCjIgMCBvYmoKPDwvTGVuZ3RoIDMgMCBSL0ZpbHRlci9GbGF0ZURlY29kZT4+CnN0cmVhbQp4nDPQM1Qo5ypUMFAw0DMwslAwMzTUszQ3VDC3hNBFqVzhWgp5XIEKALasCKwKZW5kc3RyZWFtCmVuZG9iagoKMyAwIG9iago0NQplbmRvYmoKCjUgMCBvYmoKPDwKPj4KZW5kb2JqCgo2IDAgb2JqCjw8L0ZvbnQgNSAwIFIKL1Byb2NTZXRbL1BERi9UZXh0XQo+PgplbmRvYmoKCjEgMCBvYmoKPDwvVHlwZS9QYWdlL1BhcmVudCA0IDAgUi9SZXNvdXJjZXMgNiAwIFIvTWVkaWFCb3hbMCAwIDYxMiA3OTJdL0dyb3VwPDwvUy9UcmFuc3BhcmVuY3kvQ1MvRGV2aWNlUkdCL0kgdHJ1ZT4+L0NvbnRlbnRzIDIgMCBSPj4KZW5kb2JqCgo0IDAgb2JqCjw8L1R5cGUvUGFnZXMKL1Jlc291cmNlcyA2IDAgUgovTWVkaWFCb3hbIDAgMCA2MTIgNzkyIF0KL0tpZHNbIDEgMCBSIF0KL0NvdW50IDE+PgplbmRvYmoKCjcgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSCi9PcGVuQWN0aW9uWzEgMCBSIC9YWVogbnVsbCBudWxsIDBdCi9MYW5nKGVuLUNBKQo+PgplbmRvYmoKCjggMCBvYmoKPDwvQ3JlYXRvcjxGRUZGMDA1NzAwNzIwMDY5MDA3NDAwNjUwMDcyPgovUHJvZHVjZXI8RkVGRjAwNEMwMDY5MDA2MjAwNzIwMDY1MDA0RjAwNjYwMDY2MDA2OTAwNjMwMDY1MDAyMDAwMzYwMDJFMDAzND4KL0NyZWF0aW9uRGF0ZShEOjIwMjEwODI2MTM0NjU3LTA0JzAwJyk+PgplbmRvYmoKCnhyZWYKMCA5CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDIyOSAwMDAwMCBuIAowMDAwMDAwMDE5IDAwMDAwIG4gCjAwMDAwMDAxMzUgMDAwMDAgbiAKMDAwMDAwMDM3MSAwMDAwMCBuIAowMDAwMDAwMTU0IDAwMDAwIG4gCjAwMDAwMDAxNzYgMDAwMDAgbiAKMDAwMDAwMDQ2OSAwMDAwMCBuIAowMDAwMDAwNTY1IDAwMDAwIG4gCnRyYWlsZXIKPDwvU2l6ZSA5L1Jvb3QgNyAwIFIKL0luZm8gOCAwIFIKL0lEIFsgPDk1NDhDMUE4RTFCQ0RCRkQ0ODAxMzQyOEIzNEEyQ0E5Pgo8OTU0OEMxQThFMUJDREJGRDQ4MDEzNDI4QjM0QTJDQTk+IF0KL0RvY0NoZWNrc3VtIC85QUJEOEQ0NUEzMDgzMEMzMzQ5MUYzOTYwNkY0MkEyOQo+PgpzdGFydHhyZWYKNzM5CiUlRU9GCg==";
    public Document document1, document2;
    public List<Document> uploadedDocuments;

    public static void main( String... args ) {
        new DocumentUploadFromBase64ContentExample().run();
    }

    public void execute() {

        documentInputStream2 = this.getClass().getClassLoader().getResourceAsStream("taggedDocument.pdf");

        // 1. Create a package
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId("Client1")
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCompany("Acme Inc."))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        superDuperPackage.setId(packageId);

        // 2. Construct documents
        document1 = newDocumentWithName(DOCUMENT1_NAME)
                .withId(DOCUMENT1_ID)
                .fromBase64Content(BASE64_CONTENT)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .withField(FieldBuilder.checkBox()
                                .onPage(0)
                                .atPosition(400, 200)
                                .withValue(FieldBuilder.CHECKBOX_CHECKED))
                        .atPosition(100, 100))
                .build();

        document2 = newDocumentWithName(DOCUMENT2_NAME)
                .withId(DOCUMENT2_ID)
                .fromBase64Content(BASE64_CONTENT)
                .withSignature(signatureFor(email1)
                        .onPage(0)
                        .withField(FieldBuilder.checkBox()
                                .onPage(0)
                                .atPosition(400, 200)
                                .withValue(FieldBuilder.CHECKBOX_CHECKED))
                        .atPosition(100, 100))
                .build();

        // 3. Upload the documents to the created package by uploading the document.
        uploadedDocuments = eslClient.uploadDocumentsWithBase64Content(packageId, Arrays.asList(document1, document2));

        eslClient.sendPackage(superDuperPackage.getId());

        SessionToken sessionToken = eslClient.getSessionService().createSessionToken( superDuperPackage.getId().toString(), "Client1" );
    }
}
