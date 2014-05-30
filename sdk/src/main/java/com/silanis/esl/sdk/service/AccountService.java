package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;

public class AccountService {

    private UrlTemplate template;
    private RestClient client;

    public AccountService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    public void inviteUser( AccountMember accountMember ) {
        String path = template.urlFor( UrlTemplate.ACCOUNT_INVITE_MEMBER_PATH ).build();
        User user = new AccountMemberConverter( accountMember ).toAPIUser();
        try {
            client.post( path, Serialization.toJson( user ) );
        } catch (RequestException e){
            throw new EslServerException( "Unable to invite member to account.", e);
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to account.", e );
        }
    }
}
