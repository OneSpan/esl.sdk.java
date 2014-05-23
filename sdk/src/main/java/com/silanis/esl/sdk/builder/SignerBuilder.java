package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>The SignerBuilder class is a convenient class used to create and customize a signer.</p>
 */
final public class SignerBuilder {

    public static final int DEFAULT_SIGNING_ORDER = 0;

    private final String email;
    private final GroupId groupId;
    private String firstName;
    private String lastName;
    private int signingOrder = DEFAULT_SIGNING_ORDER;
    private AuthenticationBuilder authenticationBuilder = new AuthenticationBuilder();
    private Authentication authentication = null;
    private String title = "";
    private String company = "";
    private boolean canChangeSigner;
    private String message = "";
    private boolean deliverSignedDocumentsByEmail;
    private String id = null;
    private boolean locked = false;
    private Map<String, AttachmentRequirement> attachments = new HashMap<String, AttachmentRequirement>();

    /**
     * <p>The constructor of the SignerBuilderClass.</p>
     *
     * @param email the signer's email.
     */
    private SignerBuilder(String email) {
        if (email == null) {
            this.email = email;
        } else {
            this.email = email.toLowerCase();
        }
        this.groupId = null;
    }

    private SignerBuilder(GroupId groupId) {
        this.email = null;
        this.groupId = groupId;
    }

