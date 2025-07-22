package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.service.AdhocGroupService.addAdhocGroupMembersToAdhocGroup;
import static com.silanis.esl.sdk.service.AdhocGroupService.buildAdhocGroup;
import static com.silanis.esl.sdk.service.AdhocGroupService.buildAdhocGroupMember;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AdhocGroupExample extends SDKSample {

  private static final Logger LOG = Logger.getLogger(AdhocGroupExample.class.getName());

  public static void main(String... args) {
    new AdhocGroupExample().run();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public void execute() {
    final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
        .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK")
        .withSigner(newSignerWithEmail(email1)
            .withFirstName("Fox")
            .withLastName("Mulder"))
        .build();

    // 1. Create a package
    packageId = eslClient.createPackage(superDuperPackage);

    retrievedPackage = eslClient.getPackage(packageId);

    final Role adhocGroup = buildAdhocGroup("Adhoc Group Name 99");

    final Signer adhocGroupMemberInitial1 = buildAdhocGroupMember("test 80", "test 80 ln",
        "test80@test.com");
    final Signer adhocGroupMemberInitial2 = buildAdhocGroupMember("test 90", "test 90 ln",
        "test90@test.com");
    final List<Role> createAdhocGroupWihMembersRequest = addAdhocGroupMembersToAdhocGroup(
        Stream.of(adhocGroupMemberInitial1, adhocGroupMemberInitial2).collect(Collectors.toList()),
        adhocGroup);

    final List<Role> createdAdhocGroupWithMembers = this.eslClient.getAdhocGroupService()
        .createAdhocGroup(packageId.getId(), createAdhocGroupWihMembersRequest);
    LOG.info("createdAdhocGroupWithMembers: " + createdAdhocGroupWithMembers);

    final Signer adhocGroupMember = buildAdhocGroupMember("test 100", "test 100 ln",
        "test100@test.com");
    this.eslClient.getAdhocGroupService()
        .addAdhocGroupMember(this.packageId.getId(), adhocGroup.getId(),
            Collections.singletonList(adhocGroupMember));

    final List<Role> roles = this.eslClient.getPackageService()
        .getRoles(packageId);

    LOG.info("list of roles: " + roles);

    // 2. Construct a document with two signatures, one for the initial signer and one for the adhoc group.
    final String email = roles.stream().filter(AdHocGroupUtils::isAdhocGroup).findFirst().get()
        .getSigners().get(0).getEmail();
    final Document document = newDocumentWithName("First Document")
        .fromStream(documentInputStream1, DocumentType.PDF)
        .withId("documentId")
        .withSignature(signatureFor(email1)
            .atPosition(100, 100)
            .onPage(0))
        .withSignature(signatureFor(email)
            .atPosition(100, 300)
            .onPage(0))
        .build();

    // 3. Attach the document to the created package by uploading the document.
    eslClient.uploadDocument(document.getFileName(), document.getContent(), document, packageId);
  }

}
