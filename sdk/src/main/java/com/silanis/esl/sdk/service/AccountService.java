package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Sender;
import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
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
        Sender sender = new AccountMemberConverter( accountMember ).toAPISender();
        try {
            client.post( path, Serialization.toJson( sender ) );
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to account.", e );
        }
    }
}
