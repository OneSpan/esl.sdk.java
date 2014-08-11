package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.SenderInfo;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;
import com.silanis.esl.sdk.internal.converter.SenderConverter;
import com.silanis.esl.sdk.service.apiclient.AccountApiClient;

import java.util.HashMap;
import java.util.Map;


/**
 * The AccountService provides methods to help create senders for an account
 */
public class AccountService {

    private AccountApiClient apiClient;

    public AccountService(AccountApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Invite a new member to the account
     *
     * @param accountMember The member to be invited
     */
    public com.silanis.esl.sdk.Sender inviteUser(AccountMember accountMember) {
        Sender sender = new AccountMemberConverter(accountMember).toAPISender();
        sender = apiClient.inviteUser(sender);
        return new SenderConverter(sender).toSDKSender();
    }

    /**
     * Send a reminder invite to a sender
     *
     * @param senderId The sender Id
     */
    public void sendInvite(String senderId) {
        apiClient.sendInvite(senderId);
    }

    /**
     * Get a list of senders from the account base on page request
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by name.
     * @param request   Identifying which page of results to return.
     * @return A list mapping all the senders to their respective name
     */
    public Map<String, com.silanis.esl.sdk.Sender> getSenders(Direction direction, PageRequest request) {
        Result<Sender> apiResponse = apiClient.getSenders(direction, request);
        Map<String, com.silanis.esl.sdk.Sender> result = new HashMap<String, com.silanis.esl.sdk.Sender>();
        for (Sender sender : apiResponse.getResults()) {
            result.put(sender.getEmail(), new SenderConverter(sender).toSDKSender());
        }
        return result;
    }

    /**
     * Get the sender
     *
     * @param senderId The sender Id
     * @return The sender corresponding to the senderId
     */
    public com.silanis.esl.sdk.Sender getSender(String senderId) {
        Sender apiResponse = apiClient.getSender(senderId);
        return new SenderConverter(apiResponse).toSDKSender();
    }

    /**
     * Delete a sender from the account
     *
     * @param senderId The sender Id
     */
    public void deleteSender(String senderId) {
        apiClient.deleteSender(senderId);
    }


    /**
     * Update the information of a sender
     *
     * @param sender   The updated info of the sender
     * @param senderId The sender Id
     */
    public void updateSender(SenderInfo sender, String senderId) {
        Sender apiSender = new SenderConverter(sender).toAPISender();
        apiSender.setId(senderId);
        apiClient.updateSender(apiSender, senderId);
    }
}
