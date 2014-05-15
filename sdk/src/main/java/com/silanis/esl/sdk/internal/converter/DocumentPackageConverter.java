package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.builder.*;

import java.util.Locale;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 3:39 PM
 *
 * Converter between SDK and API Document Package.
 */
public class DocumentPackageConverter {

    private com.silanis.esl.api.model.Package apiPackage;
    private com.silanis.esl.sdk.DocumentPackage sdkPackage;

    /**
     * Construct with API object involved in conversion.
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
    public DocumentPackageConverter(com.silanis.esl.sdk.DocumentPackage sdkPackage) {
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

        com.silanis.esl.api.model.Package apiPackageToCreate = new com.silanis.esl.api.model.Package()
                .setName(sdkPackage.getName())
                .setDue(sdkPackage.getExpiryDate())
                .setAutocomplete(sdkPackage.getAutocomplete());


        if ( sdkPackage.getDescription() != null && !sdkPackage.getDescription().isEmpty() ) {
            apiPackageToCreate.setDescription(sdkPackage.getDescription());
        }

        if ( sdkPackage.getPackageMessage() != null && !sdkPackage.getPackageMessage().isEmpty() ) {
            apiPackageToCreate.setEmailMessage(sdkPackage.getPackageMessage());
        }

        if ( sdkPackage.getAttributes() != null ) {
            apiPackageToCreate.setData(sdkPackage.getAttributes().toMap());
        }

        if ( sdkPackage.getLanguage() != null ) {
            apiPackageToCreate.setLanguage(sdkPackage.getLanguage().getLanguage());
        }

        if ( sdkPackage.getSettings() != null ) {
            apiPackageToCreate.setSettings(new DocumentPackageSettingsConverter(sdkPackage.getSettings()).toAPIPackageSettings());
        }

        if ( sdkPackage.getSenderInfo() != null ) {
            apiPackageToCreate.setSender(new SenderConverter(sdkPackage.getSenderInfo()).toAPISender());
        }

        int signerCount = 1;
        for ( com.silanis.esl.sdk.Signer signer : sdkPackage.getSigners().values() ) {
            Role role = new Role()
                    .setName( signer.getId() == null ? "signer" + signerCount : signer.getId() )
                    .addSigner( new SignerConverter(signer).toAPISigner() )
                    .setIndex( signer.getSigningOrder() )
                    .setReassign( signer.canChangeSigner() )
                    .setId( signer.getId() == null ? "role" + signerCount : signer.getId() );

            signerCount++;

            if ( signer.getMessage() != null ) {
                role.setEmailMessage( new BaseMessage().setContent( signer.getMessage() ) );
            }

            for (com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement : signer.getAttachmentRequirement().values()) {
                role.addAttachmentRequirement(new AttachmentRequirementConverter(attachmentRequirement).toAPIAttachmentRequirement());
            }

            apiPackageToCreate.addRole(role);
        }

        return apiPackageToCreate;
    }

    /**
     * Convert from API to SDK.
     *
     * @return
     */
    public com.silanis.esl.sdk.DocumentPackage toSDKPackage() {

        if (apiPackage == null) {
            return sdkPackage;
        }

        PackageBuilder packageBuilder = PackageBuilder.newPackageNamed(apiPackage.getName());

        packageBuilder.withID(new PackageId(apiPackage.getId()));
        packageBuilder.autocomplete(apiPackage.evalAutocomplete());
        packageBuilder.expiresAt( apiPackage.getDue());
        packageBuilder.withStatus( apiPackage.getStatus());

        if (apiPackage.getDescription() != null) {
            packageBuilder.describedAs(apiPackage.getDescription());
        }

        if (apiPackage.getEmailMessage() != null) {
            packageBuilder.withEmailMessage(apiPackage.getEmailMessage());
        }

        if (apiPackage.getLanguage() != null) {
            packageBuilder.withLanguage(new Locale(apiPackage.getLanguage()));
        }

        if (apiPackage.getSettings() != null) {
            packageBuilder.withSettings(new DocumentPackageSettingsConverter(apiPackage.getSettings()).toSDKPackageSettings());
        }

        if (apiPackage.getSender() != null) {
            packageBuilder.withSenderInfo(new SenderConverter(apiPackage.getSender()).toSDKSender());
        }
        packageBuilder.withAttributes( new DocumentPackageAttributesBuilder(apiPackage.getData()).build());

        for ( com.silanis.esl.api.model.Role role : apiPackage.getRoles() ) {
            if ( role.getSigners().isEmpty() ) {
                continue;
            }

            if ( role.getSigners().get( 0 ).getGroup() != null ) {
                packageBuilder.withSigner(SignerBuilder.newSignerFromGroup(new GroupId(role.getSigners().get(0).getGroup().getId())));
            } else {
                packageBuilder.withSigner( new SignerConverter(role).toSDKSigner() );

                // The custom sender information is stored in the role.signer object.
                if (role.getType() == RoleType.SENDER) {
                    // Override sender info with the customized ones.
                    com.silanis.esl.sdk.SenderInfo senderInfo = new com.silanis.esl.sdk.SenderInfo();

                    com.silanis.esl.api.model.Signer signer = role.getSigners().get(0);
                    senderInfo.setFirstName(signer.getFirstName());
                    senderInfo.setLastName(signer.getLastName());
                    senderInfo.setTitle(signer.getTitle());
                    senderInfo.setCompany(signer.getCompany());
                    packageBuilder.withSenderInfo(senderInfo);
                }
            }
        }

        for ( com.silanis.esl.api.model.Document apiDocument : apiPackage.getDocuments() ) {
            Document document = new DocumentConverter(apiDocument, apiPackage).toSDKDocument();
            packageBuilder.withDocument( document );
        }

        return packageBuilder.build();
    }

}
