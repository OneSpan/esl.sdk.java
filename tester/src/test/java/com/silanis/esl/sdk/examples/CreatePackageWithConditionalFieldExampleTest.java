package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class CreatePackageWithConditionalFieldExampleTest {

    @Test
    public void verifyResult() {
        CreatePackageWithConditionalFieldExample example = new CreatePackageWithConditionalFieldExample();
        example.run();

        //check if conditions was succesfully added to package
        assertThat("Package doesn't have conditional fields.", example.getRetrievedPackage().getConditions(), hasSize(1));
    }
}
