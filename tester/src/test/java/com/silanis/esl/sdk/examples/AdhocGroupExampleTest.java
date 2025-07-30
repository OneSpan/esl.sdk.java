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

public final class AdhocGroupExampleTest {
  final AdhocGroupExample example = new AdhocGroupExample();

  @Test
  public void verifyResult() {
    this.example.run();

    assertThat("Additional Adhoc Group Request has to one Adhoc Group: ",
        (int) this.example.getCreateAdhocGroupWithMembersRequest().stream()
            .filter(AdHocGroupUtils::isAdHocGroup).count(),
        is(1));
    assertThat("Additional Adhoc Group Request has to have one Adhoc Group which has two members: ",
        (int) this.example.getCreateAdhocGroupWithMembersRequest().stream()
            .filter(AdHocGroupUtils::isAdHocGroup).findFirst().get().getSigners().get(0).getGroup().getMembers().size(),
        is(2));

    final DocumentPackage documentPackage = this.example.getRetrievedPackage();

    assertThat("Package name should not have changed", documentPackage.getName(), not(LAYOUT_PACKAGE_NAME));
    assertThat("Package description should not have changed", documentPackage.getDescription(), not(LAYOUT_PACKAGE_DESCRIPTION));
    assertThat("Package should have only 2 signers", documentPackage.getSigners().size(), is(6));
    /* Note that default consent will be added automatically. */
    assertThat("Package should have 2 documents", documentPackage.getDocuments().size(), is(2));

    final Document document = this.example.retrievedPackage.getDocument("First Document");

    assertThat("Document should have 2 signature", document.getSignatures().size(), is(2));

  }
}
