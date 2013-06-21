package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.Challenge;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * User: dave
 */
public class SignerBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        String email = "email@aol.com";
        String firstName = "withFirstName";
        String lastName = "withLastName";
        int signingOrder = 1;
        Signer signer = newSignerWithEmail(email)
                .withFirstName( firstName )
                .withLastName( lastName )
                .signingOrder( signingOrder )
                .build();

        assertEquals( email, signer.getEmail() );
        assertEquals( firstName, signer.getFirstName() );
        assertEquals( lastName, signer.getLastName() );
        assertEquals( signingOrder, signer.getSigningOrder() );
    }

    @Test(expected = EslException.class)
    public void signerEmailCannotBeNull() {
        newSignerWithEmail(null).build();
    }

    @Test(expected = EslException.class)
    public void signerEmailCannotBeEmptyString() {
        newSignerWithEmail(" ").build();
    }

    @Test(expected = EslException.class)
    public void signerFirstNameCannotBeNull() {
        newSignerWithEmail("joe@blow.com").build();
    }

    @Test(expected = EslException.class)
    public void signerFirstNameCannotBeEmptyString() {
        newSignerWithEmail("joe@blow.com").build();
    }

    @Test(expected = EslException.class)
    public void signerLastNameCannotBeNull() {
        newSignerWithEmail("joe@blow.com").withFirstName("Joe").build();
    }

    @Test(expected = EslException.class)
    public void signerLastNameCannotBeEmptyString() {
        newSignerWithEmail("joe@blow.com").withFirstName("Joe").build();
    }

    @Test
    public void canSpecifyTitleAndCompany() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withTitle("Super Manager")
                .withCompany("Acme Inc")
                .build();

        assertThat(signer.getTitle(), is(equalTo("Super Manager")));
        assertThat(signer.getCompany(), is(equalTo("Acme Inc")));
    }

    @Test
    public void authenticationDefaultsToEmail() {
        Signer signer =  newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .build();

        assertThat(signer.getAuthenticationMethod(), is(equalTo(AuthenticationMethod.EMAIL)));
    }

    @Test
    public void providingQuestionAndAnswersSetsAuthenticationMethod() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .challengedWithQuestions(firstQuestion("What's your favorite sport?")
                        .answer("golf"))
                .build();

        assertThat(signer.getAuthenticationMethod(), is(equalTo(AuthenticationMethod.CHALLENGE)));
    }

    @Test
    public void savesProvidedQuestionsAndAnswers() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .challengedWithQuestions(firstQuestion("First question")
                        .answer("First answer")
                        .secondQuestion("Second question")
                        .answer("Second answer"))
                .build();

        assertThat(signer.getChallengeQuestions(), contains(new Challenge("First question", "First answer"), new Challenge("Second question", "Second answer")));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotProvideQuestionWithoutAnswer() {
        newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .challengedWithQuestions(firstQuestion("Question missing answer..."))
                .build();
    }

    @Test
    public void providingSignerCellPhoneSetsUpSMSAuthentication() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withSmsSentTo("1112223333")
                .build();

        assertThat(signer.getAuthenticationMethod(), is(equalTo(AuthenticationMethod.SMS)));
        assertThat(signer.getPhoneNumber(), is(equalTo("1112223333")));
    }

    @Test(expected = EslException.class)
    public void nullPhoneNumberNotAllowed() {
        newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withSmsSentTo(null)
                .build();
    }

    @Test(expected = EslException.class)
    public void emptyPhoneNumberNotAllowed() {
        newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withSmsSentTo("  ")
                .build();
    }

    @Test
    public void canConfiguredSignedDocumentDelivery() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .deliverSignedDocumentsByEmail()
                .build();

        assertThat(signer.isDeliverSignedDocumentsByEmail(), is(true));
    }
}