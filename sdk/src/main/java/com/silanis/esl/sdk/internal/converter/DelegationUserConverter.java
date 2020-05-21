package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.DelegationUserBuilder;

/**
 * Created by schoi on 3/23/15.
 */
public class DelegationUserConverter {

    private com.silanis.esl.api.model.DelegationUser apiDelegationUser;
    private com.silanis.esl.sdk.DelegationUser sdkDelegationUser;

    /**
     * Construct with API delegationUser object involved in conversion.
     *
     * @param apiDelegationUser
     */
    public DelegationUserConverter(com.silanis.esl.api.model.DelegationUser apiDelegationUser) {
        this.apiDelegationUser = apiDelegationUser;
    }

    /**
     * Construct with SDK delegationUser object involved in conversion.
     *
     * @param sdkDelegationUser
     */
    public DelegationUserConverter(com.silanis.esl.sdk.DelegationUser sdkDelegationUser) {
        this.sdkDelegationUser = sdkDelegationUser;
    }

    /**
     * Convert from SDK DelegationUser to API DelegationUser.
     *
     * @return API DelegationUser.
     */
    public com.silanis.esl.api.model.DelegationUser toAPIDelegationUser() {
        if (sdkDelegationUser == null) {
            return apiDelegationUser;
        }

        com.silanis.esl.api.model.DelegationUser result = new com.silanis.esl.api.model.DelegationUser();
        result.setEmail(sdkDelegationUser.getEmail());
        result.setFirstName(sdkDelegationUser.getFirstName());
        result.setId(sdkDelegationUser.getId());
        result.setLastName(sdkDelegationUser.getLastName());
        result.setName(sdkDelegationUser.getName());
        result.setExpiryDate(sdkDelegationUser.getExpiryDate());
        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK DelegationUser.
     */
    public com.silanis.esl.sdk.DelegationUser toSDKDelegationUser() {

        if (apiDelegationUser == null) {
            return sdkDelegationUser;
        }

        return DelegationUserBuilder.newDelegationUser(apiDelegationUser.getEmail())
            .withFirstName( apiDelegationUser.getFirstName() )
            .withId( apiDelegationUser.getId() )
            .withLastName(apiDelegationUser.getLastName() )
            .withName(apiDelegationUser.getName() )
            .withExpiryDate(apiDelegationUser.getExpiryDate() )
            .build();
    }
}
