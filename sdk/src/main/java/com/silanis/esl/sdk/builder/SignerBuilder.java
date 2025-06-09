package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.*;

import static com.silanis.esl.sdk.AuthenticationMethod.*;
import static com.silanis.esl.sdk.builder.SignerBuilder.AuthenticationBuilder.newAuthenticationWithMethod;


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
    private NotificationMethodsBuilder notificationMethodsBuilder;
    private Authentication authentication = null;
    private NotificationMethods notificationMethods = null;
    private String title = "";
    private String company = "";
    private Locale language;
    private boolean canChangeSigner;
    private String message = "";
    private boolean deliverSignedDocumentsByEmail;
    private String id = null;
    private String placeholderName = null;
    private List<AttachmentRequirement> attachments = new ArrayList<AttachmentRequirement>();
    private KnowledgeBasedAuthentication knowledgeBasedAuthentication;
    private String localLanguage;

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
    private SignerBuilder(Placeholder placeholder) {
        this.email = null;
        this.groupId = null;
        this.id = placeholder.getId();
        this.placeholderName = placeholder.getName();
        this.signingOrder = placeholder.getSigningOrder();
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
     * @param placeholder the placeholder's ID.
     * @return the signer builder itself
     */
    public static SignerBuilder newSignerPlaceholder(Placeholder placeholder) {
        return new SignerBuilder(placeholder);
    }

    /**
     * Sets the ID of the signer for this package.
     * <p>
     * E.g.: the signer's email makes for a good unique ID. john@do.com
     *
     * @param id the signer's ID @size(min="1", max="255")
     * @return the signer builder itself
     */
    public SignerBuilder withCustomId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the signer's ID to the placeholder's ID.
     *
     * @param placeholder
     * @return
     */
    public SignerBuilder replacing(Placeholder placeholder) {
        this.id = placeholder.getId();
        return this;
    }

    public SignerBuilder withNotificationMethods(NotificationMethodsBuilder notificationMethodsBuilder){
        this.notificationMethodsBuilder = notificationMethodsBuilder;
        return this;
    }

    /**
     * Sets the signer's first name.
     *
     * @param firstName the signer's first name @size(min="1", max="255")
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
     * @param lastName the signer's last name @size(min="1", max="255")
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
     * @param signingOrder a value greater than zero
     * @return the signer builder itself
     */
    public SignerBuilder signingOrder(int signingOrder) {
        this.signingOrder = signingOrder;
        return this;
    }

    private Signer buildGroupSigner() {
        Signer result = new Signer(groupId);

        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setId(id);
        result.setAttachmentRequirements(attachments);
        result.setLocalLanguage(localLanguage);
        return result;
    }

    private Signer buildPlaceholderSigner() {
        Signer result = new Signer(id);
        result.setPlaceholderName(placeholderName);

        Asserts.notNullOrEmpty(id, "No placeholder set for this signer!");
        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setAttachmentRequirements(attachments);
        result.setLocalLanguage(localLanguage);
        return result;
    }

    private Signer buildRegularSigner() {
        if (authentication == null) {
            authentication = authenticationBuilder.build();
        }
        if (notificationMethods == null && notificationMethodsBuilder != null) {
            notificationMethods = notificationMethodsBuilder.build();
        }

        Signer result;

        Asserts.notNullOrEmpty(firstName, "first name");
        Asserts.notNullOrEmpty(lastName, "last name");
        result = new Signer(email, firstName, lastName, authentication, notificationMethods);
        result.setTitle(title);
        result.setCompany(company);
        result.setLanguage(language);
        result.setDeliverSignedDocumentsByEmail(deliverSignedDocumentsByEmail);

        result.setSigningOrder(signingOrder);
        result.setCanChangeSigner(canChangeSigner);
        result.setMessage(message);
        result.setId(id);
        result.setAttachmentRequirements(attachments);
        result.setKnowledgeBasedAuthentication(knowledgeBasedAuthentication);
        result.setLocalLanguage(localLanguage);
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
        } else if (isPlaceholder()) {
            signer = buildPlaceholderSigner();
        } else {
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
     * @param challengeBuilder the challenge builder
     * @return the signer builder object itself
     * @see ChallengeBuilder
     */
    public SignerBuilder challengedWithQuestions(ChallengeBuilder challengeBuilder) {
        this.authenticationBuilder = challengeBuilder;
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to QASMS.
     * </p>
     * The signer will be asked to authenticate, before accessing his signing
     * ceremony, by providing answers to authentication questions and providing an SMS PIN number that will have been sent by
     * OneSpan Sign to his phone..
     *
     * @param qasmsBuilder the qasms builder
     * @return the signer builder object itself
     * @see ChallengeBuilder
     */
    public SignerBuilder challengedWithQASMS(QASMSBuilder qasmsBuilder) {
        this.authenticationBuilder = qasmsBuilder;
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to SMS.
     * </p>
     * The signer will be asked to authenticate, before accessing his signing
     * ceremony, by providing an SMS PIN number that will have been sent by
     * OneSpan Sign to his phone.
     *
     * @param phoneNumber the signer's cellphone number to which the SMS PIN number will be sent @size(min="10", max="40")
     * @return the signer builder object itself
     */
    public SignerBuilder withSmsSentTo(String phoneNumber) {
        this.authenticationBuilder = new SMSAuthenticationBuilder(phoneNumber);
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to SSO.
     * </p>
     *
     * @return the signer builder object itself
     */
    public SignerBuilder withSSOAuthentication() {
        this.authenticationBuilder = newAuthenticationWithMethod(SSO);
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to SAA.
     * </p>
     *
     * @param idvWorkflow
     * @return the signer builder object itself
     */
    public SignerBuilder withIDVAuthentication(IdvWorkflow idvWorkflow) {
        this.authenticationBuilder = new IDVAuthenticationBuilder(idvWorkflow);
        return this;
    }

    /**
     * <p>
     * Sets the signer's authentication type to SAA.
     * </p>
     *
     * @param phoneNumber
     * @param idvWorkflow
     * @return the signer builder object itself
     */
    public SignerBuilder withIDVAuthentication(String phoneNumber, IdvWorkflow idvWorkflow) {
        this.authenticationBuilder = new IDVAuthenticationBuilder(phoneNumber, idvWorkflow);
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
     * @param title the signer's title @size(min="0", max="255")
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
     * @param company the signer's company name @size(max="255")
     * @return the signer builder object itself
     * @throws EslException throws an exception if signer is a group signer.
     */
    public SignerBuilder withCompany(String company) {
        Asserts.genericAssert(!isGroupSigner(), "company can not be set for a group signer");
        this.company = company;
        return this;
    }

    /**
     * <p>Sets the signer's language.</p>
     *
     * @param language the signer's language
     * @return the signer builder object itself
     * @throws EslException throws an exception if signer is a group signer.
     */
    public SignerBuilder withLanguage(Locale language) {
        Asserts.genericAssert(!isGroupSigner(), "language can not be set for a group signer");
        this.language = language;
        return this;
    }

    /**
     * The signer can assign someone else to sign the package.
     * <p>Sets the canChangeSigner property to true.</p>
     *
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
     *
     * @return the signer builder object itself
     */
    public SignerBuilder deliverSignedDocumentsByEmail() {
        deliverSignedDocumentsByEmail = true;
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

    public SignerBuilder withLocalLanguage() {
        this.localLanguage = "local";
        return this;
    }

    /**
     * <p>Adds an attachment requirement for the signer. The attachment requirement is conveniently customized by the
     * builder provided as parameter.</p>
     *
     * @param builder the attachment requirement builder
     * @return the signer builder object itself
     * @see AttachmentRequirementBuilder
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
        attachments.add(attachmentRequirement);
    }


    /**
     * <p>Adds KBA to the signer.</p>
     *
     * @param knowledgeBasedAuthentication knowledge based authentication
     * @return the signer builder itself
     */
    public SignerBuilder challengedWithKnowledgeBasedAuthentication(KnowledgeBasedAuthentication knowledgeBasedAuthentication) {
        this.knowledgeBasedAuthentication = knowledgeBasedAuthentication;
        return this;
    }

    /**
     * Authentication builder is a convenient class used to create an Authentication object
     * with email defined as the authentication method.
     *
     * @author admin
     */
    public static class AuthenticationBuilder {
        private final AuthenticationMethod method;

        private AuthenticationBuilder() {
            this.method = AuthenticationMethod.EMAIL;
        }

        private AuthenticationBuilder(AuthenticationMethod method) {
            this.method = method;
        }

        public static AuthenticationBuilder newAuthenticationWithMethod(AuthenticationMethod method) {
            return new AuthenticationBuilder(method);
        }

        public Authentication build() {
            return new Authentication(method);
        }
    }


    public static class NotificationMethodsBuilder {
        private final Set<NotificationMethod> primary;
        private String phone;

        private NotificationMethodsBuilder(){
            primary = new HashSet<>();
        }

        public NotificationMethodsBuilder withPrimaryMethods(Set<NotificationMethod> methods) {
            this.withPrimaryMethods(methods.toArray(new NotificationMethod[0]));
            return this;
        }

        public NotificationMethodsBuilder withPrimaryMethods(NotificationMethod... methods) {
            this.primary.clear();
            this.addPrimaryMethods(methods);
            return this;
        }

        public NotificationMethodsBuilder addPrimaryMethods(NotificationMethod... methods) {
            this.primary.addAll(Arrays.asList(methods));
            return this;
        }

        public NotificationMethodsBuilder withPhoneNumber(String phone) {
            this.phone = phone;
            return this;
        }

        public static NotificationMethodsBuilder newNotificationMethods() {
            return new NotificationMethodsBuilder();
        }

        public NotificationMethods build() {
            return new NotificationMethods(primary, phone);
        }
    }


    /**
     * Challenge builder is a convenient class used to create an Authentication
     * object. It is used to help define the authentication questions and
     * answers when the user logs on to OneSpan Sign.
     */
    public static class ChallengeBuilder extends AuthenticationBuilder {

        private String question;
        private final List<Challenge> challenges = new ArrayList<Challenge>();

        /**
         * Challenge builder constructor.
         *
         * @param question the question @size(min="1", max="255")
         */
        public ChallengeBuilder(String question) {
            this.question = question;
        }

        /**
         * First question asked to the user when they log on to OneSpan Sign.
         *
         * @param question the first question @size(min="1", max="255")
         * @return This
         */
        public static ChallengeBuilder firstQuestion(String question) {
            return new ChallengeBuilder(question);
        }

        /**
         * Second question asked to the user when they log on to OneSpan Sign.
         *
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
         * @param answer answer to the authentication questions @size(min="1", max="255")
         * @return This
         * @see #firstQuestion(String)
         * @see #secondQuestion(String)
         */
        public ChallengeBuilder answer(String answer) {
            challenges.add(new Challenge(question, answer));
            return this;
        }

        /**
         * Add answer to the first and second question with mask input option. Must be invoked in order
         * in order to provide the first question's answer and the second
         * question's answer.
         *
         * <p>
         * It should not be invoked more than twice.
         *
         * @param answer     answer to the authentication questions @size(min="1", max="255")
         * @param maskOption enable/disable masking of challenge
         * @return This
         * @see #firstQuestion(String)
         * @see #secondQuestion(String)
         */
        @Deprecated
        public ChallengeBuilder answer(String answer, Challenge.MaskOptions maskOption) {
            challenges.add(new Challenge(question, answer, maskOption));
            return this;
        }

        /**
         * Add answer to the first and second question with mask input. Must be invoked in order
         * in order to provide the first question's answer and the second
         * question's answer.
         *
         * <p>
         * It should not be invoked more than twice.
         *
         * @param answer answer to the authentication questions @size(min="1", max="255")
         * @return This
         * @see #firstQuestion(String)
         * @see #secondQuestion(String)
         */
        public ChallengeBuilder answerWithMaskInput(String answer) {
            challenges.add(new Challenge(question, answer, Challenge.MaskOptions.MaskInput));
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
         * Builder used to define authentication with OneSpan Sign by entering an
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
            return new Authentication(AuthenticationMethod.SMS, phoneNumber);
        }
    }

    public static class IDVAuthenticationBuilder extends AuthenticationBuilder {

        private String phoneNumber;
        private final IdvWorkflow idvWorkflow;

        /**
         * Builder used to define authentication with OneSpan Sign by entering an
         * SMS PIN number sent at the phone number defined below when the user
         * attempts to log in.
         *
         * @param idvWorkflow
         */
        public IDVAuthenticationBuilder(IdvWorkflow idvWorkflow) {
            this.idvWorkflow = idvWorkflow;
        }

        public IDVAuthenticationBuilder(String phoneNumber, IdvWorkflow idvWorkflow) {
            this.phoneNumber = phoneNumber;
            this.idvWorkflow = idvWorkflow;
        }

        @Override
        public Authentication build() {
            return new Authentication(IDV, phoneNumber, idvWorkflow);
        }
    }

    public static class QASMSBuilder extends AuthenticationBuilder {

        public static final String SMS_CHALLENGE_TYPE = "SMS";
        public static final String CHALLENGE_CHALLENGE_TYPE = "CHALLENGE";
        private String question;
        private String challengeType;
        private final List<Challenge> challenges = new ArrayList<Challenge>();

        /**
         * Challenge builder constructor.
         *
         * @param question      the question @size(min="1", max="255")
         * @param challengeType
         */
        public QASMSBuilder() {
        }
        public QASMSBuilder(String question, String challengeType) {
            this.question = question;
            this.challengeType = challengeType;
        }

         /**
         * First question asked to the user when they log on to OneSpan Sign.
         *
         * @param question the first question @size(min="1", max="255")
         * @param challengeType challenge type (CHALLENGE or SMS)
         * @return This
         */
        public static QASMSBuilder firstQuestion(String challengeType, String question) {
            return new QASMSBuilder(question, challengeType);
        }

        /**
         * Second question asked to the user when they log on to OneSpan Sign.
         *
         * @param question the second question @size(min="1", max="255")
         * @param challengeType challenge type (CHALLENGE or SMS)
         * @return This
         */
        public QASMSBuilder secondQuestion(String challengeType, String question) {
            this.question = question;
            this.challengeType = challengeType;
            return this;
        }

        /**
         * sms challenge asked to the user when they log on to OneSpan Sign.
         *
         * @param question the second question @size(min="1", max="255"): phoneNumber
         * @return This
         */
        public QASMSBuilder smsPhoneNumber(String question) {
            challenges.add(new Challenge(SMS_CHALLENGE_TYPE, question, null, Challenge.MaskOptions.None));
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
         * @param answer answer to the authentication questions @size(min="1", max="255")
         * @return This
         * @see #firstQuestion(String, String)
         * @see #secondQuestion(String, String)
         */
        public QASMSBuilder answer(String answer) {
            challenges.add(new Challenge(challengeType, question, answer));
            return this;
        }

        /**
         * Add answer to the first and second question with mask input option. Must be invoked in order
         * in order to provide the first question's answer and the second
         * question's answer.
         *
         * <p>
         * It should not be invoked more than twice.
         *
         * @param answer     answer to the authentication questions @size(min="1", max="255")
         * @param maskOption enable/disable masking of challenge
         * @return This
         * @see #firstQuestion(String, String)
         * @see #secondQuestion(String, String)
         */
        @Deprecated
        public QASMSBuilder answer(String answer, Challenge.MaskOptions maskOption) {
            challenges.add(new Challenge(challengeType, question, answer, maskOption));
            return this;
        }

        /**
         * Add answer to the first and second question with mask input. Must be invoked in order
         * in order to provide the first question's answer and the second
         * question's answer.
         *
         * <p>
         * It should not be invoked more than twice.
         *
         * @param answer answer to the authentication questions @size(min="1", max="255")
         * @return This
         * @see #firstQuestion(String, String)
         * @see #secondQuestion(String, String)
         */
        public QASMSBuilder answerWithMaskInput(String answer) {
            challenges.add(new Challenge(challengeType, question, answer, Challenge.MaskOptions.MaskInput));
            return this;
        }

        @Override
        public Authentication build() {
            if (questionProvided() && challenges.isEmpty()) {
                throw new IllegalStateException("Question challenge was provided with no answer");
            }
            if (challengeTypeProvided() && challenges.isEmpty()) {
                throw new IllegalStateException("Challenge type was provided with no challenge");
            }

            return new Authentication(QASMS, challenges);
        }

        private boolean questionProvided() {
            return question != null && !question.trim().isEmpty();
        }

        private boolean challengeTypeProvided() {
            return challengeType != null && !challengeType.trim().isEmpty();
        }

    }

    private boolean isGroupSigner() {
        return groupId != null;
    }

    private boolean isPlaceholder() {
        return groupId == null && email == null;
    }
    /**
     * <p>Adds a signer information for Lexis Nexis USA to the signer. The signer information is conveniently customized by the builder provided as parameter.</p>
     *
     * @param signerInformationForLexisNexisBuilder the signer builder for Lexis Nexis USA
     * @return the signer builder itself
     */
    public SignerBuilder challengedWithKnowledgeBasedAuthentication(SignerInformationForLexisNexisBuilder signerInformationForLexisNexisBuilder) {
        return challengedWithKnowledgeBasedAuthentication(signerInformationForLexisNexisBuilder.build());
    }

    /**
     * <p>Adds a signer information for LexisNexis to the signer.</p>
     *
     * @param signerInformationForLexisNexis a signer information for LexisNexis
     * @return the signer builder itself
     */
    public SignerBuilder challengedWithKnowledgeBasedAuthentication(SignerInformationForLexisNexis signerInformationForLexisNexis) {
        if (this.knowledgeBasedAuthentication == null) {
            this.knowledgeBasedAuthentication = new KnowledgeBasedAuthentication();
        }
        this.knowledgeBasedAuthentication.setSignerInformationForLexisNexis(signerInformationForLexisNexis);
        return this;
    }
}