package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.BaseMessage;
import com.silanis.esl.api.model.Delivery;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.Asserts;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static com.silanis.esl.api.util.AdHocGroupUtils.isAdHocGroupSigner;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 11:53 AM
 * <p>
 * Converter between SDK Signer and API Signer.
 */
public class SignerConverter {
    private com.silanis.esl.sdk.Signer sdkSigner = null;
    private com.silanis.esl.api.model.Signer apiSigner = null;
    private com.silanis.esl.api.model.Role apiRole = null;

    /**
     * Construct with API signer object involved in conversion.
     *
     * @param apiRole
     */
    public SignerConverter(com.silanis.esl.api.model.Role apiRole) {
        this.apiRole = apiRole;

        if (apiRole != null) {
            this.apiSigner = apiRole.getSigners().get(0);
        }
    }

    /**
     * Construct with SDK signer object involved in conversion.
     *
     * @param sdkSigner
     */
    public SignerConverter(com.silanis.esl.sdk.Signer sdkSigner) {
        this.sdkSigner = sdkSigner;
    }

    /**
     * Convert from SDK signer to API signer.
     *
     * @return an API Signer object.
     */

    public com.silanis.esl.api.model.Signer toAPISigner() {
        if (sdkSigner == null) {
            return apiSigner;
        }
        com.silanis.esl.api.model.Signer result = new com.silanis.esl.api.model.Signer();

        if (!sdkSigner.isGroupSigner()) {
            result.setEmail(sdkSigner.getEmail())
                    .setFirstName(sdkSigner.getFirstName())
                    .setLastName(sdkSigner.getLastName())
                    .setTitle(sdkSigner.getTitle())
                    .setCompany(sdkSigner.getCompany())
                    .setKnowledgeBasedAuthentication(new KnowledgeBasedAuthenticationConverter(sdkSigner.getKnowledgeBasedAuthentication()).toAPIKnowledgeBasedAuthentication())
                    .setDelivery(new Delivery().setEmail(sdkSigner.isDeliverSignedDocumentsByEmail()));

        } else {
            result.setGroup(new com.silanis.esl.api.model.Group().setId(sdkSigner.getGroupId().toString()));
        }

        if (sdkSigner.getLanguage() != null) {
            result.setLanguage(LocaleConverter.convertToString(sdkSigner.getLanguage()));
        }

        if (sdkSigner.getId() != null) {
            result.setId(sdkSigner.getId());
        }

        result.setAuth(new AuthenticationConverter(sdkSigner.getAuthentication()).toAPIAuthentication());

        if (sdkSigner.getNotificationMethods() != null){
            result.setPhone(sdkSigner.getNotificationMethods().getPhone())
                    .setNotificationMethods(new NotificationMethodsConverter(sdkSigner.getNotificationMethods()).toAPINotificationMethods());
        }


        return result;
    }

    private Signer newRegularSignerFromAPIRole() {
        SignerBuilder signerBuilder;

        if (apiSigner.getGroup() == null) {
            signerBuilder = SignerBuilder.newSignerWithEmail(apiSigner.getEmail())
                    .withFirstName(apiSigner.getFirstName())
                    .withLastName(apiSigner.getLastName())
                    .withCompany(apiSigner.getCompany())
                    .withLanguage(LocaleConverter.convertToLocale(apiSigner.getLanguage()))
                    .withTitle(apiSigner.getTitle())
                    .challengedWithKnowledgeBasedAuthentication(new KnowledgeBasedAuthenticationConverter(apiSigner.getKnowledgeBasedAuthentication()).toSDKKnowledgeBasedAuthentication());
            if (apiSigner.getDelivery() != null && apiSigner.getDelivery().getEmail()) {
                signerBuilder.deliverSignedDocumentsByEmail();
            }
        } else if (isAdHocGroupSigner(apiSigner)) {
            signerBuilder = SignerBuilder.newSignerWithEmail(apiSigner.getEmail())
                .withFirstName(apiSigner.getFirstName())
                .withCompany(apiSigner.getCompany())
                .withLanguage(LocaleConverter.convertToLocale(apiSigner.getLanguage()))
                .withAdhocGroupSigner(true)
                .challengedWithKnowledgeBasedAuthentication(new KnowledgeBasedAuthenticationConverter(apiSigner.getKnowledgeBasedAuthentication()).toSDKKnowledgeBasedAuthentication());
            if (apiSigner.getDelivery() != null && apiSigner.getDelivery().getEmail()) {
                signerBuilder.deliverSignedDocumentsByEmail();
            }
        } else {
            signerBuilder = SignerBuilder.newSignerFromGroup(
                new GroupId(apiSigner.getGroup().getId()));
        }

        signerBuilder.withCustomId(apiSigner.getId())
                .withRoleId(apiRole.getId());

        if (apiRole.getIndex() != null)
            signerBuilder.signingOrder(apiRole.getIndex());

        if (apiRole.evalReassign()) {
            signerBuilder.canChangeSigner();
        }

        if (apiRole.getEmailMessage() != null) {
            signerBuilder.withEmailMessage(apiRole.getEmailMessage().getContent());
        }

        signerBuilder.withAuthentication(new AuthenticationConverter(apiSigner.getAuth()).toSDKAuthentication());


        if (apiSigner.getNotificationMethods() != null) {
            signerBuilder.withNotificationMethods(newNotificationMethods()
                    .withPrimaryMethods(NotificationMethodsConverter.convertNotificationMethodsToSDK(apiSigner.getNotificationMethods().getPrimary()))
                    .withPhoneNumber(apiSigner.getPhone()));
        }


        for (com.silanis.esl.api.model.AttachmentRequirement attachmentRequirement : apiRole.getAttachmentRequirements()) {
            signerBuilder.withAttachmentRequirement(new AttachmentRequirementConverter(attachmentRequirement).toSDKAttachmentRequirement());
        }

        Signer signer = signerBuilder.build();

        if (apiSigner.getSignerType() != null) {
            signer.setSignerType(apiSigner.getSignerType());
        }

        if (apiRole.evalLocked()) {
            signer.setLocked(true);
        }

        Map<String, Object> apiRoleData = apiRole.getData();
        if (apiRoleData != null && apiRoleData.containsKey(Role.LOCAL_LANGUAGE_DATA_KEY)) {
            Object localLanguage = apiRoleData.get(Role.LOCAL_LANGUAGE_DATA_KEY);
            if (localLanguage != null) {
                signer.setLocalLanguage(localLanguage.toString());
            }
        }

        return signer;
    }

