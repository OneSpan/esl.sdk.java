package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Visibility;
import com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newAdHocGroupSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example class demonstrating how to create a document package with an ad-hoc group signer.
 * This class shows how to set up a package containing multiple signers including an ad-hoc group
 * with two members in a single HTTP call.
 * <p>
 * The example creates a package with:
 * Multiple individual signers with custom properties
 * An ad-hoc group containing signers and group members
 * Document signatures positioned at specific coordinates
 * Package visibility and attributes configuration
 */
public class AdhocGroupExample extends SDKSample {

    public static final String ADHOC_GROUP_ID = "adhoc-group-id";
    public static final String SIGNER_1 = "signer1";
    public static final String SIGNER_2 = "signer2";
    public static final String SIGNER_3 = "signer3";

    public static final String ADHOC_GROUP_NAME = "Adhoc Group";

    public static void main(String... args) {
        new AdhocGroupExample().run();
    }

    public void execute() {
        final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK in one http call")
                .withVisibility(Visibility.ACCOUNT)
                .withAttributes(DocumentPackageAttributesBuilder.newDocumentPackageAttributes() // do not display transaction owner in the package on Sender UI dashboard
                        .withAttribute("senderVisible", Boolean.FALSE)
                        .build())
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER_1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withCompany("Acme Inc.")
                        .withSmsSentTo("5555555555")
                )
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(SIGNER_2)
                        .withFirstName("Olivia")
                        .withLastName("Brown")
                        .withCompany("Acme Inc.")
                )
                .withSigner(newSignerWithEmail(email3)
                        .withCustomId(SIGNER_3)
                        .withFirstName("James")
                        .withLastName("Johnson")
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
                                        .withUserId(SIGNER_2).as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                                        .build())
                                .build())
                )
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withId("documentId")
                        .withSignature(signatureFor(email3)
                                .atPosition(100, 100)
                                .onPage(0))
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
