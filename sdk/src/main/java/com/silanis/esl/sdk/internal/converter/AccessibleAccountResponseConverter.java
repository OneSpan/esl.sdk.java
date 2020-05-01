package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Optional;
import com.silanis.esl.api.model.AccessibleAccountResponse;

public class AccessibleAccountResponseConverter {

    private Optional<AccessibleAccountResponse> apiAccessibleAccountResponseOptional;
    private Optional<com.silanis.esl.sdk.AccessibleAccountResponse> sdkAccessibleAccountResponseOptional;

    public AccessibleAccountResponseConverter(AccessibleAccountResponse apiAccessibleAccountResponse ) {
        apiAccessibleAccountResponseOptional = Optional.of( apiAccessibleAccountResponse );
        sdkAccessibleAccountResponseOptional = Optional.absent();
    }

    public AccessibleAccountResponseConverter(com.silanis.esl.sdk.AccessibleAccountResponse sdkAccessibleAccountResponse ) {
        apiAccessibleAccountResponseOptional = Optional.absent();
        sdkAccessibleAccountResponseOptional = Optional.of( sdkAccessibleAccountResponse );
    }

    public AccessibleAccountResponse toAPIAccessibleAccountResponse() {
        if ( sdkAccessibleAccountResponseOptional.isPresent() ) {
            AccessibleAccountResponse result = new AccessibleAccountResponse();
            com.silanis.esl.sdk.AccessibleAccountResponse sdkAccessibleAccountResponse = sdkAccessibleAccountResponseOptional.get();
            result.setAccountName(sdkAccessibleAccountResponse.getAccountName());
            result.setAccountUid(sdkAccessibleAccountResponse.getAccountUid());
            return result;
        } else {
            return apiAccessibleAccountResponseOptional.get();
        }
    }

    public com.silanis.esl.sdk.AccessibleAccountResponse toSDKAccessibleAccountResponse() {
        if ( apiAccessibleAccountResponseOptional.isPresent() ) {
            com.silanis.esl.sdk.AccessibleAccountResponse result = new com.silanis.esl.sdk.AccessibleAccountResponse();
            AccessibleAccountResponse apiAccessibleAccountResponse = apiAccessibleAccountResponseOptional.get();
            result.setAccountName(apiAccessibleAccountResponse.getAccountName());
            result.setAccountUid(apiAccessibleAccountResponse.getAccountUid());
            return result;
        } else {
            return sdkAccessibleAccountResponseOptional.get();
        }
    }
}
