package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.VerificationType;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.AccessibleAccountResponse;
import com.silanis.esl.sdk.Account;
import com.silanis.esl.sdk.DelegationUser;
import com.silanis.esl.sdk.internal.converter.*;
import com.silanis.esl.sdk.internal.converter.AccountMemberConverter;
import com.silanis.esl.sdk.internal.converter.AccountRoleConverter;
import com.silanis.esl.sdk.internal.converter.DelegationUserConverter;
import com.silanis.esl.sdk.internal.converter.SenderConverter;
import com.silanis.esl.sdk.service.apiclient.AccountApiClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.silanis.esl.api.model.AccountRole;

/**
 * The AccountService provides methods to help create senders for an account
 */
public class AccountService {

    private final AccountApiClient apiClient;

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
     * Update the information of a sender image signature
     *
     * @param fileName uploaded image file name
     * @param fileContent uploaded image file content
     * @param senderId The sender Id
     */
    public void updateSenderImageSignature(String fileName, byte[] fileContent, String senderId) {
        apiClient.updateSenderImageSignature(fileName, fileContent, senderId);
    }

    /**
     * Get the information of a sender image signature
     *
     * @param senderId The sender Id
     */
    public SenderImageSignature getSenderImageSignature(String senderId) {
        com.silanis.esl.api.model.SenderImageSignature apiResponse = apiClient.getSenderImageSignature(senderId);
        return new SenderImageSignatureConverter(apiResponse).toSDKSenderImageSignature();
    }

