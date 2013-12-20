package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.builder.GroupMemberBuilder;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 1:33 PM
 */
public class GroupMemberConverter {
    private com.silanis.esl.api.model.GroupMember apiGroupMember;
    private com.silanis.esl.sdk.GroupMember sdkGroupMember;

    /**
     * Construct with API groupMember object involved in conversion.
     *
     * @param apiGroupMember
     */
    public GroupMemberConverter(com.silanis.esl.api.model.GroupMember apiGroupMember) {
        this.apiGroupMember = apiGroupMember;
    }

    /**
     * Construct with SDK groupMember object involved in conversion.
     *
     * @param sdkGroupMember
     */
    public GroupMemberConverter(com.silanis.esl.sdk.GroupMember sdkGroupMember) {
        this.sdkGroupMember = sdkGroupMember;
    }

    /**
     * Convert from SDK GroupMember to API GroupMember.
     *
     * @return API GroupMember.
     */
    public com.silanis.esl.api.model.GroupMember toAPIGroupMember() {
        if (sdkGroupMember == null) {
            return apiGroupMember;
        }

        com.silanis.esl.api.model.GroupMember result = new com.silanis.esl.api.model.GroupMember();
        result.setEmail(sdkGroupMember.getEmail());
        result.setFirstName(sdkGroupMember.getFirstName());
        result.setLastName(sdkGroupMember.getLastName());
        result.setMemberType( new GroupMemberTypeConverter(sdkGroupMember.getGroupMemberType()).toAPIGroupMemberType());
        return result;

    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK GroupMember.
     */
    public com.silanis.esl.sdk.GroupMember toSDKGroupMember() {

        if (apiGroupMember == null) {
            return sdkGroupMember;
        }

        return GroupMemberBuilder.newGroupMember( apiGroupMember.getEmail() )
                .as( new GroupMemberTypeConverter(apiGroupMember.getMemberType()).toSDKGroupMemberType() )
                .withFirstName( apiGroupMember.getFirstName() )
                .withLastName(apiGroupMember.getLastName())
                .build();
    }
}
