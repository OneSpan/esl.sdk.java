package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

public class CreatePackageOneStepWithSignerExample extends SDKSample {

    public static void main(String... args) {
        new CreatePackageOneStepWithSignerExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage pkg1 = PackageBuilder.newPackageNamed(getPackageName())
                .withSigner(SignerBuilder.newSignerWithEmail( email1 )
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withSSOAuthentication()
                )
                .build();
        packageId = eslClient.createPackageOneStep(pkg1);
    }

}
