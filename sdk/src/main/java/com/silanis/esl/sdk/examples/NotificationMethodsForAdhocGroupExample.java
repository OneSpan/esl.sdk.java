package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newAdHocGroupSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.NotificationMethod;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;

public class NotificationMethodsForAdhocGroupExample extends SDKSample {
    public static final String ADHOC_GROUP_ID = "adhoc-group-id";
    public static final String ADHOC_GROUP_NAME = "Example Adhoc Group Name";
    public static final String SIGNER_1 = "signer1";
    public static final String SIGNER_2 = "signer2";
    public static final String SIGNER_PHONE = "+12042345678";

    public static void main(String... args) {
        new NotificationMethodsForAdhocGroupExample().run();
    }

    public void execute() {
        DocumentPackage package1 = newPackageNamed(getPackageName())
                .describedAs("This example is created to demonstrate Notification Methods with Adhoc Group Signers")
                .withSigner(newSignerWithEmail(email1)
                        .withCustomId(SIGNER_1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL))
                )
                .withSigner(newSignerWithEmail(email2)
                        .withCustomId(SIGNER_2)
                        .withFirstName("Olivia")
                        .withLastName("Brown")
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                                .withPhoneNumber(SIGNER_PHONE))
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
                .build();

        packageId = eslClient.createPackage(package1);
        retrievedPackage = eslClient.getPackage(packageId);
    }
}
