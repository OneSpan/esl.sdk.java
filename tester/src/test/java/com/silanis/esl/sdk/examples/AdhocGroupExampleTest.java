package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.GroupMember;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_PACKAGE_DESCRIPTION;
import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_PACKAGE_NAME;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public final class AdhocGroupExampleTest {
  final AdhocGroupExample example = new AdhocGroupExample();

  @Test
  public void verifyResult() {
    this.example.run();

    final List<Signer> signers = this.example.getEslClient().getPackage(this.example.packageId).getSigners();
    final Signer adhocGroupSigner = signers.stream()
            .filter(signer -> AdHocGroupUtils.isAdHocGroupEmail(signer.getEmail())).findFirst().get();
    final Set<String> adhocGroupMembersIds = adhocGroupSigner.getGroup().getMembers().stream()
            .map(GroupMember::getUserId).collect(Collectors.toSet());

    assertThat("Transaction has two signers which is not a member of the Adhoc Group: ",
            (int) signers.stream()
                    .filter(signer -> !(AdHocGroupUtils.isAdHocGroupEmail(signer.getEmail())
                            ||adhocGroupMembersIds.contains(signer.getId()))).count(),
            is(2));

    assertThat("Transaction has one Adhoc Group: ",
            (int) signers.stream()
                    .filter(signer -> AdHocGroupUtils.isAdHocGroupEmail(signer.getEmail())).count(),
            is(1));

    assertThat("The Adhoc Group which has two members: ",
            (int) adhocGroupSigner.getGroup().getMembers().size(),
            is(2));

    final DocumentPackage documentPackage = this.example.getRetrievedPackage();

    assertThat("Package name should not have changed", documentPackage.getName(), not(LAYOUT_PACKAGE_NAME));
    assertThat("Package description should not have changed", documentPackage.getDescription(), not(LAYOUT_PACKAGE_DESCRIPTION));
    assertThat("Package should have only 5 signers", documentPackage.getSigners().size(), is(5));
    /* Note that default consent will be added automatically. */
    assertThat("Package should have 2 documents", documentPackage.getDocuments().size(), is(2));

    final Document document = this.example.retrievedPackage.getDocument("First Document");

    assertThat("Document should have two signatures", document.getSignatures().size(), is(2));

  }
}
