package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.api.model.BaseMessage;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.Message;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.SenderInfo;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.SystemAlert;
import com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.silanis.esl.api.util.AdHocGroupUtils.isAdHocGroup;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerFromGroup;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerPlaceholder;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 3:39 PM
 * <p>
 * Converter between SDK and API Document Package.
 */
public class DocumentPackageConverter {

    private com.silanis.esl.api.model.Package apiPackage;
    private DocumentPackage sdkPackage;

    /**
     * Construct with API object involved in conversion.
     *
     * @param apiPackage
     */
    public DocumentPackageConverter(com.silanis.esl.api.model.Package apiPackage) {
        this.apiPackage = apiPackage;
    }

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param sdkPackage
     */
    public DocumentPackageConverter(DocumentPackage sdkPackage) {
        this.sdkPackage = sdkPackage;
    }

    /**
     * Convert from SDK to API.
     *
     * @return
     */
    public com.silanis.esl.api.model.Package toAPIPackage() {
        if (sdkPackage == null) {
            return apiPackage;
        }

        com.silanis.esl.api.model.Package result = new com.silanis.esl.api.model.Package()
                .safeSetName(sdkPackage.getName())
                .safeSetDue(sdkPackage.getExpiryDate())
                .safeSetAutocomplete(sdkPackage.getAutocomplete())
                .safeSetNotarized(sdkPackage.getNotarized())
                .safeSetTrashed(sdkPackage.getTrashed());

        if (sdkPackage.getId() != null) {
            result.setId(sdkPackage.getId().toString());
        }

        if (sdkPackage.getDescription() != null && !sdkPackage.getDescription().isEmpty()) {
            result.setDescription(sdkPackage.getDescription());
        }

        if (sdkPackage.getPackageMessage() != null && !sdkPackage.getPackageMessage().isEmpty()) {
            result.setEmailMessage(sdkPackage.getPackageMessage());
        }

        if (sdkPackage.getAttributes() != null) {
            result.setData(sdkPackage.getAttributes().toMap());
        }

        if (sdkPackage.getLanguage() != null) {
            result.setLanguage(LocaleConverter.convertToString(sdkPackage.getLanguage()));
        }

        if (sdkPackage.getSettings() != null) {
            result.setSettings(new DocumentPackageSettingsConverter(sdkPackage.getSettings()).toAPIPackageSettings());
        }

        if (sdkPackage.getSenderInfo() != null) {
            result.setSender(new SenderConverter(sdkPackage.getSenderInfo()).toAPISender());
        }

        if (sdkPackage.getVisibility() != null) {
            result.setVisibility(new VisibilityConverter(sdkPackage.getVisibility()).toAPIVisibility());
        }

        if (sdkPackage.getStatus() != null) {
            result.setStatus(new PackageStatusConverter(sdkPackage.getStatus()).toAPIPackageStatus());
        }

        result.safeSetTimezoneId(sdkPackage.getTimezoneId());

        List<Role> roles =
                Lists.newArrayList(Iterables.transform(sdkPackage.getSigners(), new Function<Signer, Role>() {
                    int signerCount = 1;

                    @Override
                    public Role apply(final Signer input) {
                        String id;
                        if (input.getId() != null) {
                            id = input.getId();
                        } else if (input.getGroupId() == null) {
                            id = "role" + signerCount;
                        } else {
                            id = input.getGroupId().getId();
                        }

                        Role role = new Role()
                                .setName(input.getId() == null ? "signer" + signerCount : input.getId())
                                .addSigner(new SignerConverter(input).toAPISigner())
                                .setIndex(input.getSigningOrder())
                                .setReassign(input.canChangeSigner())
                                .setLocked(input.isLocked())
                                .setId(id);

                        signerCount++;

                        if (input.getMessage() != null) {
                            role.setEmailMessage(new BaseMessage().setContent(input.getMessage()));
                        }

                        for (AttachmentRequirement attachmentRequirement : input.getAttachmentRequirements()) {
                            role.addAttachmentRequirement(new AttachmentRequirementConverter(attachmentRequirement).toAPIAttachmentRequirement());
                        }

                        String localLanguage = input.getLocalLanguage();
                        if (localLanguage != null) {
                            role.putData(Role.LOCAL_LANGUAGE_DATA_KEY, localLanguage);
                        }

                        return role;
                    }
                }));

        result.safeSetRoles(roles);

        for (Signer signer : sdkPackage.getPlaceholders()) {
            Role role = new SignerConverter(signer).toAPIRole(signer.getId(), signer.getPlaceholderName());
            role.setIndex(signer.getSigningOrder());
            result.addRole(role);
        }

        if (sdkPackage.getConditions() != null) {
            ArrayList<com.silanis.esl.api.model.FieldCondition> conditions = new ArrayList<com.silanis.esl.api.model.FieldCondition>();
            for (FieldCondition condition : sdkPackage.getConditions()) {
                conditions.add(new FieldConditionConverter(condition).toAPIFieldCondition());
            }
            result.setConditions(conditions);
        }

        return result;
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public DocumentPackage toSDKPackage() {

        if (apiPackage == null) {
            return sdkPackage;
        }

        PackageBuilder packageBuilder = PackageBuilder.newPackageNamed(apiPackage.getName());

        packageBuilder.withID(new PackageId(apiPackage.getId()));
        packageBuilder.autocomplete(apiPackage.evalAutocomplete());
        packageBuilder.expiresAt(apiPackage.getDue());
        packageBuilder.withStatus(new PackageStatusConverter(apiPackage.getStatus()).toSDKPackageStatus());

        if (apiPackage.getDescription() != null) {
            packageBuilder.describedAs(apiPackage.getDescription());
        }

        if (apiPackage.getEmailMessage() != null) {
            packageBuilder.withEmailMessage(apiPackage.getEmailMessage());
        }

        if (apiPackage.getNotarized() != null) {
            packageBuilder.withNotarized(apiPackage.getNotarized());
        }

        if (apiPackage.getTrashed() != null) {
            packageBuilder.withTrashed(apiPackage.getTrashed());
        }

        if (apiPackage.getLanguage() != null) {
            packageBuilder.withLanguage(toSdkLanguage(apiPackage.getLanguage()));
        }

        if (apiPackage.getSettings() != null) {
            packageBuilder.withSettings(new DocumentPackageSettingsConverter(apiPackage.getSettings()).toSDKPackageSettings());
        }

        if (apiPackage.getSender() != null) {
            packageBuilder.withSenderInfo(new SenderConverter(apiPackage.getSender()).toSDKSenderInfo());
        }

        if (apiPackage.getVisibility() != null) {
            packageBuilder.withVisibility(new VisibilityConverter(apiPackage.getVisibility()).toSDKVisibility());
        }

        if (apiPackage.getTimezoneId() != null) {
            packageBuilder.withTimezoneId(apiPackage.getTimezoneId());
        }
        packageBuilder.withAttributes(new DocumentPackageAttributesBuilder(apiPackage.getData()).build());

        for (Role role : apiPackage.getRoles()) {

            if (role.getSigners().isEmpty()) {
                packageBuilder.withSigner(newSignerPlaceholder(new Placeholder(role.getId(), role.getName(), role.getIndex())));
            } else if (isGroupRole(role)) {
                packageBuilder.withSigner(
                    newSignerFromGroup(new GroupId(role.getSigners().get(0).getGroup().getId())));
            } else {
                packageBuilder.withSigner(new SignerConverter(role).toSDKSigner());

                // The custom sender information is stored in the role.signer object.
                if ("SENDER".equals(role.getType())) {
                    // Override sender info with the customized ones.
                    SenderInfo senderInfo = new SenderInfo();

                    com.silanis.esl.api.model.Signer signer = role.getSigners().get(0);
                    senderInfo.setFirstName(signer.getFirstName());
                    senderInfo.setLastName(signer.getLastName());
                    senderInfo.setTitle(signer.getTitle());
                    senderInfo.setCompany(signer.getCompany());
                    senderInfo.setEmail(signer.getEmail());

                    packageBuilder.withSenderInfo(senderInfo);
                }
            }
        }

        for (com.silanis.esl.api.model.Document apiDocument : apiPackage.getDocuments()) {
            Document document = new DocumentConverter(apiDocument, apiPackage).toSDKDocument();
            packageBuilder.withDocument(document);
        }

        DocumentPackage documentPackage = packageBuilder.build();

        ArrayList<Message> messages = new ArrayList<Message>();
        for (com.silanis.esl.api.model.Message apiMessage : apiPackage.getMessages()) {
            messages.add(new MessageConverter(apiMessage).toSDKMessage());
        }
        documentPackage.setMessages(messages);
        if (apiPackage.getUpdated() != null) {
            documentPackage.setUpdatedDate(apiPackage.getUpdated());
        }
        if (apiPackage.getCreated() != null) {
            documentPackage.setCreatedDate(apiPackage.getCreated());
        }
        if (apiPackage.getConditions() != null) {
            ArrayList<FieldCondition> conditions = new ArrayList<FieldCondition>();
            for (com.silanis.esl.api.model.FieldCondition condition : apiPackage.getConditions()) {
                conditions.add(new FieldConditionConverter(condition).toSDKFieldCondition());
            }
            documentPackage.setConditions(conditions);
        }
        if (apiPackage.getAlerts() != null) {
            ArrayList<SystemAlert> alerts = new ArrayList<>();
            for (com.silanis.esl.api.model.SystemAlert apiAlert : apiPackage.getAlerts()) {
                alerts.add(new SystemAlertConverter(apiAlert).toSDKSystemAlert());
            }
            documentPackage.setAlerts(alerts);
        }
        return documentPackage;
    }

    /**
     * Determines if the given role is a group role.
     * A group role is defined as a role that is not an ad-hoc group and whose first signer has a non-null group.
     *
     * @param role the Role to check
     * @return true if the role is a group role, false otherwise
     */
    private static boolean isGroupRole(final Role role) {
        return (!isAdHocGroup(role)) && role.getSigners().get(0).getGroup() != null;
    }

    private Locale toSdkLanguage(String apiLanguage) {
        if (apiLanguage == null)
            return null;

        String[] strArray = apiLanguage.split("-");

        if (strArray.length == 2) {
            return new Locale(strArray[0], strArray[1]);
        } else {
            return new Locale(strArray[0]);
        }
    }

}
