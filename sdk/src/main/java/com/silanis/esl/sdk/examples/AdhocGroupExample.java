package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newAdHocGroupSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example class demonstrating how to prepare a transaction with an ad-hoc group signer.
 */
public class AdhocGroupExample extends SDKSample {

    public static final String ADHOC_GROUP_ID = "adhoc-group-id";
    public static final String SIGNER_1 = "signer1";
    public static final String SIGNER_2 = "signer2";

    public static final String ADHOC_GROUP_NAME = "Example Adhoc Group Name";

    public static void main(String... args) {
        new AdhocGroupExample().run();
    }

    public void execute() {
        final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER_1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withCompany("Acme Inc.")
                        .withSmsSentTo("1234567890")
                )
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(SIGNER_2)
                        .withFirstName("Olivia")
                        .withLastName("Brown")
                        .withCompany("Acme Inc.")
                )
                .withSigner(newAdHocGroupSigner()
                        .withCustomId(ADHOC_GROUP_ID)
                        .withGroup(GroupBuilder.newGroup(ADHOC_GROUP_NAME)
                                .withMember(GroupMemberBuilder.newAdHocGroupMember()
                                        .withUserId(SIGNER_1)
                                        .as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                                        .build())
                                .withMember(GroupMemberBuilder.newAdHocGroupMember()
                                        .withUserId(SIGNER_2)
                                        .as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                                        .build())
                                .build())
                )
                .withDocument(newDocumentWithName("Example Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId("documentId")
                        .withSignature(signatureFor(new Placeholder(ADHOC_GROUP_ID))
                                .atPosition(100, 200)
                                .onPage(0))
                        .build())
                .build();

        packageId = this.eslClient.createPackageOneStep(superDuperPackage);
        eslClient.sendPackage(packageId);
        retrievedPackage = this.eslClient.getPackage(packageId);
    }
}
