package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.PackageStatus;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public final class AdhocGroupExampleTest {
  final AdhocGroupExample example = new AdhocGroupExample();

  @Test
  public void verifyResult() {
    this.example.run();

    final DocumentPackage documentPackage = this.example.getRetrievedPackage();

    assertThat("Package is prepared and sent", documentPackage.getStatus(), is(PackageStatus.SENT));

  }
}
