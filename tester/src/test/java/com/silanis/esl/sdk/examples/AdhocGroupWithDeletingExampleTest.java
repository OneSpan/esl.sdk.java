package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_PACKAGE_DESCRIPTION;
import static com.silanis.esl.sdk.examples.DocumentLayoutExample.LAYOUT_PACKAGE_NAME;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

public final class AdhocGroupWithDeletingExampleTest {
  final AdhocGroupWithDeletingExample example = new AdhocGroupWithDeletingExample();

  @Test
  public void verifyResult() {
    this.example.run();

    assertThat("Additional Adhoc Group Request has to one Adhoc Group: ",
        (int) this.example.getCreatedAdhocGroupWithMembers().stream()
            .filter(AdHocGroupUtils::isAdhocGroup).count(),
        is(1));
    assertThat("Additional Adhoc Group Request has to have one Adhoc Group which has two members: ",
        (int) this.example.getCreatedAdhocGroupWithMembers().stream()
            .filter(AdHocGroupUtils::isAdhocGroup).findFirst().get().getSigners().get(0).getGroup().getMembers().size(),
        is(2));
    assertThat("Additional Adhoc Group has member with email test90@test.com: ",
        (int) this.example.getCreatedAdhocGroupWithMembers().stream()
            .filter(AdHocGroupUtils::isAdhocGroup).findFirst().get().getSigners().get(0).getGroup().getMembers().stream().filter(member->"test90@test.com".equals(member.getEmail())).count(),
        is(1));
    assertThat("Additional Adhoc Group has no member with email test90@test.com after it was removed: ",
        (int) this.example.getRolesAfterRemovingOneAdhocGroupMember().stream()
            .filter(AdHocGroupUtils::isAdhocGroup).findFirst().get().getSigners().get(0).getGroup().getMembers().stream().filter(member->"test90@test.com".equals(member.getEmail())).count(),
        is(0));

    final DocumentPackage documentPackage = this.example.getRetrievedPackage();

    assertThat("Package name should not have changed", documentPackage.getName(), not(LAYOUT_PACKAGE_NAME));
    assertThat("Package description should not have changed", documentPackage.getDescription(), not(LAYOUT_PACKAGE_DESCRIPTION));
    assertThat("Package should have only 2 signers", documentPackage.getSigners().size(), is(5));
    /* Note that default consent will be added automatically. */
    assertThat("Package should have 2 documents", documentPackage.getDocuments().size(), is(2));

    final Document document = this.example.retrievedPackage.getDocument("First Document");

    assertThat("Document should have 2 signature", document.getSignatures().size(), is(2));

  }
}
