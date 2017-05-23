package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SignerVerification;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by aafrasiabian on 23/05/17.
 */
public class SignerVerificationBuilder {

    private String typeId;
    private String payload;

    private SignerVerificationBuilder(String typeId) {
        this.typeId = typeId;
    }

    public static SignerVerificationBuilder newSignerVerification(String typeId) {
        if(StringUtils.isBlank(typeId)) {
            throw new EslException("No TypeId set for this signer verification!");
        }

        SignerVerificationBuilder signerVerificationBuilder = new SignerVerificationBuilder(typeId);
        return signerVerificationBuilder;
    }

    public SignerVerificationBuilder withPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public SignerVerification build() {
        SignerVerification result = new SignerVerification();
        result.setTypeId(typeId);
        result.setPayload(payload);

        return result;
    }
}
