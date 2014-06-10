package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.model.User;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SenderInfo;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;
import com.silanis.esl.sdk.internal.converter.SenderConverter;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private UrlTemplate template;
    private RestClient client;

    public AccountService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    public void inviteUser( AccountMember accountMember ) {
        String path = template.urlFor( UrlTemplate.ACCOUNT_MEMBER_PATH).build();
        User user = new AccountMemberConverter( accountMember ).toAPIUser();
        try {
            client.post( path, Serialization.toJson( user ) );
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to account.", e );
        }
    }

    public Map<String, com.silanis.esl.sdk.Sender> getSenders(){
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_PATH).build();

        try {
            String stringResponse = client.get(path);
            Result<Sender> apiResponse = JacksonUtil.deserialize(stringResponse, new TypeReference<Result<Sender>>() {
            });
            Map<String, com.silanis.esl.sdk.Sender> result = new HashMap<String, com.silanis.esl.sdk.Sender>();
            for ( Sender sender : apiResponse.getResults() ) {
                result.put(sender.getEmail(), new SenderConverter(sender).toSDKSender());
            }
            return result;
        } catch (Exception e) {
            throw new EslException("Failed to retrieve Account Members List.", e);
        }
    }

    public void deleteSender(String senderId){
        String path = template.urlFor(UrlTemplate.SENDER_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            client.delete( path );
        } catch ( Exception e ) {
            throw new EslException( "Could not delete sender." + " Exception: " + e.getMessage(), e );
        }
    }

    public void updateSender(SenderInfo sender, String senderId){
        Sender apiPayload = new SenderConverter( sender ).toAPISender();
        String path = template.urlFor(UrlTemplate.SENDER_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String json = Serialization.toJson(apiPayload);
            client.post(path, json);
        } catch ( Exception e ) {
            throw new EslException( "Could not update sender." + " Exception: " + e.getMessage(), e );
        }
    }
}
