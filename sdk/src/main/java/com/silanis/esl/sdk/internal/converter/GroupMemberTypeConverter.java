package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.MemberType;
import com.silanis.esl.sdk.GroupMemberType;
import com.silanis.esl.sdk.builder.BuilderException;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 1:50 PM
 * <p/>
 * Converter between SDK  and API group member type.
 */
public class GroupMemberTypeConverter {

    private com.silanis.esl.sdk.GroupMemberType sdkMemberType = null;
    private com.silanis.esl.api.model.MemberType apiMemberType = null;

    /**
     * Construct with API memberType object involved in conversion.
     *
     * @param apiMemberType
     */
    public GroupMemberTypeConverter(com.silanis.esl.api.model.MemberType apiMemberType) {
        this.apiMemberType = apiMemberType;
    }

    /**
     * Construct with SDK memberType object involved in conversion.
     *
     * @param sdkMemberType
     */
    public GroupMemberTypeConverter(com.silanis.esl.sdk.GroupMemberType sdkMemberType) {
        this.sdkMemberType = sdkMemberType;
    }

    /**
     * Convert from SDK memberType to API memberType.
     *
     * @return an API MemberType object.
     */
    public com.silanis.esl.api.model.MemberType toAPIGroupMemberType() {
        if (sdkMemberType == null) {
            return apiMemberType;
        }
        switch (sdkMemberType) {
            case MANAGER:
                return MemberType.MANAGER;
            case REGULAR:
                return MemberType.REGULAR;
            default:
                throw new BuilderException("Unrecognized group member type.");
        }
    }

    /**
     * Convert from API memberType to SDK memberType.
     *
     * @return an SDK MemberType object.
     */
    public com.silanis.esl.sdk.GroupMemberType toSDKGroupMemberType() {

        if (apiMemberType == null) {
            return sdkMemberType;
        }

        switch (apiMemberType) {
            case MANAGER:
                return GroupMemberType.MANAGER;
            case REGULAR:
                return GroupMemberType.REGULAR;
            default:
                throw new BuilderException("Unrecognized group member type.");
        }

    }
}
