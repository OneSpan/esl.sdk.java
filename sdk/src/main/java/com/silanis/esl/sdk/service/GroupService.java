package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.Group;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.GroupMember;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.GroupConverter;
import com.silanis.esl.sdk.internal.converter.GroupMemberConverter;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private UrlTemplate template;
    private RestClient client;

    public GroupService( RestClient client, String baseUrl ) {
        this.client = client;
        template = new UrlTemplate( baseUrl );
    }

    /**
     * Get a list of all groups belong to the account under which the requests are being issued.
     *
     * @return
     */
    public List<Group> getMyGroups() {
        String path = template.urlFor( UrlTemplate.GROUPS_PATH ).build();
        try {
            String stringResponse = client.get( path );
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
        String path = template.urlFor( UrlTemplate.GROUPS_ID_PATH ).replace( "{groupId}", groupId.getId() ).build();
        try {
            String stringResponse = client.get( path );
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
        String path = template.urlFor( UrlTemplate.GROUPS_PATH ).build();
        com.silanis.esl.api.model.Group apiGroup = new GroupConverter(group).toAPIGroupWithoutMembers();
        try {
            String stringResponse = client.post( path, Serialization.toJson( apiGroup ) );
            com.silanis.esl.api.model.Group apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.Group.class );
            Group resultGroup =  new GroupConverter(apiResponse).toSDKGroup();
            for ( GroupMember groupMember : group.getMembers() ) {
                inviteMember( resultGroup.getId(), groupMember );
            }
            return resultGroup;
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to create Group.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to create Group.", e );
        }
    }

    public GroupMember inviteMember( GroupId groupId, GroupMember groupMember ) {
        String path = template.urlFor(  UrlTemplate.GROUPS_MEMBER_PATH ).build().replace( "{groupId}", groupId.getId() );
        com.silanis.esl.api.model.GroupMember apiGroupMember = new GroupMemberConverter(groupMember).toAPIGroupMember();
        try {
            String stringResponse = client.post( path, Serialization.toJson( apiGroupMember ) );
            com.silanis.esl.api.model.GroupMember apiResponse = Serialization.fromJson( stringResponse, com.silanis.esl.api.model.GroupMember.class );
            GroupMember resultGroupMember = new GroupMemberConverter(apiResponse).toSDKGroupMember();
            return resultGroupMember;
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
        String path = template.urlFor( UrlTemplate.GROUPS_ID_PATH ).replace( "{groupId}", groupId.getId() ).build();
        try {
            client.delete( path );
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
}
