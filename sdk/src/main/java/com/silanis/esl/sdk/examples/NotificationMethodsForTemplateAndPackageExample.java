package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.PackageBuilder;

import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;


public class NotificationMethodsForTemplateAndPackageExample extends SDKSample{
    protected DocumentPackage templatePackage;
    protected DocumentPackage createdPackage;
    protected DocumentPackage signerUpdatedPackage;

    public DocumentPackage getTemplatePackage() {
        return templatePackage;
    }
    public DocumentPackage getCreatedPackage() {
        return createdPackage;
    }
    public DocumentPackage getSignerUpdatedPackage() {
        return signerUpdatedPackage;
    }

    public static final String PACKAGE_DESCRIPTION = "This example is created to demonstrate that package's signer update changes signer's notification methods";
    public static final String PACKAGE_SIGNER1_FIRST = "John";
    public static final String PACKAGE_SIGNER1_LAST = "Smith";
    public static final String PACKAGE_SIGNER1_CUSTOM_ID = "Signer1";
    public static final String PACKAGE_SIGNER1_PHONE = "+12042345678";

    public static void main(String... args) {
        new NotificationMethodsForTemplateAndPackageExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage template = PackageBuilder.newPackageNamed("Template " + getPackageName())
                .describedAs("first message")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName(PACKAGE_SIGNER1_FIRST)
                        .withLastName(PACKAGE_SIGNER1_LAST)
                        .withCustomId(PACKAGE_SIGNER1_CUSTOM_ID)
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                                .withPhoneNumber(PACKAGE_SIGNER1_PHONE)))
                .build();

        template.setId(eslClient.getTemplateService().createTemplate(template));
        templatePackage = template;

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs(PACKAGE_DESCRIPTION)
                .build();

        // Verify signer's NM when create package from template
        packageId = eslClient.getTemplateService().createPackageFromTemplate(template.getId(), newPackage);
        createdPackage = eslClient.getPackage(packageId);

        // Able to update signer's NM during signer update
        Signer signer1 = eslClient.getPackageService().getSigner(packageId, PACKAGE_SIGNER1_CUSTOM_ID);
        signer1.setNotificationPrimaryMethods(NotificationMethod.EMAIL);
        signer1.setNotificationPhoneNumber("+15147623743");

        // Signer's change of NM should be reflected on the new Signer updated package.
        eslClient.getPackageService().updateSigner(packageId, signer1);
        signerUpdatedPackage = eslClient.getPackage(packageId);
    }
}
