package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.service.AdhocGroupService.addAdhocGroupLinkedMembersToAdhocGroup;
import static com.silanis.esl.sdk.service.AdhocGroupService.buildAdhocGroup;
import static com.silanis.esl.sdk.service.AdhocGroupService.buildAdhocGroupMember;
import static com.silanis.esl.sdk.service.AdhocGroupService.buildAdhocGroupMemberToLinkExistingRole;

import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class AdhocGroupExampleWithLinkExternalSigner extends SDKSample {

  private static final Logger LOG = Logger.getLogger(AdhocGroupExampleWithLinkExternalSigner.class.getName());

  private List<Role> createAdhocGroupWithMembersRequest;
  private Role transactionOwner;

  public static void main(String... args) {
    new AdhocGroupExampleWithLinkExternalSigner().run();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public void execute() {
    final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
        .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK with Linked Group Member")
        .withSigner(newSignerWithEmail(email1)
            .withFirstName("Fox")
            .withLastName("Mulder"))
        .withSigner(newSignerWithEmail(email2)
            .withFirstName("test 80")
            .withLastName("test 80 ln"))
        .build();

    // 1. Create a package
    packageId = this.eslClient.createPackage(superDuperPackage);

    // Create an adhoc group with one initial member which is linked to existing external user test 80 with email2.
    final Role adhocGroup = buildAdhocGroup("Adhoc Group Name 99 With Linked Signer");

    final List<Role> initialRoles = this.eslClient.getPackageService()
        .getRoles(packageId);

    initialRoles.stream()
        .filter(role -> email2.equalsIgnoreCase(role.getSigners().get(0).getEmail()))
        .findFirst().ifPresent(role -> {
          final String roleToLink = role.getId();
          final GroupMember groupMemberToLinkExistingRole = buildAdhocGroupMemberToLinkExistingRole(
              roleToLink);
          final Role updatedAdhocGroupWithLinkedMember = addAdhocGroupLinkedMembersToAdhocGroup(
              Collections.singletonList(groupMemberToLinkExistingRole), adhocGroup);
          createAdhocGroupWithMembersRequest = Collections.singletonList(
              updatedAdhocGroupWithLinkedMember);
        });

    final List<Role> createdAdhocGroupWithMembers = this.eslClient.getAdhocGroupService()
        .createAdhocGroup(packageId.getId(), createAdhocGroupWithMembersRequest);
    if (LOG.isLoggable(Level.INFO)) {
      LOG.info(
          "createdAdhocGroupWithMembers: " + AdHocGroupUtils.toString(
              createdAdhocGroupWithMembers));
    }

    // Add another adhoc member linked to existing external user Fox Mulder email1 to the adhoc group.
    initialRoles.stream()
        .filter(role -> email1.equalsIgnoreCase(role.getSigners().get(0).getEmail()))
        .findFirst().ifPresent(role -> {
          final String roleToLink = role.getId();
          final GroupMember groupMemberToLinkExistingRole = buildAdhocGroupMemberToLinkExistingRole(
              roleToLink);
          this.eslClient.getAdhocGroupService().addAdhocGroupLinkedMembers(packageId.getId(), adhocGroup.getId(),
                  Collections.singletonList(groupMemberToLinkExistingRole));
        });

    transactionOwner = this.eslClient.getAdhocGroupService()
        .getTransactionOwner(packageId.getId());
    this.eslClient.getAdhocGroupService()
        .addAdhocGroupMembers(packageId.getId(), transactionOwner, adhocGroup.getId(),
            Collections.singletonList(buildAdhocGroupMember("test1", "test1", transactionOwner.getSigners().get(0).getEmail())));

    final List<Role> roles = this.eslClient.getPackageService()
        .getRoles(packageId);

    if (LOG.isLoggable(Level.INFO)) {
      LOG.info("list of roles: " + AdHocGroupUtils.toString(roles));
    }

    // 2. Construct a document with two signatures, one for the initial signer and one for the adhoc group.
    final String email = roles.stream().filter(AdHocGroupUtils::isAdHocGroup).findFirst().map(
        adhocGroupRole -> adhocGroupRole.getSigners().get(0).getEmail()
        ).orElse(null);
   final Document document = newDocumentWithName("First Document")
        .fromStream(documentInputStream1, DocumentType.PDF)
        .withId("documentId")
        .withSignature(signatureFor(email1)
            .atPosition(100, 100)
            .onPage(0))
       .withSignature(signatureFor(email2)
           .atPosition(100, 300)
           .onPage(0))
        .withSignature(signatureFor(email)
            .atPosition(100, 500)
            .onPage(0))
        .build();

    // 3. Attach the document to the created package by uploading the document.
    this.eslClient.uploadDocument(document.getFileName(), document.getContent(), document,
        packageId);

    retrievedPackage = this.eslClient.getPackage(packageId);
  }

  public List<Role> getCreateAdhocGroupWithMembersRequest() {
    return createAdhocGroupWithMembersRequest;
  }

  public Role getTransactionOwner() {
    return transactionOwner;
  }

}
