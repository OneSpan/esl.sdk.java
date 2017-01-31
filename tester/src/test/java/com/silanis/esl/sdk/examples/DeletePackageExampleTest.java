package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.examples.notAllowed.DeletePackageExample;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

/**
 * User: jessica
 * Date: 08/01/14
 * Time: 3:54 PM
 * <p/>
 * Test for DeletePackageExample.
 */
public class DeletePackageExampleTest {

    @Test (expected = com.silanis.esl.sdk.EslException.class)
    public void verifyResult() {
        DeletePackageExample example = new DeletePackageExample();
        example.run();
        assertThat("Package should have been deleted.", example.getRetrievedPackage(), nullValue());
    }
}
