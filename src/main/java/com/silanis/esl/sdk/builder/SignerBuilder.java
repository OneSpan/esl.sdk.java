package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Authentication;
import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.Signer;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.internal.Asserts.notNullOrEmpty;

/**
 * <p>The SignerBuilder class is a convenient class used to create and customize a signer.</p>
 */
final public class SignerBuilder {

    public static final int DEFAULT_SIGNING_ORDER = 0;

    private String email;
    private String firstName;
    private String lastName;
    private int signingOrder = DEFAULT_SIGNING_ORDER;
    private AuthenticationBuilder authenticationBuilder = new AuthenticationBuilder();
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
    }

    /**
     * <p>Creates a SignerBuilder object.</p>
     * @param email	the signer's email
     * @return	a SignerBuilder object
     */
    public static SignerBuilder newSignerWithEmail( String email ) {
        notNullOrEmpty(email, "email");

        return new SignerBuilder(email);
    }

    /**
     * Sets the ID of the signer.
     * @param id	the signer's ID
     * @return	the signer builder itself
     */
    public SignerBuilder withCustomId( String id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets the signer's first name
     * @param firstName	the signer's first name
     * @return	the signer builder itself
     */
    public SignerBuilder withFirstName( String firstName ) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets the signer's last name
     * @param lastName	the signer's last name
     * @return	the signer builder itself
     */
    public SignerBuilder withLastName( String lastName ) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Sets the signing order.
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
        notNullOrEmpty(firstName, "first name");
        notNullOrEmpty(lastName, "last name");

        Authentication authentication = authenticationBuilder.build();

        Signer signer = new Signer( email, firstName, lastName, authentication );
        signer.setSigningOrder(signingOrder);
        signer.setTitle(title);
        signer.setCompany(company);
        signer.setCanChangeSigner(canChangeSigner);
        signer.setMessage(message);
        signer.setDeliverSignedDocumentsByEmail(deliverSignedDocumentsByEmail);
        signer.setId(id);
        signer.setLocked(locked);
        return signer;
    }

    /**
     * <p>Sets the signer's authentication type to CHALLENGE.</p>
     * @param challengeBuilder	the challenge builder
     * @return	the signer builder object itself
     */
    public SignerBuilder challengedWithQuestions(ChallengeBuilder challengeBuilder) {
        this.authenticationBuilder = challengeBuilder;
        return this;
    }

    /**
     * <p>Sets the signer's authentication type to SMS.</p>
     * @param phoneNumber
     * @return	the signer builder object itself
     */
    public SignerBuilder withSmsSentTo(String phoneNumber) {
        this.authenticationBuilder = new SMSAuthenticationBuilder(phoneNumber);
        return this;
    }

    /**
     * <p>Sets the signer's title.</p>
     * @param title the signer's title
     * @return	the signer builder object itself
     */
    public SignerBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * <p>Sets the signer's company name.</p>
     * @param company	the signer's company name
     * @return	the signer builder object itself
     */
    public SignerBuilder withCompany(String company) {
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

    static SignerBuilder newSignerFromAPISigner( com.silanis.awsng.web.rest.model.Role role ) {
        com.silanis.awsng.web.rest.model.Signer eslSigner = role.getSigners().get( 0 );

        SignerBuilder signerBuilder = SignerBuilder.newSignerWithEmail( eslSigner.getEmail() )
                .withCustomId( eslSigner.getId() )
                .withRoleId( role.getId() )
                .withFirstName( eslSigner.getFirstName() )
                .withLastName( eslSigner.getLastName() )
                .withCompany( eslSigner.getCompany() )
                .withTitle( eslSigner.getTitle() )
                .signingOrder( role.getIndex() );

        if ( eslSigner.getDelivery() != null && eslSigner.getDelivery().getEmail() ) {
            signerBuilder.deliverSignedDocumentsByEmail();
        }

        if ( role.evalReassign() ) {
            signerBuilder.canChangeSigner();
        }

        if ( role.getEmailMessage() != null ) {
            signerBuilder.withEmailMessage( role.getEmailMessage().getContent() );
        }

        if ( role.getLocked() ) {
            signerBuilder.lock();
        }
        return signerBuilder;
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
            notNullOrEmpty(phoneNumber, "phone number");
            return new Authentication(phoneNumber);
        }
    }
}