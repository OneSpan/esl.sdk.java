package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.GroupId;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.DocumentBuilder;

/**
 * User: jessica
 * Date: 21/11/13
 * Time: 12:13 PM
 *
 * Converter between SDK Document and API Document.
 *
 */
public class DocumentConverter {

    private com.silanis.esl.api.model.Document apiDocument = null;
    private com.silanis.esl.api.model.Package apiPackage=null;
    private com.silanis.esl.sdk.Document sdkDocument=null;

    /**
     * Construct with API objects.
     */
    public DocumentConverter( com.silanis.esl.api.model.Document apiDocument, com.silanis.esl.api.model.Package apiPackage ) {
        this.apiDocument = apiDocument;
        this.apiPackage = apiPackage;
    }

    /**
     * Construct with SDK object.
     */
    public DocumentConverter( com.silanis.esl.sdk.Document sdkDocument ) {
        this.sdkDocument = sdkDocument;
    }


    /**
     * Convert from API document to SDK Document.
     *
     * @return SDK Document.
     */
    public com.silanis.esl.sdk.Document toSDKDocument() {

        if (apiDocument == null) {
            return sdkDocument;
        }

        DocumentBuilder documentBuilder = DocumentBuilder.newDocumentWithName(apiDocument.getName());
        documentBuilder.withId( apiDocument.getId() );
        if ( apiDocument.getIndex() != null ) {
            documentBuilder.atIndex( apiDocument.getIndex() );
        }

        documentBuilder.withDescription( apiDocument.getDescription() );
        documentBuilder.withExternal(new ExternalConverter(apiDocument.getExternal()).toSDKExternal());
        for ( Approval apiApproval : apiDocument.getApprovals() ) {
            documentBuilder.withSignature( new SignatureConverter( apiApproval, apiPackage ).toSDKSignature());
        }

        for ( com.silanis.esl.api.model.Field apiField : apiDocument.getFields() ) {
            documentBuilder.withInjectedField( new FieldConverter(apiField).toSDKField() );
        }

        return documentBuilder.build();
    }
    /**
     * Convert from SDK document to API Document.
     *
     * @return API Document.
     */
    public com.silanis.esl.api.model.Document toAPIDocument( com.silanis.esl.api.model.Package createdPackage ) {

        if (sdkDocument == null) {
            return apiDocument;
        }

        com.silanis.esl.api.model.Document resultAPIDocument = new com.silanis.esl.api.model.Document()
                .setIndex(sdkDocument.getIndex())
                .setExtract(sdkDocument.isExtract())
                .setName(sdkDocument.getName());

        if(sdkDocument.getExternal() != null){
            resultAPIDocument.setExternal(new ExternalConverter(sdkDocument.getExternal()).toAPIExternal());
        }

        if ( sdkDocument.getId() != null ) {
            resultAPIDocument.setId(sdkDocument.getId().getId());
        }

        if ( sdkDocument.getDescription() != null ) {
            resultAPIDocument.setDescription(sdkDocument.getDescription());
        }

        for ( Signature signature : sdkDocument.getSignatures() ) {

            Approval approval = new SignatureConverter(signature).toAPIApproval();
            if( signature.isPlaceholderSignature() ){
                approval.setRole(signature.getRoleId().getId());
            }
            else if ( signature.isGroupSignature() ) {
                approval.setRole(findRoleIdForGroup( signature.getGroupId(), createdPackage ) );
            } else {
                approval.setRole(findRoleIdForSignature( signature.getSignerEmail(), createdPackage ) );
            }
            resultAPIDocument.addApproval(approval);
        }

        for (com.silanis.esl.sdk.Field field : sdkDocument.getInjectedFields() ) {
            resultAPIDocument.addField(ConversionService.convert(field));
        }

        return resultAPIDocument;
    }

    public com.silanis.esl.api.model.Document toAPIDocumentMetadata(){

        if (sdkDocument == null) {
            throw new IllegalArgumentException("No SDK document available to perform conversion");
        }

        com.silanis.esl.api.model.Document resultAPIDocument = new com.silanis.esl.api.model.Document()
                .setIndex(sdkDocument.getIndex())
                .setExtract(sdkDocument.isExtract())
                .setName(sdkDocument.getName());

        if( sdkDocument.getId() != null){
            resultAPIDocument.setId( sdkDocument.getId().toString() );
        }

        if( sdkDocument.getDescription() != null){
            resultAPIDocument.setDescription( sdkDocument.getDescription() );
        }

        return resultAPIDocument;
    }

    /**
     * Find the role ID for a specified group in a specified package.
     * @param groupId
     * @param createdPackage
     *
     * @return role id
     */
    private String findRoleIdForGroup( GroupId groupId, Package createdPackage ) {
        for ( Role role : createdPackage.getRoles() ) {
            if ( !role.getSigners().isEmpty() ) {
                if ( role.getSigners().get( 0 ).getGroup() != null ) {
                    if ( role.getSigners().get( 0 ).getGroup().getId().equals( groupId.getId() ) ) {
                        return role.getId();
                    }
                }
            }
        }
        throw new IllegalStateException( "No role found for signer group " + groupId.getId() );
    }

    /**
     * Find the Role ID for the signer having a specified email in a specified package.
     *
     * @param signerEmail
     * @param createdPackage
     * @return Role ID.
     */
    private String findRoleIdForSignature( String signerEmail, Package createdPackage ) {
        for ( Role role : createdPackage.getRoles() ) {
            if ( !role.getSigners().isEmpty() ) {
                if ( role.getSigners().get( 0 ).getEmail() != null ) {
                    if ( signerEmail.equalsIgnoreCase( role.getSigners().get( 0 ).getEmail() ) ) {
                        return role.getId();
                    }
                }
            }
        }

        throw new IllegalStateException( "No role found for signer email " + signerEmail );
    }

}
