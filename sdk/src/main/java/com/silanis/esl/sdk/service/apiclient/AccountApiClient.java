package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Sender;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;

/**
 * Created by dave on 31/07/14.
 */
public class AccountApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public AccountApiClient( RestClient restClient, String apiUrl ) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public Sender inviteUser(Sender sender) {
        String path = template.urlFor( UrlTemplate.ACCOUNT_MEMBER_PATH ).build();
        try {
            String stringResponse = restClient.post( path, Serialization.toJson( sender ) );
            Sender apiResponse = Serialization.fromJson(stringResponse, Sender.class);
            return apiResponse;
        } catch (RequestException e){
            throw new EslServerException( "Unable to invite member to account.", e);
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to account.", e );
        }
    }

    public void updateSender(Sender sender, String senderId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String json = Serialization.toJson(sender);
            restClient.post(path, json);
        }
        catch ( RequestException e ) {
            throw new EslServerException( "Could not update sender.", e );
        }
        catch ( Exception e ) {
            throw new EslException( "Could not update sender." + " Exception: " + e.getMessage(), e );
        }
    }
}
