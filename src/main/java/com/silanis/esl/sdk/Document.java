package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Package;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.internal.converter.ConversionService;
import com.silanis.esl.sdk.internal.converter.SignatureConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>The document class contains data describing pages, signatures and their associated data and layout.</>
 * <p>Allowed file types for uploaded documents are Adobe PDF (.pdf), a Microsoft Word (.doc or.docx), an IBM Symphony (.odt),</p>
 * <p>or an Open Document (.odt) file.</p>
 * <p>Any document that is not having the .pdf extension will be automatically converted to Adobe pdf based on its extension.</p>
 * <p>This conversion is achieved during the package creation.</p>
 */
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private final Collection<Signature> signatures = new ArrayList<Signature>();
    private byte[] content;
    private String fileName;
    private int index;
    private boolean extract;
    private DocumentId id;
    private List<Field> injectedFields = new ArrayList<Field>();
    private String description;

    com.silanis.esl.api.model.Document toAPIDocument( Package createdPackage ) {
        com.silanis.esl.api.model.Document doc = new com.silanis.esl.api.model.Document()
                .setIndex(index)
                .setExtract(extract)
                .setName(name);

        if ( id != null ) {
            doc.setId( id.getId() );
        }

        if ( description != null ) {
            doc.setDescription( description );
        }

        for ( Signature signature : signatures ) {

            Approval approval = new SignatureConverter(signature).toAPIApproval();

            if ( signature.isGroupSignature() ) {
                approval.setRole(findRoleIdForGroup( signature.getGroupId(), createdPackage ) );
            } else {
                approval.setRole(findRoleIdForSignature( signature.getSignerEmail(), createdPackage ) );
            }
            doc.addApproval(approval);
        }

        for (Field field : injectedFields ) {
            doc.addField(ConversionService.convert(field));
        }

        return doc;
    }

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

    private String findRoleIdForSignature( String signerEmail, Package createdPackage ) {
        for ( Role role : createdPackage.getRoles() ) {
            if ( !role.getSigners().isEmpty() ) {
                if ( role.getSigners().get( 0 ).getEmail() != null ) {
                    if ( signerEmail.equals( role.getSigners().get( 0 ).getEmail() ) ) {
                        return role.getId();
                    }
                }
            }
        }

        throw new IllegalStateException( "No role found for signer email " + signerEmail );
    }

    /**
     * <p>Accessor method used to retrieve the file name</p>
     * 
     * @return	the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 
     * <p>Accessor method used to set the file name</p>
     * 
     * @param fileName	the file name
     */
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    /**
     * 
     * <p>Accessor method used to retrieve the content of a document</p>
     * 
     * @return	the document content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Accessor method used to set the content of a document
     * 
     * @param content	the document content
     */
    public void setContent( byte[] content ) {
        this.content = content;
    }

    /**
     * <p>Accessor method to set the name of a document</p>
     * 
     * @param name	the document name
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * <p>Accessor method used to retrieve the name of a document</p>
     * 
     * @return	the document name
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Accessor method used to retrieve the document signatures</p>
     * 
     * @return	the document signatures
     */
    public Collection<Signature> getSignatures() {
        return signatures;
    }

    /**
     * <p>Accessor method used to specify the order that document must be signed in.</p>
     * <p>So, a document with an index set to 1 will be signed before a document with an index set to 2, and so on.</p>
     * 
     * @param position	the document signing order
     */
    public void setIndex(int position) {
        this.index = position;
    }

    /**
     * <p>Accessor method used to retrieve the document signing order</p>
     * 
     * @return	the document signing order
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * 
     * @param extract
     */
    public void setExtraction( boolean extract ) {
        this.extract = extract;
    }

    /**
     * 
     * <p>Accessor method used to set the document ID</p>
     * 
     * @param id	the ID of the document
     */
    public void setId( DocumentId id ) {
        this.id = id;
    }

    /**
     * <p>Accessor method used to get the ID of the document</p>
     * 
     * @return	the ID of the document
     */
    public DocumentId getId() {
        return id;
    }

    public void addSignatures(List<Signature> signatures) {
        this.signatures.addAll(signatures);
    }

    public void addInjectedFields( List<Field> fields ) {
        this.injectedFields.addAll( fields );
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}