    private Signer newSignerPlaceholderFromAPIRole() {
        Asserts.notNullOrEmpty(apiRole.getId(), "role.id");

        SignerBuilder signerBuilder = SignerBuilder.newSignerPlaceholder(new Placeholder(apiRole.getId()))
                .signingOrder(apiRole.getIndex());

        if (apiRole.evalReassign()) {
            signerBuilder.canChangeSigner();
        }

        if (apiRole.getEmailMessage() != null) {
            signerBuilder.withEmailMessage(apiRole.getEmailMessage().getContent());
        }

        if (apiRole.getIndex() != null) {
            signerBuilder.signingOrder(apiRole.getIndex());
        }

        Signer signer = signerBuilder.build();

        if (apiRole.getLocked()) {
            signer.setLocked(true);
        }

        Map<String, Object> apiRoleData = apiRole.getData();
        if (apiRoleData != null && apiRoleData.containsKey(Role.LOCAL_LANGUAGE_DATA_KEY)) {
            Object localLanguage = apiRoleData.get(Role.LOCAL_LANGUAGE_DATA_KEY);
            if (localLanguage != null) {
                signer.setLocalLanguage(localLanguage.toString());
            }
        }

        return signer;
    }

    /**
     * Convert from API signer to SDK signer.
     *
     * @return an SDK Signer object.
     */
    public com.silanis.esl.sdk.Signer toSDKSigner() {

        if (apiRole == null) {
            return sdkSigner;
        }

        if (apiRole.getSigners() == null || apiRole.getSigners().isEmpty()) {
            return newSignerPlaceholderFromAPIRole();
        } else {
            return newRegularSignerFromAPIRole();
        }
    }

    /**
     * Convert SDK signer to API role
     *
     * @param roleIdName
     * @return an API Role object
     */
    public Role toAPIRole(String roleIdName) {
        Role role = new Role();

        if (!sdkSigner.isPlaceholderSigner()) {
            role.addSigner(new SignerConverter(sdkSigner).toAPISigner());
        }

        role.setIndex(sdkSigner.getSigningOrder());
        role.setReassign(sdkSigner.canChangeSigner());

        if (StringUtils.isEmpty(sdkSigner.getId())) {
            role.setId(roleIdName);
            role.setName(roleIdName);
        } else {
            role.setId(sdkSigner.getId());
            role.setName(sdkSigner.getId());
        }

        if (!(sdkSigner.getMessage() == null || sdkSigner.getMessage().isEmpty())) {
            BaseMessage message = new BaseMessage();

            message.setContent(sdkSigner.getMessage());
            role.setEmailMessage(message);
        }

        role.setLocked(sdkSigner.isLocked());

        for (com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement : sdkSigner.getAttachmentRequirements()) {
            role.addAttachmentRequirement(new AttachmentRequirementConverter(attachmentRequirement).toAPIAttachmentRequirement());
        }

        String localLanguage = sdkSigner.getLocalLanguage();
        if (localLanguage != null) {
            role.putData(Role.LOCAL_LANGUAGE_DATA_KEY, localLanguage);
        }

        return role;
    }

    /**
     * Convert SDK signer to API role
     *
     * @param id
     * @param name
     * @return an API Role object
     */
    public Role toAPIRole(String id, String name) {
        Role role = new Role();

        if (!sdkSigner.isPlaceholderSigner()) {
            role.addSigner(new SignerConverter(sdkSigner).toAPISigner());
        }

        role.setIndex(sdkSigner.getSigningOrder());
        role.setReassign(sdkSigner.canChangeSigner());

        if (StringUtils.isEmpty(sdkSigner.getId())) {
            role.setId(id);
        } else {
            role.setId(sdkSigner.getId());
        }

        if (StringUtils.isEmpty(sdkSigner.getPlaceholderName())) {
            role.setName(name);
        } else {
            role.setName(sdkSigner.getPlaceholderName());
        }

        if (!(sdkSigner.getMessage() == null || sdkSigner.getMessage().isEmpty())) {
            BaseMessage message = new BaseMessage();

            message.setContent(sdkSigner.getMessage());
            role.setEmailMessage(message);
        }

        role.setLocked(sdkSigner.isLocked());

        for (com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement : sdkSigner.getAttachmentRequirements()) {
            role.addAttachmentRequirement(new AttachmentRequirementConverter(attachmentRequirement).toAPIAttachmentRequirement());
        }

        String localLanguage = sdkSigner.getLocalLanguage();
        if (localLanguage != null) {
            role.putData(Role.LOCAL_LANGUAGE_DATA_KEY, localLanguage);
        }

        return role;
    }
}
