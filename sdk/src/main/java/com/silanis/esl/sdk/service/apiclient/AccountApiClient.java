package com.silanis.esl.sdk.service.apiclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.AccountRole;
import com.silanis.esl.api.model.DelegationUser;
import com.silanis.esl.api.model.Result;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.model.VerificationType;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.Direction;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import java.util.List;

/**
 * Created by dave on 31/07/14.
 */
public class AccountApiClient {

    private final UrlTemplate template;
    private final RestClient restClient;

    public AccountApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        this.template = new UrlTemplate(apiUrl);
    }

    public Sender inviteUser(Sender sender) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_MEMBER_PATH).build();
        try {
            String stringResponse = restClient.post(path, Serialization.toJson(sender));
            return Serialization.fromJson(stringResponse, Sender.class);
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
            return JacksonUtil.deserialize(stringResponse, new TypeReference<Result<Sender>>() {});
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

    public List<DelegationUser> getDelegates(String senderId) {
        String path = template.urlFor(UrlTemplate.DELEGATES_PATH)
                              .replace("{senderId}", senderId)
                              .build();

        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, DelegationUser.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get delegates.", e);
        } catch (Exception e) {
            throw new EslException("Could not get delegates." + " Exception: " + e.getMessage(), e);
        }
    }

    public void updateDelegates(String senderId, List<String> delegateIds) {
        String path = template.urlFor(UrlTemplate.DELEGATES_PATH)
                              .replace("{senderId}", senderId)
                              .build();
        try {
            String json = Serialization.toJson(delegateIds);
            restClient.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update delegates.", e);
        } catch (Exception e) {
            throw new EslException("Could not update delegates." + " Exception: " + e.getMessage(), e);
        }
    }

    public void addDelegate(String senderId, DelegationUser delegationUser) {
        String path = template.urlFor(UrlTemplate.DELEGATE_ID_PATH)
                              .replace("{senderId}", senderId)
                              .replace("{delegateId}", delegationUser.getId())
                              .build();
        try {
            String json = Serialization.toJson(delegationUser);
            restClient.post(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not add a delegate.", e);
        } catch (Exception e) {
            throw new EslException("Could not add a delegate." + " Exception: " + e.getMessage(), e);
        }
    }

    public void removeDelegate(String senderId, String delegateId) {
        String path = template.urlFor(UrlTemplate.DELEGATE_ID_PATH)
                              .replace("{senderId}", senderId)
                              .replace("{delegateId}", delegateId)
                              .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not remove a delegate.", e);
        } catch (Exception e) {
            throw new EslException("Could not remove a delegate." + " Exception: " + e.getMessage(), e);
        }
    }

    public void clearDelegates(String senderId) {
        String path = template.urlFor(UrlTemplate.DELEGATES_PATH)
                              .replace("{senderId}", senderId)
                              .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not clear delegates.", e);
        } catch (Exception e) {
            throw new EslException("Could not clear delegates." + " Exception: " + e.getMessage(), e);
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

    public List<VerificationType> getVerificationTypes() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_VERIFICATION_TYPE_PATH)
                // TODO: Why we need pass accountId when it is not used in backend?
                .replace("{accountId}", "dummyAccountId")
                .build();

        try {
            String stringResponse = restClient.get(path);
            Result<?> result = Serialization.fromJson(stringResponse, Result.class);

            return Serialization.fromJsonToList(Serialization.toJson(result.getResults()), VerificationType.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get verification types.", e);
        } catch (Exception e) {
            throw new EslException("Could not get verification types." + " Exception: " + e.getMessage(), e);
        }
    }

    public List<Account> getSubAccounts() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SUBACCOUNTS_PATH).build();
        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, Account.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get subAccounts.", e);
        } catch (Exception e) {
            throw new EslException("Could not get subAccounts." + " Exception: " + e.getMessage(), e);
        }
    }

    public List<AccessibleAccountResponse> getAccessibleAccounts() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SUBACCOUNTS_ACCESSIBLEACCOUNTS_PATH).build();
        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, AccessibleAccountResponse.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get accessibleAccounts.", e);
        } catch (Exception e) {
            throw new EslException("Could not get accessibleAccounts." + " Exception: " + e.getMessage(), e);
        }
    }

    public Account createSubAccount(SubAccount subAccount) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SUBACCOUNTS_PATH).build();
        try {
            String json = Serialization.toJson(subAccount);
            String stringResponse = restClient.post(path, json);
            return Serialization.fromJson(stringResponse, Account.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create subAccount.", e);
        } catch (Exception e) {
            throw new EslException("Could not create subAccount." + " Exception: " + e.getMessage(), e);
        }
    }

    public void updateSubAccount(SubAccount subAccount, String accountId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_SUBACCOUNTS_ID_PATH)
                .replace("{accountId}", accountId)
                .build();
        try {
            String json = Serialization.toJson(subAccount);
            restClient.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update subAccount.", e);
        } catch (Exception e) {
            throw new EslException("Could not update subAccount." + " Exception: " + e.getMessage(), e);
        }
    }

    public Result<AccountRole> getAccountRoles() {
    public List<AccountRole> getAccountRoles() {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_PATH).build();

        try {
            return JacksonUtil.deserialize(restClient.get(path), new TypeReference<Result<AccountRole>>() {}).getResults();
        } catch (RequestException e) {
            throw new EslServerException("Could not get roles.", e);
        } catch (Exception e) {
            throw new EslException("Could not get roles." + " Exception: " + e.getMessage(), e);
        }
    }

    public void addAccountRole(AccountRole accountRole) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_PATH).build();

        try {
            restClient.post(path, JacksonUtil.serialize(accountRole));
        } catch (RequestException e) {
            throw new EslServerException("Could not add account role.", e);
        } catch (Exception e) {
            throw new EslException("Could not add account role." + " Exception: " + e.getMessage(), e);
        }
    }

    public void updateAccountRole(String accountRoleId, AccountRole accountRole) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_ROLE_PATH)
            .replace("{accountRoleId}", accountRoleId)
            .build();

        try {
            restClient.put(path, JacksonUtil.serialize(accountRole));
        } catch (RequestException e) {
            throw new EslServerException("Could not update account role.", e);
        } catch (Exception e) {
            throw new EslException("Could not update account role." + " Exception: " + e.getMessage(), e);
        }
    }

    public AccountRole getAccountRole(String accountRoleId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_ROLE_PATH)
            .replace("{accountRoleId}", accountRoleId)
            .build();

        try {
            return JacksonUtil.deserialize(restClient.get(path), AccountRole.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get account role.", e);
        } catch (Exception e) {
            throw new EslException("Could not get account role." + " Exception: " + e.getMessage(), e);
        }
    }

    public void deleteAccountRole(String accountRoleId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_ROLE_PATH).build();

        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete account role.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete account role." + " Exception: " + e.getMessage(), e);
        }
    }

    public List<String> getAccountRoleUsers(String accountRoleId) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_ROLES_ROLE_USERS_PATH)
            .replace("{accountRoleId}", accountRoleId)
            .build();

        try {
            return JacksonUtil.deserializeList(restClient.get(path), String.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get account role users.", e);
        } catch (Exception e) {
            throw new EslException("Could not get account role users." + " Exception: " + e.getMessage(), e);
        }
    }
}
