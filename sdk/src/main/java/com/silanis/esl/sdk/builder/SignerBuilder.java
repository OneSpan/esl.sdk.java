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
    private KnowledgeBasedAuthentication knowledgeBasedAuthentication;

    /**
     * <p>The constructor of the SignerBuilderClass.</p>
     *
     * @param email the signer's email @size(min="6", max="255", valid email address)
     */
    private SignerBuilder(String email) {
        if (email == null) {
            this.email = email;
        } else {
            this.email = email.toLowerCase();
        }
        this.groupId = null;
    }

    /**
     * <p>The constructor of the SignerBuilderClass.</p>
     *
     * @param groupId the group ID.
     */
    private SignerBuilder(GroupId groupId) {
        this.email = null;
        this.groupId = groupId;
    }

    /**
     * <p>The constructor of the SignerBuilderClass.</p>
     *
     * @param placeholder the placeholder.
     */
    private SignerBuilder(Placeholder placeholder){
        this.email = null;
        this.groupId = null;
        this.id = placeholder.getId();
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     *
     * @param email the signer's email @size(min="6", max="255", valid email address)
     * @return the signer builder itself
     */
    public static SignerBuilder newSignerWithEmail(String email) {
        return new SignerBuilder(email);
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     *
     * @param groupId the group ID.
     * @return the signer builder itself
     */
    public static SignerBuilder newSignerFromGroup(GroupId groupId) {
        return new SignerBuilder(groupId);
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     *
     * @param roleId the placeholder's ID.
     * @return the signer builder itself
     */
    public static SignerBuilder newSignerPlaceholder(Placeholder roleId) {
        return new SignerBuilder(roleId);
    }

    /**
     * Sets the ID of the signer for this package.
     * <p>
     * E.g.: the signer's email makes for a good unique ID. john@do.com
     * @param id the signer's ID @size(min="1", max="64")
     * @return the signer builder itself
     */
    public SignerBuilder withCustomId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the signer's ID to the placeholder's ID.
     * @param placeholder
     * @return
     */
    public SignerBuilder replacing( Placeholder placeholder){
        this.id = placeholder.getId();
        return this;
    }

    /**
     * Sets the signer's first name.
     *
     * @param firstName the signer's first name @size(min="1", max="64")
     * @return the signer builder itself
     */
    public SignerBuilder withFirstName(String firstName) {
        Asserts.genericAssert(!isGroupSigner(), "first name can not be set for a group signer");
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets the signer's last name.
     *
     * @param lastName the signer's last name @size(min="1", max="64")
     * @return the signer builder itself
     */
    public SignerBuilder withLastName(String lastName) {
        Asserts.genericAssert(!isGroupSigner(), "last name can not be set for a group signer");
        this.lastName = lastName;
        return this;
    }

    /**
     * Sets the signing order. If all signers can sign in any order, don't set this setting.
     * <p>
     * E.g.: a signer with a signingOrder of 1 would be required to sign before a signer with a signingOrder of 2, for example.
     *
     * @param signingOrder	a value greater than zero
     * @return	the signer builder itself
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
        result.setKnowledgeBasedAuthentication(knowledgeBasedAuthentication);

        return result;
    }

    /**
     * Builds the actual signer object.
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
     * <p>
     * Sets the signer's authentication type to CHALLENGE.
     * </p>
     * The signer will be asked to authenticate, before accessing his signing
     * ceremony, by providing answers to authentication questions.
     *
     * @see ChallengeBuilder
     * @param challengeBuilder the challenge builder
     * @return the signer builder object itself
     */
    public SignerBuilder challengedWithQuestions(ChallengeBuilder challengeBuilder) {
        this.authenticationBuilder = challengeBuilder;
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to SMS.
     * </p>
     * The signer will be asked to authenticate, before accessing his signing
     * ceremony, by providing an SMS PIN number that will have been sent by
     * e-SignLive to his phone.
     *
     * @param phoneNumber the signer's cellphone number to which the SMS PIN number will be sent @size(min="10", max="40")
     * @return the signer builder object itself
     */
    public SignerBuilder withSmsSentTo(String phoneNumber) {
        this.authenticationBuilder = new SMSAuthenticationBuilder(phoneNumber);
        return this;
    }

    /**
     * Sets the Signer's authentication. The authentication types are email,
     * questions and answers (challenges) or SMS.
     *
     * @param authentication the authentication object
     * @return
     */
    public SignerBuilder withAuthentication(Authentication authentication) {
        this.authentication = authentication;
        return this;
    }

    /**
     * <p>Sets the signer's title.</p>
     * E.g.: Mr., Mrs., Ms., etc...
     *
     * @param title the signer's title @size(min="0", max="64")
     * @return	the signer builder object itself
     */
    public SignerBuilder withTitle(String title) {
        Asserts.genericAssert(!isGroupSigner(), "title can not be set for a group signer");
        this.title = title;
        return this;
    }

    /**
     * <p>Sets the signer's company name.</p>
     *
     * @param company the signer's company name @size(max="255")
     * @return	the signer builder object itself
     * @throws EslException throws an exception if signer is a group signer.
     */
    public SignerBuilder withCompany(String company) {
        Asserts.genericAssert(!isGroupSigner(), "company can not be set for a group signer");
        this.company = company;
        return this;
    }

    /**
     * The signer can assign someone else to sign the package.
     * <p>Sets the canChangeSigner property to true.</p>
     * @return the signer builder object itself
     */
    public SignerBuilder canChangeSigner() {
        canChangeSigner = true;
        return this;
    }

    /**
     * Sets the signer's email message they will receive in the email invitation to start the signing ceremony.
     *
     * @param message the message the signer will receive in the email invitation to start the signing ceremony @size(min="0", max="2000")
     * @return the signet builder object itself
     */
    public SignerBuilder withEmailMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * <p>Invoking this method results in documents being distributed back to the sender as an email attachments once the package is complete.</p>
     * @return	the signer builder object itself
     */
    public SignerBuilder deliverSignedDocumentsByEmail() {
        deliverSignedDocumentsByEmail = true;
        return this;
    }

    /**
     * Locks the signer.
     * @return the signer builder object itself
     */
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
     * @see AttachmentRequirementBuilder
     * @param builder the attachment requirement builder
     * @return the signer builder object itself
     */
    public SignerBuilder withAttachmentRequirement(AttachmentRequirementBuilder builder) {
        return withAttachmentRequirement(builder.build());
    }

    /**
     * Adds an attachment requirement for the signer.
     *
     * @param attachmentRequirement the attachment
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
     * <p>Adds KBA to the signer.</p>
     *
     * @param knowledgeBasedAuthentication kba
     * @return the signer builder itself
     */
    public SignerBuilder withKBA(KnowledgeBasedAuthentication knowledgeBasedAuthentication) {
        this.knowledgeBasedAuthentication = knowledgeBasedAuthentication;
        return this;
    }

    /**
     * <p>Adds a signer information for Equifax Canada to the signer. The signer information is conveniently customized by the builder provided as parameter.</p>
     *
     * @param signerInformationForEquifaxCanadaBuilder the signer builder for Equifax Canada
     * @return the signer builder itself
     */
    public SignerBuilder withKBA(SignerInformationForEquifaxCanadaBuilder signerInformationForEquifaxCanadaBuilder) {
        return withKBA(signerInformationForEquifaxCanadaBuilder.build());
    }

    /**
     * <p>Adds a signer information for Equifax Canada to the signer.</p>
     *
     * @param signerInformationForEquifaxCanada a signer information for Equifax Canada
     * @return the signer builder itself
     */
    public SignerBuilder withKBA(SignerInformationForEquifaxCanada signerInformationForEquifaxCanada) {
        if(this.knowledgeBasedAuthentication == null) {
            this.knowledgeBasedAuthentication = new KnowledgeBasedAuthentication();
        }
        this.knowledgeBasedAuthentication.setSignerInformationForEquifaxCanada(signerInformationForEquifaxCanada);
        return this;
    }

    /**
     * <p>Adds a signer information for Equifax USA to the signer. The signer information is conveniently customized by the builder provided as parameter.</p>
     *
     * @param signerInformationForEquifaxUSABuilder the signer builder for Equifax USA
     * @return the signer builder itself
     */
    public SignerBuilder withKBA(SignerInformationForEquifaxUSABuilder signerInformationForEquifaxUSABuilder) {
        return withKBA(signerInformationForEquifaxUSABuilder.build());
    }

    /**
     * <p>Adds a signer information for Equifax USA to the signer.</p>
     *
     * @param signerInformationForEquifaxUSA a signer information for Equifax USA
     * @return the signer builder itself
     */
    public SignerBuilder withKBA(SignerInformationForEquifaxUSA signerInformationForEquifaxUSA) {
        if(this.knowledgeBasedAuthentication == null) {
            this.knowledgeBasedAuthentication = new KnowledgeBasedAuthentication();
        }
        this.knowledgeBasedAuthentication.setSignerInformationForEquifaxUSA(signerInformationForEquifaxUSA);
        return this;
    }

    /**
     * Authentication builder is a convenient class used to create an Authentication object
     * with email defined as the authentication method.
     * @author admin
     */
    public static class AuthenticationBuilder {
        public Authentication build() {
            return new Authentication(AuthenticationMethod.EMAIL);
        }
    }

    /**
     * Challenge builder is a convenient class used to create an Authentication
     * object. It is used to help define the authentication questions and
     * answers when the user logs on to e-SignLive.
     *
     */
    public static class ChallengeBuilder extends AuthenticationBuilder {

        private String question;
        private final List<Challenge> challenges = new ArrayList<Challenge>();

        /**
         * Challenge builder constructor.
         * @param question the question @size(min="1", max="255")
         */
        public ChallengeBuilder(String question) {
            this.question = question;
        }

        /**
         * First question asked to the user when they log on to e-SignLive.
         * @param question the first question @size(min="1", max="255")
         * @return This
         */
        public static ChallengeBuilder firstQuestion(String question) {
            return new ChallengeBuilder(question);
        }

        /**
         * Second question asked to the user when they log on to e-SignLive.
         * @param question the second question @size(min="1", max="255")
         * @return This
         */
        public ChallengeBuilder secondQuestion(String question) {
            this.question = question;
            return this;
        }

        /**
         * Add answer to the first and second question. Must be invoked in order
         * in order to provide the first question's answer and the second
         * question's answer.
         *
         * <p>
         * It should not be invoked more than twice.
         *
         * @see #firstQuestion(String)
         * @see #secondQuestion(String)
         * @param answer answer to the authentication questions @size(min="1", max="255")
         * @return This
         */
        public ChallengeBuilder answer(String answer) {
            challenges.add(new Challenge(question, answer));
            return this;
        }

        @Override
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

        /**
         * Builder used to define authentication with e-SignLive by entering an
         * SMS PIN number sent at the phone number defined below when the user
         * attempts to log in.
         *
         * @param phoneNumber the phone number @size(min="10", max="40")
         */
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