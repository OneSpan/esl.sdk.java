package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageListOptions;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;

import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.PackageListOptionsBuilder.newPackageListOptions;
import static com.silanis.esl.sdk.builder.PackageRetrievalOptionsBuilder.newPackageRetrievalOptions;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateTime.now;

/**
 * Example demonstrating how to retrieve the expiration date of a package in the transaction time zone,
 * both for a single package and for a page of packages.
 * <p>
 * By default the expiration date is returned in UTC. With the useTransactionTimezone query parameter, it is also
 * available with the offset of the package time zone through {@link DocumentPackage#getExpiryDateWithOffset()}.
 */
public class GetPackageInTransactionTimezoneExample extends SDKSample {

    // A time zone without daylight saving time, so its offset is always +03:30
    public static final String PACKAGE_TIMEZONE_ID = "Asia/Tehran";

    private DocumentPackage packageInUtc;
    private DocumentPackage packageInTransactionTimezone;
    private DocumentPackage listedPackageInTransactionTimezone;

    public static void main(String... args) {
        new GetPackageInTransactionTimezoneExample().run();
    }

    @Override
    public void execute() {
        Date createdFrom = now().minusMinutes(1).toDate();

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .withTimezoneId(PACKAGE_TIMEZONE_ID)
                .expiresAt(now().plusMonths(1).toDate())
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        // Default behaviour: the expiration date is returned in UTC
        packageInUtc = eslClient.getPackage(packageId);

        // The expiration date is returned in the transaction time zone
        packageInTransactionTimezone = eslClient.getPackage(packageId,
                newPackageRetrievalOptions().withTransactionTimezone().build());
        System.out.println("Expiry date in the transaction time zone: " + packageInTransactionTimezone.getExpiryDateWithOffset());

        // The same option applies to a page of packages
        PackageListOptions listOptions = newPackageListOptions()
                .withStatus(PackageStatus.DRAFT.name())
                .withDateRange(createdFrom, now().plusMinutes(1).toDate())
                .withTransactionTimezone()
                .build();
        Page<DocumentPackage> resultPage = eslClient.getPackageService().getPackagesWithOptions(new PageRequest(1), listOptions);
        for (DocumentPackage documentPackage : resultPage) {
            if (packageId.equals(documentPackage.getId())) {
                listedPackageInTransactionTimezone = documentPackage;
            }
        }
    }

    public DocumentPackage getPackageInUtc() {
        return packageInUtc;
    }

    public DocumentPackage getPackageInTransactionTimezone() {
        return packageInTransactionTimezone;
    }

    public DocumentPackage getListedPackageInTransactionTimezone() {
        return listedPackageInTransactionTimezone;
    }
}
