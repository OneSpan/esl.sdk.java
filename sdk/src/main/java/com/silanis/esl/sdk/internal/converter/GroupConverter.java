package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.builder.GroupBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 9:46 AM
 * <p/>
 * Converter between SDK and API Group.
 */
public class GroupConverter {

    private com.silanis.esl.sdk.Group sdkGroup = null;
    private com.silanis.esl.api.model.Group apiGroup = null;

    /**
     * Construct with API group object involved in conversion.
     *
     * @param apiGroup
     */
    public GroupConverter(com.silanis.esl.api.model.Group apiGroup) {
        this.apiGroup = apiGroup;
    }

    /**
     * Construct with SDK group object involved in conversion.
     *
     * @param sdkGroup
     */
    public GroupConverter(com.silanis.esl.sdk.Group sdkGroup) {
        this.sdkGroup = sdkGroup;
    }

    /**
     * Convert from SDK Group to API Group.
     *
     * @return API Group.
     */
    public com.silanis.esl.api.model.Group toAPIGroup() {
        if (sdkGroup == null) {
            return apiGroup;
        }

        com.silanis.esl.api.model.Group result = toAPIGroupWithoutMembers();
        result.setMembers(Lists.newArrayList(Iterables.transform(sdkGroup.getMembers(), new Function<com.silanis.esl.sdk.GroupMember, GroupMember>() {
            @Override
            public GroupMember apply(final com.silanis.esl.sdk.GroupMember input) {
                return new GroupMemberConverter(input).toAPIGroupMember();
            }
        })));

        return result;
    }

    /**
     * Convert from SDK Group to API Group without group members.
     *
     * @return API Group.
     */
    public com.silanis.esl.api.model.Group toAPIGroupWithoutMembers() {

        if (sdkGroup == null) {
            return apiGroup;
        }

        com.silanis.esl.api.model.Group result = new com.silanis.esl.api.model.Group();
        result.setName(sdkGroup.getName());
        result.safeSetCreated(sdkGroup.getCreated());
        result.safeSetUpdated(sdkGroup.getUpdated());
        result.safeSetEmail(sdkGroup.getEmail());
        result.safeSetEmailMembers(sdkGroup.getEmailMembers());

        if (sdkGroup.getId() != null) {
            result.safeSetId(sdkGroup.getId().getId());
        }

        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK Group.
     */
    public com.silanis.esl.sdk.Group toSDKGroup() {

        if (apiGroup == null) {
            return sdkGroup;
        }

        com.silanis.esl.sdk.Group result;
        GroupBuilder builder = GroupBuilder.newGroup(apiGroup.getName())
                                           .withEmail(apiGroup.getEmail());

        if (apiGroup.getEmailMembers()) {
            builder = builder.withIndividualMemberEmailing();
        } else {
            builder = builder.withoutIndividualMemberEmailing();
        }

        if (StringUtils.isNotBlank(apiGroup.getId())) {
            builder.withId(new GroupId(apiGroup.getId()));
        }

        result = builder.build();

        result.getMembers().addAll(Lists.newArrayList(Iterables.transform(apiGroup.getMembers(), new Function<GroupMember, com.silanis.esl.sdk.GroupMember>() {
            @Override
            public com.silanis.esl.sdk.GroupMember apply(final GroupMember input) {
                return new GroupMemberConverter(input).toSDKGroupMember();
            }
        })));

        return result;
    }

}
