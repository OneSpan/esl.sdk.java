package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.GroupConverter;
import com.silanis.esl.sdk.internal.converter.GroupMemberConverter;
import com.silanis.esl.sdk.internal.converter.GroupSummaryConverter;

import java.util.ArrayList;
import java.util.List;

public class GroupService extends EslComponent {

    public GroupService(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    /**
     * Get a list of all groups belong to the account under which the requests are being issued.
     *
     * @return
     */
    public List<Group> getMyGroups() {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_PATH ).build();
        return getGroups(path);
    }

    /**
     * Retrieve group(s) belonging to the account by specifying complete (or part of) GroupName.
     *
     * @param groupName
     * @return
     */
    public List<Group> getMyGroups( String groupName ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_PATH).addParam("name", groupName ).build();
        return getGroups(path);
    }

    private List<Group> getGroups(String path) {
        try {
            String stringResponse = getClient().get( path );
            Result<com.silanis.esl.api.model.Group> apiResponse = JacksonUtil.deserialize( stringResponse, new TypeReference<Result<com.silanis.esl.api.model.Group>>() {
            } );
            List<Group> result = new ArrayList<Group>();
            for ( com.silanis.esl.api.model.Group apiGroup : apiResponse.getResults() ) {
                result.add( new GroupConverter(apiGroup).toSDKGroup());
            }
            return result;
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to retrieve Groups list.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to retrieve Groups list.", e );
        }
    }

    /**
     * Retrieve a single group belonging to this account, specified by GroupId.
     *
     * @param groupId
     * @return
     */
    public Group getGroup( GroupId groupId ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_ID_PATH ).replace( "{groupId}", groupId.getId() ).build();
        try {
            String stringResponse = getClient().get( path );
            com.silanis.esl.api.model.Group apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.Group.class );
            return new GroupConverter(apiResponse).toSDKGroup();
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to retrieve Group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to retrieve Group.", e );
        }
    }

    /**
     * Create a new group belonging to this account.
     *
     * @param group
     * @return
     */
    public Group createGroup( Group group ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_PATH ).build();
        com.silanis.esl.api.model.Group apiGroup = new GroupConverter(group).toAPIGroupWithoutMembers();
        try {
            String stringResponse = getClient().post( path, Serialization.toJson( apiGroup ) );
            com.silanis.esl.api.model.Group apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.Group.class );
            Group resultGroup =  new GroupConverter(apiResponse).toSDKGroup();
            for ( GroupMember groupMember : group.getMembers() ) {
                addMember(resultGroup.getId(), groupMember);
            }
            return resultGroup;
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to create Group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to create Group.", e );
        }
    }

    /**
     * Update a group belonging to this account
     *
     * @param group
     * @return
     */
    public Group updateGroup( Group group, GroupId groupId ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_ID_PATH )
                .replace("{groupId}", groupId.getId())
                .build();
        com.silanis.esl.api.model.Group apiGroup = new GroupConverter(group).toAPIGroup();
        try {
            String stringResponse = getClient().put( path, Serialization.toJson( apiGroup ) );
            com.silanis.esl.api.model.Group apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.Group.class );

            Group resultGroup =  new GroupConverter(apiResponse).toSDKGroup();
            return resultGroup;
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to update Group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to update Group.", e );
        }
    }

    /**
     * Invite a new sender to the group. Can be used to add a new member to a group.
     *
     * @param groupId
     * @param groupMember
     * @return The group member
     */
    public GroupMember addMember(GroupId groupId, GroupMember groupMember) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(  UrlTemplate.GROUPS_MEMBER_PATH ).build().replace( "{groupId}", groupId.getId() );
        com.silanis.esl.api.model.GroupMember apiGroupMember = new GroupMemberConverter(groupMember).toAPIGroupMember();
        try {
            String stringResponse = getClient().post( path, Serialization.toJson( apiGroupMember ) );
            com.silanis.esl.api.model.GroupMember apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.GroupMember.class );
            GroupMember resultGroupMember = new GroupMemberConverter(apiResponse).toSDKGroupMember();
            return resultGroupMember;
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to add member to group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to add member to group.", e );
        }
    }

    /**
     * Invite a new sender to the group. Can be used to add a new member to a group.
     *
     * @param groupId
     * @param groupMember
     * @return The group with the new member
     */
    public Group inviteMember(GroupId groupId, GroupMember groupMember) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(  UrlTemplate.GROUPS_INVITE_PATH ).build().replace( "{groupId}", groupId.getId() );
        com.silanis.esl.api.model.GroupMember apiGroupMember = new GroupMemberConverter(groupMember).toAPIGroupMember();
        try {
            String stringResponse = getClient().post( path, Serialization.toJson( apiGroupMember ) );
            com.silanis.esl.api.model.Group apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.Group.class );
            Group resultGroup = new GroupConverter(apiResponse).toSDKGroup();
            return resultGroup;
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to invite member to group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to group.", e );
        }
    }

    /**
     * Delete a group belonging to this account, specified by GroupId
     *
     * @param groupId
     */
    public void deleteGroup( GroupId groupId ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_ID_PATH ).replace( "{groupId}", groupId.getId() ).build();
        try {
            getClient().delete( path );
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to delete Group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to delete Group.", e );
        }
    }

    /**
     * Retrieve a list of emails for all members of a group belonging to this account, specified by GroupId.
     *
     * @param groupId
     * @return Null if the group doesn't exist, otherwise a list of all group member emails.
     */
    public List<String> getGroupMemberEmails( GroupId groupId ) {
        List<String> result = null;
        Group group = getGroup( groupId );

        if ( group != null ) {
            result = new ArrayList<String>();

            for ( GroupMember groupMember : group.getMembers() ) {
                result.add( groupMember.getEmail() );
            }
        }

        return result;
    }

    /**
     * Retrieve a list of all members of a group belonging to this account, specified by GroupId.
     *
     * @param groupId
     * @return Null if the group doesn't exist, the group's members otherwise.
     */
    public List<GroupMember> getGroupMembers( GroupId groupId ) {
        List<GroupMember> result = null;

        Group group = getGroup( groupId );
        if ( group != null ) {
            result = group.getMembers();
        }

        return result;
    }

    /**
     * Retrieve summaries for all groups for the current account.
     *
     * @return summaries for all groups for the current account.
     */
    public List<com.silanis.esl.sdk.GroupSummary> getGroupSummaries() {
        List<com.silanis.esl.sdk.GroupSummary> result = new ArrayList<com.silanis.esl.sdk.GroupSummary>();
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.GROUPS_SUMMARY_PATH ).build();
        try {
            String stringResponse = getClient().get( path );
            Result<com.silanis.esl.api.model.GroupSummary> apiResponse = JacksonUtil.deserialize( stringResponse, new TypeReference<Result<com.silanis.esl.api.model.GroupSummary>>() {
            } );
            for(com.silanis.esl.api.model.GroupSummary apiGroupSummary : apiResponse.getResults()) {
                result.add(new GroupSummaryConverter(apiGroupSummary).toSDKGroupSummary());
            }
            return result;
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to retrieve Group Summary list.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to retrieve Group Summary list.", e );
        }
    }
}
