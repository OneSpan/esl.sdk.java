package com.silanis.esl.sdk.internal.converter;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 1:50 PM
 * <p/>
 * Converter between SDK  and API group member type.
 */
public class GroupMemberTypeConverter {

    private com.silanis.esl.sdk.GroupMemberType sdkMemberType = null;
    private String apiMemberType = null;

    /**
     * Construct with API memberType object involved in conversion.
     *
     * @param apiMemberType
     */
    public GroupMemberTypeConverter(String apiMemberType) {
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
    public String toAPIGroupMemberType() {
        if (sdkMemberType == null) {
            return apiMemberType;
        }

        if(sdkMemberType.getValue().equals("MANAGER"))
            return "MANAGER";
        else if (sdkMemberType.getValue().equals("REGULAR"))
            return "REGULAR";
        else
            return "";
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

        if (apiMemberType.equals("MANAGER"))
            return sdkMemberType.MANAGER;
        else if (apiMemberType.equals("REGULAR"))
            return sdkMemberType.REGULAR;
        else
            return sdkMemberType.UNRECOGNIZED(apiMemberType);
    }
}
