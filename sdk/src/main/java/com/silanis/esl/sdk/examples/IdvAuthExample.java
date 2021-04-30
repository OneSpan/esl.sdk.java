package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.IdvWorkflowConfig;
import com.silanis.esl.sdk.service.AccountConfigService;

import java.util.List;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.IdvWorkflowBuilder.newIdvWorkflow;
import static com.silanis.esl.sdk.builder.IdvWorkflowConfigBuilder.newIdvWorkflowConfig;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Created by schoi on 2020-10-01.
 */
public class IdvAuthExample extends SDKSample {

    public static final String PHONE_NUMBER = "+15555555555";
    public static final String IDV_WORKFLOW_ID1 = "00000000-0000-0001-0000-200000000055";
    public static final String TYPE1 = "DV";
    public static final String TENANT = "oss";
    public static final String DESC1 = "This is Mitek Document verification only Mock workflow.";

    public static final String IDV_WORKFLOW_ID2 = "00000000-0001-0001-0000-200000000055";
    public static final String TYPE2 = "DVF";
    public static final String DESC2 = "This is Mitek Document Verification with Facial Comparison Mock workflow.";

    public List<IdvWorkflowConfig> idvWorkflowConfigsBeforeCreating, idvWorkflowConfigsAfterCreating,
            idvWorkflowConfigsAfterUpdating, idvWorkflowConfigsAfterDeleting;

    public static void main(String... args) {
        new IdvAuthExample().run();
    }

    public void execute() {
        AccountConfigService accountConfigService = eslClient.getAccountConfigService();
        idvWorkflowConfigsBeforeCreating = accountConfigService.getIdvWorkflowConfigs();

        if (!idvWorkflowConfigsBeforeCreating.isEmpty()) {
            accountConfigService.deleteIdvWorkflowConfigs();
            idvWorkflowConfigsBeforeCreating = accountConfigService.getIdvWorkflowConfigs();
        }

        idvWorkflowConfigsAfterCreating = accountConfigService
                .createIdvWorkflowConfigs(singletonList(newIdvWorkflowConfig(IDV_WORKFLOW_ID1)
                        .withType(TYPE1)
                        .withTenant(TENANT)
                        .withDesc(DESC1)
                        .enableSkipWhenAccessingSignedDocuments()
                        .build()));

        idvWorkflowConfigsAfterUpdating = accountConfigService
                .updateIdvWorkflowConfigs(asList(newIdvWorkflowConfig(IDV_WORKFLOW_ID1)
                        .withType(TYPE1)
                        .withTenant(TENANT)
                        .withDesc(DESC1)
                        .enableSkipWhenAccessingSignedDocuments()
                        .build(), newIdvWorkflowConfig(IDV_WORKFLOW_ID2)
                        .withType(TYPE2)
                        .withTenant(TENANT)
                        .withDesc(DESC2)
                        .enableSkipWhenAccessingSignedDocuments()
                        .build()));

        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a Signer IDV authentication example")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("Jamie Anne")
                        .withLastName("Kurtz")
                        .withIDVAuthentication(PHONE_NUMBER,
                                newIdvWorkflow(IDV_WORKFLOW_ID1)
                                        .withType(TYPE1)
                                        .withTenant(TENANT)
                                        .build()))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = eslClient.getPackage(packageId);

        accountConfigService.deleteIdvWorkflowConfigs();
        idvWorkflowConfigsAfterDeleting = accountConfigService.getIdvWorkflowConfigs();
    }
}