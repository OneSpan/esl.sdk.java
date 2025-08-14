package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Sender;
import com.silanis.esl.sdk.SenderStatus;
import com.silanis.esl.sdk.SenderType;
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
 * with three members (two signers and one account owner) in a single HTTP call.
 * <p>
 * The example creates a package with:
 * Multiple individual signers with custom properties
 * An ad-hoc group containing signers and group members
 * Document signatures positioned at specific coordinates
 * Package visibility and attributes configuration
 */
public class AdhocGroupBasicPackageCreationExample extends SDKSample {

    public static final String ADHOC_GROUP_ID = "adhoc-group-id";
    public static final String AGENT_1 = "agent1";
    public static final String AGENT_2 = "agent2";
    public static final String AGENT_3 = "agent3";

    public static final String ADHOC_GROUP_NAME = "Adhoc Group X-Files";

    public static void main(String... args) {
        new AdhocGroupBasicPackageCreationExample().run();
    }

    public void execute() {
        final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK in one http call")
                .withVisibility(Visibility.ACCOUNT)
                .withAttributes(DocumentPackageAttributesBuilder.newDocumentPackageAttributes() // do not display transaction owner in the package on Sender UI dashboard
                        .withAttribute("senderVisible", Boolean.FALSE)
                        .build())
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(AGENT_1)
                        .withFirstName("Fox")
                        .withLastName("Mulder")
                        .withCompany("FBI")
                        .withSmsSentTo("5144301408")
                )
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(AGENT_2)
                        .withFirstName("Dana")
                        .withLastName("Scully")
                        .withCompany("FBI")
                )
                .withSigner(newSignerWithEmail(email3)
                        .withCustomId(AGENT_3)
                        .withFirstName("John")
                        .withLastName("Doggett")
                        .withCompany("FBI")
                        .signingOrder(1)
                )
                .withSigner(newAdHocGroupSigner()
                        .withCustomId(ADHOC_GROUP_ID)
                        .withGroup(GroupBuilder.newGroup(ADHOC_GROUP_NAME)
                                .withMember(GroupMemberBuilder.newAdHocGroupMember()
                                        .withUserId(AGENT_1)
                                        .as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                                        .build())
                                .withMember(GroupMemberBuilder.newAdHocGroupMember()
                                        .withUserId(AGENT_2).as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                                        .build())
                                .build())
                        .signingOrder(3)
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
