package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;
import com.silanis.esl.sdk.internal.converter.SenderConverter;

import java.util.HashMap;
import java.util.Map;


/**
 * The AccountService provides methods to help create senders for an account
 */
public class AccountService {

    private UrlTemplate template;
    private RestClient client;

    public AccountService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    /**
     * Invite a new member to the account
     *
     * @param accountMember The member to be invited
     */
    public com.silanis.esl.sdk.Sender inviteUser( AccountMember accountMember ) {
        String path = template.urlFor( UrlTemplate.ACCOUNT_MEMBER_PATH).build();
        Sender sender = new AccountMemberConverter( accountMember ).toAPISender();
        try {
            String stringResponse = client.post( path, Serialization.toJson( sender ) );
            Sender apiResponse = Serialization.fromJson( stringResponse, Sender.class );
            return new SenderConverter( apiResponse ).toSDKSender();
        } catch (RequestException e){
            throw new EslServerException( "Unable to invite member to account.", e);
        } catch ( Exception e ) {
            throw new EslException( "Unable to invite member to account.", e );
        }
    }

    /**
     * Send a reminder invite to a sender
     *
     * @param senderId The sender Id
     */
    public void sendInvite(String senderId){
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_INVITE_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            client.post(path, null);
        } catch (RequestException e){
            throw new EslServerException( "Unable to send invite to member.", e);
        } catch ( Exception e ) {
            throw new EslException( "Unable to send invite to member.", e );
        }
    }

    /**
     * Get a list of all the senders from the account
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by name.
     * @return A list mapping all the senders to their respective name
     */
    public Map<String, com.silanis.esl.sdk.Sender> getSenders(Direction direction) {
        return getSenders(direction, new PageRequest(1, 1));
    }

    /**
     * Get a list of senders from the account base on page request
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by name.
     * @param request   Identifying which page of results to return.
     * @return A list mapping all the senders to their respective name
     */
    public Map<String, com.silanis.esl.sdk.Sender> getSenders(Direction direction, PageRequest request){
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_LIST_PATH)
                .replace("{dir}", direction.getDirection())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String stringResponse = client.get(path);
            Result<Sender> apiResponse = JacksonUtil.deserialize(stringResponse, new TypeReference<Result<Sender>>() {
            });
            Map<String, com.silanis.esl.sdk.Sender> result = new HashMap<String, com.silanis.esl.sdk.Sender>();
            for ( Sender sender : apiResponse.getResults() ) {
                result.put(sender.getEmail(), new SenderConverter(sender).toSDKSender());
            }
            return result;
        }
        catch (RequestException e) {
            throw new EslServerException("Failed to retrieve Account Members List.", e);
        }
        catch (Exception e) {
            throw new EslException("Failed to retrieve Account Members List.", e);
        }
    }

    /**
     * Get the sender
     *
     * @param senderId The sender Id
     * @return The sender corresponding to the senderId
     */
    public com.silanis.esl.sdk.Sender getSender( String senderId  ) {
        String path = template.urlFor( UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String stringResponse = client.get(path);
            Sender apiResponse = Serialization.fromJson(stringResponse, Sender.class);
            return new SenderConverter( apiResponse ).toSDKSender();
        } catch (RequestException e){
            throw new EslServerException( "Unable to get member from account.", e);
        } catch ( Exception e ) {
            throw new EslException( "Unable to get member from account.", e );
        }
    }

    /**
     * Delete a sender from the account
     *
     * @param senderId The sender Id
     */

    public void deleteSender(String senderId){
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            client.delete( path );
        }
        catch ( RequestException e ) {
            throw new EslServerException( "Could not delete sender.", e );
        }
        catch ( Exception e ) {
            throw new EslException( "Could not delete sender." + " Exception: " + e.getMessage(), e );
        }
    }


    /**
     * Update the information of a sender
     *
     * @param sender The updated info of the sender
     * @param senderId The sender Id
     */
    public void updateSender(SenderInfo sender, String senderId){
        Sender apiPayload = new SenderConverter( sender ).toAPISender();
        apiPayload.setId(senderId);
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String json = Serialization.toJson(apiPayload);
            client.post(path, json);
        }
        catch ( RequestException e ) {
            throw new EslServerException( "Could not update sender.", e );
        }
        catch ( Exception e ) {
            throw new EslException( "Could not update sender." + " Exception: " + e.getMessage(), e );
        }
    }
}
