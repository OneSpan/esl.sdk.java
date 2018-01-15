package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.External;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldStyle;
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
        documentBuilder.withData( apiDocument.getData() );
        for ( Approval apiApproval : apiDocument.getApprovals() ) {
            documentBuilder.withSignature( new SignatureConverter( apiApproval, apiPackage ).toSDKSignature());
        }

        for ( com.silanis.esl.api.model.Field apiField : apiDocument.getFields() ) {
            Field sdkField = new FieldConverter(apiField).toSDKField();

            if (sdkField.getStyle() != FieldStyle.BOUND_QRCODE) {
                documentBuilder.withInjectedField(sdkField);
            } else {
                documentBuilder.withQRCode(sdkField);
            }
        }

        Document document = documentBuilder.build();
        if ( apiDocument.getPages() != null && !apiDocument.getPages().isEmpty() ) {
            document.setNumberOfPages(apiDocument.getPages().size());
        }

        return document;
    }
    /**
     * Convert from SDK document to API Document.
     *
     * @return API Document.
     */
    public com.silanis.esl.api.model.Document toAPIDocument( final com.silanis.esl.api.model.Package createdPackage ) {

        if (sdkDocument == null) {
            return apiDocument;
        }

        com.silanis.esl.api.model.Document result = new com.silanis.esl.api.model.Document()
                .setIndex(sdkDocument.getIndex())
                .setExtract(sdkDocument.isExtract())
                .setData(sdkDocument.getData())
                .setName(sdkDocument.getName());

        if(sdkDocument.getId() != null) {
            result.safeSetId(sdkDocument.getId().getId());
        }
        result.safeSetDescription(sdkDocument.getDescription());

        if( sdkDocument.getExternal() != null){
            External external =  new ExternalConverter(sdkDocument.getExternal()).toAPIExternal();
            result.setExternal(external);
        }

        result.safeSetApprovals(Lists.newArrayList(Iterables.transform(sdkDocument.getSignatures(), new Function<Signature, Approval>() {
            @Override
            public Approval apply(final Signature input) {
                Approval approval = new SignatureConverter(input).toAPIApproval();
                if( input.isPlaceholderSignature() ){
                    approval.setRole(input.getRoleId().getId());
                } else if ( input.isGroupSignature() ) {
                    approval.setRole(findRoleIdForGroup( input.getGroupId(), createdPackage ) );
                } else {
                    approval.setRole(findRoleIdForSignature( input.getSignerEmail(), createdPackage ) );
                }

                return approval;
            }
        })));

        for (com.silanis.esl.sdk.Field field : sdkDocument.getInjectedFields() ) {
            result.addField(ConversionService.convert(field));
        }

        for (com.silanis.esl.sdk.Field field : sdkDocument.getQrCodes()) {
            result.addField(ConversionService.convert(field));
        }

        return result;
    }

    public com.silanis.esl.api.model.Document toAPIDocumentMetadata(){

        if (sdkDocument == null) {
            throw new IllegalArgumentException("No SDK document available to perform conversion");
        }

        com.silanis.esl.api.model.Document result = new com.silanis.esl.api.model.Document()
                .safeSetIndex(sdkDocument.getIndex())
                .safeSetExtract(sdkDocument.isExtract())
                .safeSetName(sdkDocument.getName())
                .safeSetExternal(new ExternalConverter(sdkDocument.getExternal()).toAPIExternal())
                .safeSetDescription(sdkDocument.getDescription());

        if( sdkDocument.getId() != null){
            result.setId(sdkDocument.getId().toString());
        }

        return result;
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
