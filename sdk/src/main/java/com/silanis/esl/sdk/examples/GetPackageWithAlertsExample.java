package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

import java.util.ArrayList;
import java.util.List;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.SystemAlert;
import com.silanis.esl.sdk.builder.FieldBuilder;

/**
 * Creates and retrieves a package which might have alerts associated with it.
 */
public class GetPackageWithAlertsExample extends SDKSample {
    protected DocumentPackage createdPackage;
    protected DocumentPackage updatedPackage;

    public static void main( String... args ) {
        new GetPackageWithAlertsExample().run();
    }

    public DocumentPackage getCreatedPackage() {
        return createdPackage;
    }

    public DocumentPackage getUpdatedPackage() {
        return updatedPackage;
    }

    @Override
    public void execute() {
        DocumentPackage aPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusDays(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(aPackage);
        createdPackage = eslClient.getPackage(packageId);

        // For demonstration purposes, we manually add alerts to the retrieved package and make sure the client cannot store alerts in transaction.
        DocumentPackage testAlertsCannotBeUpdatedInPackage = eslClient.getPackage(packageId);
        List<SystemAlert> alerts = new ArrayList<>();
        alerts.add(new SystemAlert(SystemAlert.SeverityLevel.CRITICAL, "TEST_DOCUMENTS_NOT_SIGNED_ALERT", "The documents are not signed yet."));
        alerts.add(new SystemAlert(SystemAlert.SeverityLevel.WARNING, "TEST_PACKAGE_EXPIRED_ALERT", "The package has expired."));
        testAlertsCannotBeUpdatedInPackage.setAlerts(alerts);

        eslClient.updatePackage(packageId, testAlertsCannotBeUpdatedInPackage);
        updatedPackage = eslClient.getPackage(packageId);
    }
}
