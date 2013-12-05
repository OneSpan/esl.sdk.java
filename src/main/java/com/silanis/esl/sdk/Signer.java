package com.silanis.esl.sdk;

import java.io.Serializable;
import java.util.List;

/**
 * <p>The Signer class contains all the information about an user that is supposed to sign a document.</p>
 */
public class Signer implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String email;
    private final GroupId groupId;
    private final String firstName;
    private final String lastName;
    private final Authentication authentication;
    private int signingOrder;
    private String title;
    private String company;
    private boolean canChangeSigner;
    private String message;
    private boolean deliverSignedDocumentsByEmail;
    private String id;
    private boolean locked;

    /**
     * <p>The constructor of the Signer class.</p> 
     * 
     * @param email	the email address
     * @param firstName	the first name
     * @param lastName	the last name
     * @param authentication the authentication used by the signer to join to a eSL signing ceremony 
     */
    public Signer(String email, String firstName, String lastName, Authentication authentication) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authentication = authentication;
        this.groupId = null;
    }

    public Signer(GroupId groupId) {
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.authentication = new Authentication(AuthenticationMethod.EMAIL);
        this.groupId = groupId;
    }

    public boolean isGroupSigner() {
        return groupId != null;
    }

    /**
     * Accessor method used to retrieve the signer's email
     * 
     * @return the signer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Accessor method used to retrieve the signer's first name
     * 
     * @return	the signer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Accessor method used to retrieve the signer's last name
     * 
     * @return	the signer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Accessor method used to set the signer's signing order inside the document
     * 
     * @param signingOrder	the signing order inside the document
     */
    public void setSigningOrder( int signingOrder ) {
        this.signingOrder = signingOrder;
    }

    /**
     * Accessor method used to retrieve the signer's signing order inside the document 
     * 
     * @return	the signer's signing order
     */
    public int getSigningOrder() {
        return signingOrder;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * Accessor method used to retrieve the authentication method used by the signer
     * 
     * @return	the authentication method
     */
    public AuthenticationMethod getAuthenticationMethod() {
        return authentication.getMethod();
    }

    /**
     * Accessor method used to retrieve the challenge question
     * 
     * @return	the challenge question
     */
    public List<Challenge> getChallengeQuestions() {
        return authentication.getChallenges();
    }

    /**
     * Accessor method used to retrieve the signer's phone number
     * 
     * @return	the phone number
     */
    public String getPhoneNumber() {
        return authentication.getPhoneNumber();
    }

    /**
     * Accessor method used to retrieve the signer's title
     * 
     * @return	the signer's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor method used to retrieve the signer's company
     * 
     * @return	the signer's company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Accessor method used to set the signer's title
     * 
     * @param title	the signer's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method used to set the signer's company
     * 
     * @param company	the signer's company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 
     * Accessor method used to set the 
     * 
     * @param canChangeSigner
     */
    public void setCanChangeSigner(boolean canChangeSigner) {
        this.canChangeSigner = canChangeSigner;
    }

    /**
     * <p>It tells if a signer can reassign or not the signing request to another signer.</p>
     * 
     * @return	if true it means that the signer can reassign the signing request to another signer
     */
    public boolean canChangeSigner() {
        return canChangeSigner;
    }

    /**
     * 
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    /**
     * <p>Accessor method used to set the deliverSignedDocumentsByEmail.</p>
     * <p>If a Signer instance has this property set to true it means that the documents once signed
     * will be delivered to the signer by email.</p>
     * 
     * @param deliverSignedDocumentsByEmail
     */
    public void setDeliverSignedDocumentsByEmail(boolean deliverSignedDocumentsByEmail) {
        this.deliverSignedDocumentsByEmail = deliverSignedDocumentsByEmail;
    }

    /**
     * <p>Provides the value set for the deliverSignedDocumentsByEmail property</p>
     * 
     * @return the value set for the deliverSignedDocumentsByEmail property
     */
    public boolean isDeliverSignedDocumentsByEmail() {
        return deliverSignedDocumentsByEmail;
    }

    /**
     * <p>Accessor method used to set the signer's ID.</p>
     * <p>This ID should be uniquely defined at the document package level</p>
     * 
     * @param id	the signer's ID
     */
    public void setId( String id ) {
        this.id = id;
    }

    /**
     * Accessor method used to retrieve the signer's ID
     * 
     * @return the signer's ID
     */
    public String getId() {
        return id;
    }

    public GroupId getGroupId() {
        return this.groupId;
    }

    public void setLocked( boolean locked ) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }
}