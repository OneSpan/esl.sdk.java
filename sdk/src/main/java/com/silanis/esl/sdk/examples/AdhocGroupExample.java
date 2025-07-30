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
import com.silanis.esl.sdk.DocumentPackageAttributes;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Visibility;
import com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class AdhocGroupExample extends SDKSample {

  private static final Logger LOG = Logger.getLogger(AdhocGroupExample.class.getName());

  private List<Role> createAdhocGroupWithMembersRequest;

  public static void main(String... args) {
    new AdhocGroupExample().run();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  public void execute() {
    final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
        .describedAs("This is a package with Adhoc Group created using OneSpan Sign SDK")
        .withVisibility(Visibility.ACCOUNT)
        .withAttributes(DocumentPackageAttributesBuilder.newDocumentPackageAttributes() // do not display transaction owner in the package on Sender UI dashboard
            .withAttribute("senderVisible", Boolean.FALSE)
            .build())
        .withSigner(newSignerWithEmail(email1)
            .withFirstName("Fox")
            .withLastName("Mulder"))
        .build();

    // 1. Create a package
    packageId = this.eslClient.createPackage(superDuperPackage);

    // Create an adhoc group with two initial members.
    final Role adhocGroup = buildAdhocGroup("Adhoc Group Name 99");

    final Signer adhocGroupMemberInitial1 = buildAdhocGroupMember("test 80", "test 80 ln",
        "success+sdk+auto2@simulator.amazonses.com");
    final Signer adhocGroupMemberInitial2 = buildAdhocGroupMember("test 90", "test 90 ln",
        "success+sdk+auto3@simulator.amazonses.com");
    final Role transactionOwner = this.eslClient.getAdhocGroupService()
        .getTransactionOwner(packageId.getId());
    createAdhocGroupWithMembersRequest = addAdhocGroupMembersToAdhocGroup(transactionOwner,
        Stream.of(adhocGroupMemberInitial1, adhocGroupMemberInitial2).collect(Collectors.toList()),
        adhocGroup);

    final List<Role> createdAdhocGroupWithMembers = this.eslClient.getAdhocGroupService()
        .createAdhocGroup(packageId.getId(), createAdhocGroupWithMembersRequest);
    if (LOG.isLoggable(Level.INFO)) {
      LOG.info(
          "createdAdhocGroupWithMembers: " + AdHocGroupUtils.toString(
              createdAdhocGroupWithMembers));
    }

    // Add an adhoc  member to the adhoc group.
    final Signer adhocGroupMember = buildAdhocGroupMember("test 100", "test 100 ln",
        "success+sdk+auto4@simulator.amazonses.com");
    this.eslClient.getAdhocGroupService()
        .addAdhocGroupMembers(this.packageId.getId(), transactionOwner, adhocGroup.getId(),
            Collections.singletonList(adhocGroupMember));

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
        .withSignature(signatureFor(email)
            .atPosition(100, 300)
            .onPage(0))
        .build();

    // 3. Attach the document to the created package by uploading the document.
    this.eslClient.uploadDocument(document.getFileName(), document.getContent(), document,
        packageId);

    this.eslClient.sendPackage(packageId);

    retrievedPackage = this.eslClient.getPackage(packageId);
  }

  public List<Role> getCreateAdhocGroupWithMembersRequest() {
    return createAdhocGroupWithMembersRequest;
  }

}
