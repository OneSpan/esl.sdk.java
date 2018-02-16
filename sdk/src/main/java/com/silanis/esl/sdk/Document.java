package com.silanis.esl.sdk;

import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.*;

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
    private int numberOfPages;
    private boolean extract;
    private Boolean tagged;
    private Set<String> extractionTypes = Sets.newHashSet();
    private DocumentId id;
    private List<Field> injectedFields = new ArrayList<Field>();
    private List<Field> qrCodes = new ArrayList<Field>();
    private String description;
    private External external;
    private Map<String, Object> data;

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
     * <p>Accessor method used to retrieve the number of pages of the document</p>
     *
     * @return	the number of pages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * <p>Accessor method used to specify the number of pages of the document.</p>
     *
     * @param numberOfPages	the document signing order
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * 
     * 
     * @param extract
     */
    public void setExtraction( boolean extract ) {
        this.extract = extract;
    }

    public boolean isExtract() {
        return extract;
    }

    public Boolean isTagged() {
        return tagged;
    }

    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    public Set<String> getExtractionTypes() {
        return extractionTypes;
    }

    public void setExtractionTypes(Set<String> extractionTypes) {
        this.extractionTypes = extractionTypes;
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

    /**
     * <p>Accessor method used to get the information from the external provider</p>
     *
     * @return the external provider
     */
    public External getExternal(){return external;}

    /**
     * <p>Accessor method used to set the document external provider</p>
     *
     * @param external the external provider of the document null if none
     */
    public void setExternal(External external){this.external = external;}

    public void addSignatures(List<Signature> signatures) {
        this.signatures.addAll(signatures);
    }

    public void addInjectedFields( List<Field> fields ) {
        this.injectedFields.addAll( fields );
    }

    public List<Field> getInjectedFields() {
        return injectedFields;
    }

    public void addQRCodes(List<Field> fields) {
        this.qrCodes.addAll(fields);
    }
    public List<Field> getQrCodes() {
        return qrCodes;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}