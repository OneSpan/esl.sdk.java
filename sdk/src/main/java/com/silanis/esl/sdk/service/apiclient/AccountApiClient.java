package com.silanis.esl.sdk.service.apiclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.internal.*;

import java.util.List;

/**
 * Created by dave on 31/07/14.
 */
public class AccountApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public AccountApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public Sender inviteUser(Sender sender) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_PATH).build();
        try {
            String stringResponse = restClient.post(path, Serialization.toJson(sender));
            Sender apiResponse = Serialization.fromJson(stringResponse, Sender.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Unable to invite member to account.", e);
        } catch (Exception e) {
            throw new EslException("Unable to invite member to account.", e);
        }
    }

    public void sendInvite(String senderId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_INVITE_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            restClient.post(path, null);
        } catch (RequestException e) {
            throw new EslServerException("Unable to send invite to member.", e);
        } catch (Exception e) {
            throw new EslException("Unable to send invite to member.", e);
        }
    }

    public Result<Sender> getSenders(Direction direction, PageRequest request) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_LIST_PATH)
                .replace("{dir}", direction.getDirection())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String stringResponse = restClient.get(path);
            Result<Sender> apiResponse = JacksonUtil.deserialize(stringResponse, new TypeReference<Result<Sender>>() {
            });
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Failed to retrieve Account Members List.", e);
        } catch (Exception e) {
            throw new EslException("Failed to retrieve Account Members List.", e);
        }
    }

    public Sender getSender(String senderId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJson(stringResponse, Sender.class);
        } catch (RequestException e) {
            throw new EslServerException("Unable to get member from account.", e);
        } catch (Exception e) {
            throw new EslException("Unable to get member from account.", e);
        }
    }

    public void deleteSender(String senderId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete sender.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete sender." + " Exception: " + e.getMessage(), e);
        }
    }

    public void updateSender(Sender sender, String senderId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_ID_PATH)
                .replace("{senderUid}", senderId)
                .build();
        try {
            String json = Serialization.toJson(sender);
            restClient.post(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update sender.", e);
        } catch (Exception e) {
            throw new EslException("Could not update sender." + " Exception: " + e.getMessage(), e);
        }
    }

    public List<Sender> getContacts() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CONTACTS_PATH)
                .build();

        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, Sender.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get contacts.", e);
        } catch (Exception e) {
            throw new EslException("Could not get contacts." + " Exception: " + e.getMessage(), e);
        }
    }
}
