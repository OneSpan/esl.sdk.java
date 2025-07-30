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

public final class AdhocGroupExampleWithLinkExternalSignerTest {
  final AdhocGroupExampleWithLinkExternalSigner example = new AdhocGroupExampleWithLinkExternalSigner();

  @Test
  public void verifyResult() {
    this.example.run();

    assertThat("Additional Adhoc Group Request has to one Adhoc Group: ",
        (int) this.example.getCreateAdhocGroupWithMembersRequest().stream()
            .filter(AdHocGroupUtils::isAdHocGroup).count(),
        is(1));
    assertThat("Additional Adhoc Group Request has to have one Adhoc Group which has one linked member: ",
        this.example.getCreateAdhocGroupWithMembersRequest().stream()
            .filter(AdHocGroupUtils::isAdHocGroup).findFirst().get().getSigners().get(0).getGroup()
            .getMembers().size(),
        is(1));
    assertThat(
        "Additional Adhoc Group has no linked member with email email2: ",
        (int) this.example.getCreateAdhocGroupWithMembersRequest().stream()
            .filter(AdHocGroupUtils::isAdHocGroup).findFirst().get().getSigners().get(0).getGroup()
            .getMembers().stream()
            .filter(member -> this.example.email2.equalsIgnoreCase(member.getEmail()))
            .count(),
        is(0));

    final DocumentPackage documentPackage = this.example.getRetrievedPackage();

    assertThat("Package name should not have changed", documentPackage.getName(),
        not(LAYOUT_PACKAGE_NAME));
    assertThat("Package description should not have changed", documentPackage.getDescription(),
        not(LAYOUT_PACKAGE_DESCRIPTION));
    assertThat("Package should have only 4 signers", documentPackage.getSigners().size(), is(4));
    /* Note that default consent will be added automatically. */
    assertThat("Package should have 2 documents", documentPackage.getDocuments().size(), is(2));

    final Document document = this.example.retrievedPackage.getDocument("First Document");

    assertThat("Document should have 3 signature", document.getSignatures().size(), is(3));

    assertThat(
        "Additional Adhoc Group has linked member with email email2 because it was added after: ",
        (int) this.example.getEslClient().getPackageService().getRoles(this.example.packageId).stream()
            .filter(AdHocGroupUtils::isAdHocGroup).findFirst().get().getSigners().get(0).getGroup()
            .getMembers().stream()
            .filter(member -> this.example.email2.equalsIgnoreCase(member.getEmail()))
            .count(),
        is(1));


  }
}