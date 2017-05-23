package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.VerificationType;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;
import com.silanis.esl.sdk.internal.converter.DelegationUserConverter;
import com.silanis.esl.sdk.internal.converter.SenderConverter;
import com.silanis.esl.sdk.service.apiclient.AccountApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        com.silanis.esl.api.model.Sender sender = new AccountMemberConverter(accountMember).toAPISender();
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
        Result<com.silanis.esl.api.model.Sender> apiResponse = apiClient.getSenders(direction, request);
        Map<String, com.silanis.esl.sdk.Sender> result = new HashMap<String, com.silanis.esl.sdk.Sender>();
        for (com.silanis.esl.api.model.Sender sender : apiResponse.getResults()) {
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
        com.silanis.esl.api.model.Sender apiResponse = apiClient.getSender(senderId);
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
        com.silanis.esl.api.model.Sender apiSender = new SenderConverter(sender).toAPISender();
        apiSender.setId(senderId);
        apiClient.updateSender(apiSender, senderId);
    }

    /**
     * Get the contacts from account
     *
     * @return the contacts (key=email, value=Sender)
     */
    public Map<String, com.silanis.esl.sdk.Sender> getContacts() {
        List<com.silanis.esl.api.model.Sender> contacts = apiClient.getContacts();

        Map<String, com.silanis.esl.sdk.Sender> result = new HashMap<String, com.silanis.esl.sdk.Sender>();
        for (com.silanis.esl.api.model.Sender apiSender : contacts) {
            result.put(apiSender.getEmail(), new SenderConverter(apiSender).toSDKSender());
        }

        return result;
    }

    /**
     * Get a list of delegation users of the sender
     *
     * @param senderId   Id of the sender who's delegation users we are to retrieve.
     * @return A list of all the delegation users of the sender.
     */
    public List<com.silanis.esl.sdk.DelegationUser> getDelegates(String senderId) {
        List<com.silanis.esl.sdk.DelegationUser> result = new ArrayList<DelegationUser>();
        List<com.silanis.esl.api.model.DelegationUser> apiDelegationUsers = apiClient.getDelegates(senderId);
        for (com.silanis.esl.api.model.DelegationUser delegationUser : apiDelegationUsers) {
            result.add(new DelegationUserConverter(delegationUser).toSDKDelegationUser());
        }
        return result;
    }

    /**
     * Update the information of delegates
     *
     * @param senderId Id of the sender who's delegation users we are to update.
     * @param delegateIds The delegate Ids to be updated.
     */
    public void updateDelegates(String senderId, List<String> delegateIds) {
        apiClient.updateDelegates(senderId, delegateIds);
    }

    /**
     * Add a new delegate to the sender
     *
     * @param senderId Id of the sender who's delegation users we are to add.
     * @param delegationUser The delegation user to be added.
     */
    public void addDelegate(String senderId, com.silanis.esl.sdk.DelegationUser delegationUser) {
        com.silanis.esl.api.model.DelegationUser apiDelegationUser = new DelegationUserConverter(delegationUser).toAPIDelegationUser();
        apiClient.addDelegate(senderId, apiDelegationUser);
    }

    /**
     * Delete a delegate from the sender
     *
     * @param senderId Id of the sender who's delegation user we are to delete.
     * @param delegateId The delegate's Ids to be deleted.
     */
    public void removeDelegate(String senderId, String delegateId) {
        apiClient.removeDelegate(senderId, delegateId);
    }

    /**
     * Delete all delegates from the sender
     *
     * @param senderId Id of the sender who's all delegation users we are to delete.
     */
    public void clearDelegates(String senderId) {
        apiClient.clearDelegates(senderId);
    }


    public List<VerificationType> getVerificationTypes() {
        return apiClient.getVerificationTypes();
    }


}
