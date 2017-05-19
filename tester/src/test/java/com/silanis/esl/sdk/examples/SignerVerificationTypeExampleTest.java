package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.VerificationType;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationTypeExampleTest {

    @Test
    public void verifyResult() {
        SignerVerificationTypesExample example = new SignerVerificationTypesExample();
        example.run();

        List<VerificationType> verificationTypes = example.verificationTypes;
        List<String> verificationTypeIds = new ArrayList<String>();

        for(VerificationType verificationType : verificationTypes) {
            verificationTypeIds.add(verificationType.getId());
        }

        assertThat("There is no Verification Type for this account.", verificationTypes.size(), greaterThanOrEqualTo(1));
        assertThat(verificationTypeIds, hasItem("DIGIPASS"));
    }
}