    /**
     * Delete the information of a sender image signature
     *
     * @param senderId The sender Id
     */
    public void deleteSenderImageSignature(String senderId) {
        apiClient.deleteSenderImageSignature(senderId);
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
    public  void updateDelegates(String senderId, List<String> delegateIds) {
        apiClient.updateDelegates(senderId, delegateIds);
    }

    /**
     * Update the information of delegates
     *
     * @param senderId Id of the sender who's delegation users we are to update.
     * @param delegates The delegates to be updated.
     */
    public void updateDelegationWithDelegationUsers(String senderId, List<DelegationUser> delegates) {
        List<com.silanis.esl.api.model.DelegationUser> apiDelegates = new ArrayList<com.silanis.esl.api.model.DelegationUser>();
        for(DelegationUser delegate : delegates) {
            apiDelegates.add(new DelegationUserConverter(delegate).toAPIDelegationUser());
        }
        apiClient.updateDelegates(senderId, apiDelegates);
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

    public List<com.silanis.esl.sdk.AccountRole> getAccountRoles() {
        List<com.silanis.esl.sdk.AccountRole> roles = new ArrayList<com.silanis.esl.sdk.AccountRole>();

        for (AccountRole accountRole : apiClient.getAccountRoles()) {
            roles.add(new AccountRoleConverter(accountRole).toSDKAccountRole());
        }
        return roles;
    }

    public void addAccountRole(com.silanis.esl.sdk.AccountRole accountRole) { apiClient.addAccountRole(new AccountRoleConverter(accountRole).toAPIAccountRole()); }

    public void updateAccountRole(String accountRoleId, com.silanis.esl.sdk.AccountRole accountRole) { apiClient.updateAccountRole(accountRoleId, new AccountRoleConverter(accountRole).toAPIAccountRole()); }

    public com.silanis.esl.sdk.AccountRole getAccountRole(String accountRoleId) {
        return new AccountRoleConverter(apiClient.getAccountRole(accountRoleId)).toSDKAccountRole();
    }

    /**
     * Get a list of subAccounts from the account base on page request
     *
     * @return A list of all the subAccounts of the account.
     */
    public List<com.silanis.esl.sdk.Account> getSubAccounts() {
        List<com.silanis.esl.api.model.Account> apiAccounts = apiClient.getSubAccounts();
        List<com.silanis.esl.sdk.Account> accounts = new ArrayList<Account>();
        for (com.silanis.esl.api.model.Account account : apiAccounts) {
            accounts.add(new AccountConverter(account).toSDKAccount());
        }
        return accounts;

    }

    /**
     * Retrieves a list of API keys for this current user in the subaccounts
     *
     * @return A list of API keys for this current user in the subaccounts.
     */
    public List<com.silanis.esl.sdk.SubAccountApiKey> getSubAccountApiKeys() {
        List<com.silanis.esl.api.model.SubAccountApiKey> subAccountApiKeyResponses = apiClient.getSubAccountApiKey();
        List<com.silanis.esl.sdk.SubAccountApiKey> subAccountApiKeyList = new ArrayList<SubAccountApiKey>();
        for (com.silanis.esl.api.model.SubAccountApiKey subAccountApiKey : subAccountApiKeyResponses) {
            subAccountApiKeyList.add(new SubAccountApiKeyConverter(subAccountApiKey).toSDKSubAccountApiKey());
        }
        return subAccountApiKeyList;
    }


    /**
     * Get a list of accessible subAccounts from the account base on page request
     *
     * @return A list of accessible subAccounts of the account.
     */
    public List<com.silanis.esl.sdk.AccessibleAccountResponse> getAccessibleAccounts() {
        List<com.silanis.esl.api.model.AccessibleAccountResponse> apiAccessibleAccounts = apiClient.getAccessibleAccounts();
        List<com.silanis.esl.sdk.AccessibleAccountResponse> accountResponses = new ArrayList<AccessibleAccountResponse>();
        for (com.silanis.esl.api.model.AccessibleAccountResponse accountResponse : apiAccessibleAccounts) {
            accountResponses.add(new AccessibleAccountResponseConverter(accountResponse).toSDKAccessibleAccountResponse());
        }
        return accountResponses;
    }


    /**
     * Create new subAccount for the account base on page request
     *
     * @param subAccount The created info of the subAccount
     */
    public com.silanis.esl.sdk.Account createSubAccount(com.silanis.esl.sdk.SubAccount subAccount) {
        com.silanis.esl.api.model.SubAccount apiSubAccount = new SubAccountConverter(subAccount).toAPISubAccount();
        com.silanis.esl.api.model.Account apiAccount = apiClient.createSubAccount(apiSubAccount);
        return new AccountConverter(apiAccount).toSDKAccount();
    }

    /**
     * Update the information of a subAccount
     *
     * @param subAccount The updated info of the subAccount
     * @param accountId The subAccount Id
     */
    public void updateSubAccount(com.silanis.esl.sdk.SubAccount subAccount, String accountId) {
        com.silanis.esl.api.model.SubAccount apiSubAccount = new SubAccountConverter(subAccount).toAPISubAccount();
        apiClient.updateSubAccount(apiSubAccount, accountId);
    }


    public void deleteAccountRole(String accountRoleId) { apiClient.deleteAccountRole(accountRoleId); }

    public List<String> getAccountRoleUsers(String accountRoleId) { return apiClient.getAccountRoleUsers(accountRoleId); }

    public List<UserAccountRole> getAssignedAccountRoles(String userId) {
        return this.getAssignedAccountRoles(userId, null);
    }

    public List<UserAccountRole> getAssignedAccountRoles(String userId, String accountId) {
        List<com.silanis.esl.api.model.UserAccountRole> accountRole = apiClient.getAssignedAccountRoles(userId, accountId);
        List<UserAccountRole> sdkUserAccountRoles = new ArrayList<UserAccountRole>();
        for(com.silanis.esl.api.model.UserAccountRole userAccountRole: accountRole){
           sdkUserAccountRoles.add((new UserAccountRoleConverter(userAccountRole)).toSDKUserAccountRole());
        }
        return sdkUserAccountRoles;
    }

    public UserAccountRole assignAccountRoleToUser(String userId, com.silanis.esl.sdk.UserAccountRole sdkUserAccountRole){
        com.silanis.esl.api.model.UserAccountRole userAccountRole = apiClient.assignAccountRoleToUser(userId, new UserAccountRoleConverter(sdkUserAccountRole).toAPIUserAccountRole());
        return new UserAccountRoleConverter(userAccountRole).toSDKUserAccountRole();
    }
}
