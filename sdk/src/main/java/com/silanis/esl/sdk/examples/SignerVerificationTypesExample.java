package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.VerificationType;
import java.util.List;

/**
 * Created by schoi on 19/04/17.
 */
public class SignerVerificationTypesExample extends SDKSample {

    public List<VerificationType> verificationTypes;

    public static void main( String... args ) {
        new SignerVerificationTypesExample().run();
    }

    public void execute() {

        List<VerificationType> verificationTypes = eslClient.getAccountService().getVerificationTypes();

        this.verificationTypes = verificationTypes;
    }
}
