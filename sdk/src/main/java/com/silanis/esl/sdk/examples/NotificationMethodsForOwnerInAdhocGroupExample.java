package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newAdHocGroupSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import java.util.Objects;

import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.GroupMember;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.NotificationMethod;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;
import com.silanis.esl.sdk.internal.converter.GroupMemberConverter;

public class NotificationMethodsForOwnerInAdhocGroupExample extends SDKSample {
    public static final String ADHOC_GROUP_ID = "adhoc-group-id";
    public static final String ADHOC_GROUP_NAME = "Example Adhoc Group Name";
    public static final String SIGNER_1 = "signer1";
    public static final String SIGNER_2 = "signer2";
    public static final String OWNER_SIGNER = "Owner";
    public static final String SIGNER_PHONE = "+12042345678";
    public static final String OWNER_PHONE = "+18765432100";

    private DocumentPackage updatedPackage;

    protected DocumentPackage getUpdatedPackage() {
        return updatedPackage;
    }

    public static void main(String... args) {
        new NotificationMethodsForOwnerInAdhocGroupExample().run();
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
        Package aPackage = eslClient.getPackageService().getApiPackage(packageId.getId());

        Role adhocSignerRole = addOwnerSignerToAdhocGroup(aPackage);
        eslClient.getPackageService().updateRole(packageId, adhocSignerRole);

        // update notification methods for owner signer in ad hoc group
        retrievedPackage = eslClient.getPackage(packageId);
        Signer ownerSigner = retrievedPackage.getSigner(senderEmail);
        ownerSigner.setNotificationPhoneNumber(OWNER_PHONE);
        ownerSigner.setNotificationPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS);

        eslClient.getPackageService().updateSigner(packageId, ownerSigner);
        updatedPackage = eslClient.getPackage(packageId);
    }

    private static Role addOwnerSignerToAdhocGroup(Package aPackage) {
        Role adhocSignerRole = aPackage.getRoles().stream()
                .filter(role -> AdHocGroupUtils.isAdHocGroupEmail(role.getSigners().get(0).getEmail()))
                .findFirst().get();

        String OWNER_ID = aPackage.getRoles().stream()
                .filter(role -> Objects.equals(role.getName(), OWNER_SIGNER))
                .findFirst().get().getId();

        GroupMember sdkGroupMember = GroupMemberBuilder.newAdHocGroupMember()
                .withUserId(OWNER_ID)
                .as(GroupMemberType.AD_HOC_GROUP_MEMBER)
                .build();

        adhocSignerRole.getSigners().get(0).getGroup().getMembers()
                .add(new GroupMemberConverter(sdkGroupMember).toAPIGroupMember());
        return adhocSignerRole;
    }

}
