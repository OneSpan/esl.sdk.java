package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Verification;
import com.silanis.esl.sdk.SignerVerification;

/**
 * Created by aafrasiabian on 23/05/17.
 */
public class SignerVerificationConverter {

    public Verification toAPISignerVerification(SignerVerification sdkSgnerVerification) {
        if(sdkSgnerVerification == null) {
            return null;
        }

        Verification apiSignerVerification = new Verification();
        apiSignerVerification.setTypeId(sdkSgnerVerification.getTypeId());
        apiSignerVerification.setPayload(sdkSgnerVerification.getPayload());

        return apiSignerVerification;
    }

    public SignerVerification toSDKSignerVerification(Verification apiSignerVerification) {
        if(apiSignerVerification == null) {
            return null;
        }

        SignerVerification sdkSgnerVerification = new SignerVerification();
        sdkSgnerVerification.setTypeId(apiSignerVerification.getTypeId());
        sdkSgnerVerification.setPayload(apiSignerVerification.getPayload());

        return sdkSgnerVerification;
    }
}