    private SignerBuilder(Placeholder placeholder){
        this.email = null;
        this.groupId = null;
        this.id = placeholder.getId();
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     *
     * @param email the signer's email
     * @return a SignerBuilder object
     */
    public static SignerBuilder newSignerWithEmail(String email) {
        return new SignerBuilder(email);
    }

    public static SignerBuilder newSignerFromGroup(GroupId groupId) {
        return new SignerBuilder(groupId);
    }

    public static SignerBuilder newSignerPlaceholder(Placeholder roleId) {
        return new SignerBuilder(roleId);
    }

    /**
     * Sets the ID of the signer.
     *
     * @param id the signer's ID
     * @return the signer builder itself
     */
    public SignerBuilder withCustomId(String id) {
        this.id = id;
        return this;
    }

    public SignerBuilder replacing( Placeholder placeholder){
        this.id = placeholder.getId();
        return this;
    }

    /**
     * Sets the signer's first name
     *
     * @param firstName the signer's first name
     * @return the signer builder itself
     */
    public SignerBuilder withFirstName(String firstName) {
        Asserts.genericAssert(!isGroupSigner(), "first name can not be set for a group signer");
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets the signer's last name
     *
     * @param lastName the signer's last name
     * @return the signer builder itself
     */
    public SignerBuilder withLastName(String lastName) {
        Asserts.genericAssert(!isGroupSigner(), "last name can not be set for a group signer");
        this.lastName = lastName;
        return this;
    }

    /**
     * Sets the signing order.
     *
     * @param signingOrder a value greater than zero
     * @return the signer builder itself
     */
    public SignerBuilder signingOrder(int signingOrder) {
        this.signingOrder = signingOrder;
        return this;
    }

    private Signer buildGroupSigner(){
        Signer result = new Signer(groupId);

        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setId(id);
        result.setLocked(locked);
        result.setAttachmentRequirement(attachments);

        return result;
    }

    private Signer buildPlaceholderSigner(){
        Signer result = new Signer(id);

        Asserts.notNullOrEmpty( id, "No placeholder set for this signer!" );
        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setLocked(locked);
        result.setAttachmentRequirement(attachments);
        return result;
    }

    private Signer buildRegularSigner(){
        if (authentication == null) {
            authentication = authenticationBuilder.build();
        }

        Signer result;

        Asserts.notNullOrEmpty(firstName, "first name");
        Asserts.notNullOrEmpty(lastName, "last name");
        result = new Signer(email, firstName, lastName, authentication);
        result.setTitle(title);
        result.setCompany(company);
        result.setDeliverSignedDocumentsByEmail(deliverSignedDocumentsByEmail);

        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setId(id);
        result.setLocked(locked);
        result.setAttachmentRequirement(attachments);

        return result;
    }

    /**
     * Builds the actual signer object
     *
     * @return the signer object
     */
    public Signer build() {

        Signer signer;
        if (isGroupSigner()) {
            signer = buildGroupSigner();
        }
        else if (isPlaceholder()){
            signer = buildPlaceholderSigner();
        }
        else{
            signer = buildRegularSigner();
        }
        return signer;

    }

    /**
     * <p>Sets the signer's authentication type to CHALLENGE.</p>
     *
     * @param challengeBuilder the challenge builder
     * @return the signer builder object itself
     */
    public SignerBuilder challengedWithQuestions(ChallengeBuilder challengeBuilder) {
        this.authenticationBuilder = challengeBuilder;
        return this;
    }

    /**
     * <p>Sets the signer's authentication type to SMS.</p>
     *
     * @param phoneNumber
     * @return the signer builder object itself
     */
    public SignerBuilder withSmsSentTo(String phoneNumber) {
        this.authenticationBuilder = new SMSAuthenticationBuilder(phoneNumber);
        return this;
    }

    /**
     * Sets the Signer's authentication.
     *
     * @param authentication
     * @return
     */
    public SignerBuilder withAuthentication(Authentication authentication) {
        this.authentication = authentication;
        return this;
    }

    /**
     * <p>Sets the signer's title.</p>
     *
     * @param title the signer's title
     * @return the signer builder object itself
     */
    public SignerBuilder withTitle(String title) {
        Asserts.genericAssert(!isGroupSigner(), "title can not be set for a group signer");
        this.title = title;
        return this;
    }

    /**
     * <p>Sets the signer's company name.</p>
     *
     * @param company the signer's company name
     * @return the signer builder object itself
     */
    public SignerBuilder withCompany(String company) {
        Asserts.genericAssert(!isGroupSigner(), "company can not be set for a group signer");
        this.company = company;
        return this;
    }

    /**
     * <p>Sets the canChangeSigner property to true.</p>
     *
     * @return the signer builder object itself
     */
    public SignerBuilder canChangeSigner() {
        canChangeSigner = true;
        return this;
    }

    /**
     * Sets the signer's email message
     *
     * @param message the message the signer will receive in the email invitation to start the signing ceremony
     * @return the signet builder object itself
     */
    public SignerBuilder withEmailMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * <p>Sets the deliverSignedDocumentsByEmail to true.</p>
     *
     * @return the signer builder object itself
     */
    public SignerBuilder deliverSignedDocumentsByEmail() {
        deliverSignedDocumentsByEmail = true;
        return this;
    }

    public SignerBuilder lock() {
        locked = true;
        return this;
    }

    /**
     * @param roleId
     * @return
     * @deprecated Use withCustomId() from now on. Will get deleted in a future release
     */
    @Deprecated
    public SignerBuilder withRoleId(String roleId) {
        return withCustomId(roleId);
    }

    /**
     * <p>Adds an attachment requirement for the signer. The attachment requirement is conveniently customized by the
     * builder provided as parameter.</p>
     *
     * @param builder
     * @return the signer builder object itself
     */
    public SignerBuilder withAttachmentRequirement(AttachmentRequirementBuilder builder) {
        return withAttachmentRequirement(builder.build());
    }

    /**
     * Adds an attachment requirement for the signer.
     *
     * @param attachmentRequirement
     * @return the signer builder object itself
     */
    public SignerBuilder withAttachmentRequirement(AttachmentRequirement attachmentRequirement) {
        addAttachment(attachmentRequirement);
        return this;
    }

    /**
     * Adds an attachment requirement for the signer.
     *
     * @param attachmentRequirement the attachment
     */
    private void addAttachment(AttachmentRequirement attachmentRequirement) {
        attachments.put(attachmentRequirement.getName(), attachmentRequirement);
    }

    /**
     * @author admin
     */
    public static class AuthenticationBuilder {
        public Authentication build() {
            return new Authentication(AuthenticationMethod.EMAIL);
        }
    }

    /**
     * Challenge builder is a convenient class used to create an Authentication object.
     */
    public static class ChallengeBuilder extends AuthenticationBuilder {

        private String question;
        private final List<Challenge> challenges = new ArrayList<Challenge>();

        /**
         * @param question
         */
        public ChallengeBuilder(String question) {
            this.question = question;
        }

        public static ChallengeBuilder firstQuestion(String question) {
            return new ChallengeBuilder(question);
        }

        public ChallengeBuilder secondQuestion(String question) {
            this.question = question;
            return this;
        }

        public ChallengeBuilder answer(String answer) {
            challenges.add(new Challenge(question, answer));
            return this;
        }

        public Authentication build() {
            if (questionProvided() && challenges.isEmpty()) {
                throw new IllegalStateException("Question challenge was provided with no answer");
            }

            return new Authentication(challenges);
        }

        private boolean questionProvided() {
            return question != null && !question.trim().isEmpty();
        }
    }

    public static class SMSAuthenticationBuilder extends AuthenticationBuilder {

        private final String phoneNumber;

        public SMSAuthenticationBuilder(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public Authentication build() {
            Asserts.notNullOrEmpty(phoneNumber, "phone number");
            return new Authentication(phoneNumber);
        }
    }

    private boolean isGroupSigner() {
        return groupId != null;
    }

    private boolean isPlaceholder(){
        return groupId == null && email == null;
    }
}