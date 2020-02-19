package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.EslClient;

import java.util.HashMap;
import java.util.Map;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Created by schoi on 06/09/18.
 */
public class AdditionalRequestHeadersExample extends SDKSample {

    public static void main(String... args) {
        new AdditionalRequestHeadersExample().run();
    }

    public void execute() {
        Map<String, String> additionalRequestHeaders = new HashMap<String, String>();
        additionalRequestHeaders.put("customHeader1", "value1");
        additionalRequestHeaders.put("customHeader2", "value2");
        additionalRequestHeaders.put("customHeader3", "value3");
        additionalRequestHeaders.put("customHeader4", "value4");

        eslClient = setupEslClientFromProps(additionalRequestHeaders);

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using the eSignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John1")
                        .withLastName("Smith1"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}