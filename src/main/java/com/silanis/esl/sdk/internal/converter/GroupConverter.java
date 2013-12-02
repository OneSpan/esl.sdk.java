package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.GroupMember;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.builder.GroupBuilder;
import com.silanis.esl.sdk.builder.GroupMemberBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jessica
 * Date: 02/12/13
 * Time: 9:46 AM
 *
 * Converter between SDK and API Group.
 *
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

        List<GroupMember> apiMembers = new ArrayList<GroupMember>();
        for ( com.silanis.esl.sdk.GroupMember sdkGroupMember : sdkGroup.getMembers() ) {
            apiMembers.add( sdkGroupMember.toAPIGroupMember() );
        }
        result.setMembers( apiMembers );

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
        result.setName( sdkGroup.getName() );
        result.safeSetCreated( sdkGroup.getCreated() );
        result.safeSetUpdated( sdkGroup.getUpdated() );
        result.setEmail( sdkGroup.getEmail() );
        result.setEmailMembers( sdkGroup.getEmailMembers() );

        if ( sdkGroup.getId() != null ) {
            result.safeSetId( sdkGroup.getId().getId() );
        }

        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return SDK Group.
     *
     */
    public com.silanis.esl.sdk.Group toSDKGroup() {

        if (apiGroup == null) {
            return sdkGroup;
        }

        com.silanis.esl.sdk.Group result;
        GroupBuilder builder = GroupBuilder.newGroup( apiGroup.getName() )
                .withEmail( apiGroup.getEmail() );

        if ( apiGroup.getEmailMembers() ) {
            builder = builder.withIndividualMemberEmailing();
        } else {
            builder = builder.withoutIndividualMemberEmailing();
        }

        if ( apiGroup.getId() != null ) {
            builder.withId( new GroupId( apiGroup.getId() ) );
        }

        result = builder.build();

        for ( com.silanis.esl.api.model.GroupMember apiGroupMember : apiGroup.getMembers() ) {
            com.silanis.esl.sdk.GroupMember sdkGroupMember = GroupMemberBuilder.fromAPIGroupMember(apiGroupMember).build();
            result.getMembers().add( sdkGroupMember );
        }
        return result;
    }

}
