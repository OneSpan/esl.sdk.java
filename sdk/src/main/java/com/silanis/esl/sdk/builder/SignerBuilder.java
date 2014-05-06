package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.Asserts;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.genericAssert;
import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

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

    /**
     * <p>The constructor of the SignerBuilderClass.</p>
     * @param email	the signer's email.
     */
    private SignerBuilder( String email ) {
        this.email = email;
        this.groupId = null;
    }

    private SignerBuilder( GroupId groupId ) {
        this.email = null;
        this.groupId = groupId;
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     * @param email	the signer's email
     * @return	a SignerBuilder object
     */
    public static SignerBuilder newSignerWithEmail( String email ) {
        return new SignerBuilder(email);
    }

    public static SignerBuilder newSignerFromGroup( GroupId groupId ) {
        return new SignerBuilder(groupId);
    }

    /**
     * Sets the ID of the signer for this package. 
     * <p>
     * E.g.: the signer's email makes for a good unique ID. john@do.com 
     * @param id	the signer's ID @size(min="1")
     * @return	the signer builder itself
     */
    public SignerBuilder withCustomId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets the signer's first name
     * @param firstName	the signer's first name @size(min="1", max="64")
     * @return	the signer builder itself
     */
    public SignerBuilder withFirstName( String firstName ) {
        Asserts.genericAssert( !isGroupSigner(), "first name can not be set for a group signer" );
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets the signer's last name
     * @param lastName	the signer's last name @size(min="1", max="64")
     * @return	the signer builder itself
     */
    public SignerBuilder withLastName( String lastName ) {
        Asserts.genericAssert( !isGroupSigner(), "last name can not be set for a group signer" );
        this.lastName = lastName;
        return this;
    }

    /**
     * Sets the signing order. If all signers can sign in any order, don't set this setting.
     * <p>
     * E.g.: a signer with a signingOrder of 1 would be required to sign before a signer with a signingOrder of 2, for example.
     * @param signingOrder	a value greater than zero
     * @return	the signer builder itself
     */
    public SignerBuilder signingOrder( int signingOrder ) {
        this.signingOrder = signingOrder;
        return this;
    }

    /**
     * Builds the actual signer object
     * @return	the signer object
     */
    public Signer build() {

        if (authentication == null) {
            authentication = authenticationBuilder.build();
        }

        Signer signer;
        if ( isGroupSigner() ) {
            signer = new Signer(groupId);
        } else {
            Asserts.notNullOrEmpty( firstName, "first name" );
            Asserts.notNullOrEmpty( lastName, "last name" );
            signer = new Signer( email, firstName, lastName, authentication );
            signer.setTitle(title);
            signer.setCompany(company);
            signer.setDeliverSignedDocumentsByEmail( deliverSignedDocumentsByEmail );
        }

        signer.setSigningOrder( signingOrder );
        signer.setCanChangeSigner( canChangeSigner );
        signer.setMessage( message );
        signer.setId(id);
        signer.setLocked(locked);
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
	 * @param challengeBuilder
	 *            the challenge builder
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
	 * @param phoneNumber the signer's cellphone number to which the SMS PIN number will be sent.
	 * @return the signer builder object itself
	 */
    public SignerBuilder withSmsSentTo(String phoneNumber) {
        this.authenticationBuilder = new SMSAuthenticationBuilder(phoneNumber);
        return this;
    }


    /**
     * Sets the Signer's authentication.
     * TODO: provide a better definition of what this truely mean
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
     * E.g.: Mr., Mrs., Ms., etc...
     * @param title the signer's title
     * @return	the signer builder object itself
     */
    public SignerBuilder withTitle(String title) {
        Asserts.genericAssert( !isGroupSigner(), "title can not be set for a group signer" );
        this.title = title;
        return this;
    }

    /**
     * <p>Sets the signer's company name.</p>
     * @param company	the signer's company name
     * @return	the signer builder object itself
     * @throws EslException throws an exception if signer is a group signer.
     */
    public SignerBuilder withCompany(String company) {
        Asserts.genericAssert( !isGroupSigner(), "company can not be set for a group signer" );
        this.company = company;
        return this;
    }

    /**
     * <p>Sets the canChangeSigner property to true.</p>
     * @return	the signer builder object itself
     */
    public SignerBuilder canChangeSigner() {
        canChangeSigner = true;
        return this;
    }

    /**
     * Sets the signer's email message
     * @param message	the message the signer will receive in the email invitation to start the signing ceremony
     * @return	the signet builder object itself
     */
    public SignerBuilder withEmailMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * <p>Sets the deliverSignedDocumentsByEmail to true.</p>
     * @return	the signer builder object itself
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
     * @deprecated Use withCustomId() from now on. Will get deleted in a future release
     * @param roleId
     * @return
     */
    @Deprecated
    public SignerBuilder withRoleId( String roleId ) {
        return withCustomId(roleId);
    }

    /**
     * 
     * @author admin
     *
     */
    public static class AuthenticationBuilder {
        public Authentication build() {
            return new Authentication(AuthenticationMethod.EMAIL);
        }
    }

    /**
     * Challenge builder is a convenient class used to create an Authentication object.  
     * 
     */
    public static class ChallengeBuilder extends AuthenticationBuilder {

        private String question;
        private final List<Challenge> challenges = new ArrayList<Challenge>();

        /**
         * 
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
            Asserts.notNullOrEmpty( phoneNumber, "phone number" );
            return new Authentication(phoneNumber);
        }
    }

    private boolean isGroupSigner() {
        return groupId != null;
    }
}