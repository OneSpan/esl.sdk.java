package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.SignatureLayout;
import com.silanis.esl.sdk.builder.SignatureLayoutBuilder;
import com.silanis.esl.sdk.builder.SignatureLogoBuilder;

public class AccountSignatureLayoutExample extends SDKSample {

    public SignatureLayout defaultSignatureLayout, patchedSignatureLayout;
    public String SIGNATURE_LOGO_IMAGE_BASE64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAMAAABrrFhUAAAAA1BMVEV6y//7EDFUAAABM0lEQVR4Xu3QgQAAAACAoP2pFymECgMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYMCAAQMGDBgwYGABDwABbQVHVQAAAABJRU5ErkJggg==";
    
    public static void main(String... args) {
        new AccountSignatureLayoutExample().run();
    }

    @Override
    protected void execute() {

        //Get account signature layout
        defaultSignatureLayout = eslClient.getAccountConfigService().getAccountSignatureLayout();

        SignatureLayout signatureLayout = SignatureLayoutBuilder.newSignatureLayoutBuilder()
                .withSignatureLogo(SignatureLogoBuilder.newSignatureLogoBuilder().withImage(SIGNATURE_LOGO_IMAGE_BASE64).build())
                .build();

        //Save account signature layout
        eslClient.getAccountConfigService().saveAccountSignatureLayout(signatureLayout);
        patchedSignatureLayout = eslClient.getAccountConfigService().getAccountSignatureLayout();

    }
}