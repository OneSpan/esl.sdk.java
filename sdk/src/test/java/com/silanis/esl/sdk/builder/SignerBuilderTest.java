package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.silanis.esl.sdk.AuthenticationMethod.*;
import static com.silanis.esl.sdk.builder.IdvWorkflowBuilder.newIdvWorkflow;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerPlaceholder;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class SignerBuilderTest {
    @Test
    public void buildWithSpecifiedValues() {
        String email = "email@aol.com";
        String firstName = "withFirstName";
        String lastName = "withLastName";
        String phone = "+1 624-635-8233";
        Set<NotificationMethod> notificationMethods = new HashSet<>();
        notificationMethods.add(NotificationMethod.EMAIL);
        notificationMethods.add(NotificationMethod.SMS);

        int signingOrder = 1;
        Signer signer = newSignerWithEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .signingOrder(signingOrder)
                .withNotificationMethods(newNotificationMethods()
                        .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                        .withPhoneNumber(phone))
                .build();

        assertEquals(email, signer.getEmail());
        assertEquals(firstName, signer.getFirstName());
        assertEquals(lastName, signer.getLastName());
        assertEquals(signingOrder, signer.getSigningOrder());
        assertEquals(phone, signer.getNotificationMethods().getPhone());
        assertEquals(notificationMethods, signer.getNotificationMethods().getPrimary());
    }


    public static class NotificationMethodsBuilderTest {
        String email = "email@aol.com";
        String firstName = "withFirstName";
        String lastName = "withLastName";
        String phone = "+1 624-635-8233";
        Set<NotificationMethod> byEmail = new HashSet<>(Arrays.asList(NotificationMethod.EMAIL));
        Set<NotificationMethod> byEmailAndSMS = new HashSet<>(Arrays.asList(NotificationMethod.EMAIL, NotificationMethod.SMS));

        @Test
        public void emailAsDefault(){
            Signer signer = newSignerWithEmail(email)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .build();

            assertEquals(null, signer.getNotificationMethods());
        }

        @Test
        public void alwaysContainsEmail(){
            Signer signer = newSignerWithEmail(email)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withNotificationMethods(newNotificationMethods()
                            .withPrimaryMethods())
                    .build();

            assertEquals(byEmail, signer.getNotificationMethods().getPrimary());
        }

        @Test
        public void noDuplicateMethods(){
            Signer signer = newSignerWithEmail(email)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withNotificationMethods(newNotificationMethods()
                            .withPrimaryMethods(NotificationMethod.EMAIL)
                            .addPrimaryMethods(NotificationMethod.EMAIL))
                    .build();

            assertEquals(byEmail, signer.getNotificationMethods().getPrimary());
        }

        @Test
        public void cannotAddSMSWithoutPhoneNumber(){
            try {
                Signer signer1 = newSignerWithEmail(email)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL)
                                .addPrimaryMethods(NotificationMethod.SMS))
                        .build();
            }
            catch (IllegalStateException e) {
                    System.err.println("Package build failed: " + e.getMessage());
            }

            try {
                Signer signer2 = newSignerWithEmail(email)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withNotificationMethods(newNotificationMethods()
                                .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS))
                        .build();
            }
            catch (IllegalStateException e) {
                System.err.println("Package build failed: " + e.getMessage());
            }

        }

        @Test
        public void canAddSMSAfterPhoneNumber(){
            Signer signer = newSignerWithEmail(email)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withNotificationMethods(newNotificationMethods()
                            .withPrimaryMethods(NotificationMethod.EMAIL, NotificationMethod.SMS)
                            .withPhoneNumber(phone))
                    .build();
            assertEquals(byEmailAndSMS, signer.getNotificationMethods().getPrimary());
        }
    }


    @Test
    public void buildPlaceholder() {
        String id = "placeholderId";
        Signer signer = newSignerPlaceholder(new Placeholder(id))
                .build();

        assertEquals(id, signer.getId());
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
        newSignerWithEmail("joe@blow.com").withFirstName(null).build();
    }

    @Test(expected = EslException.class)
    public void signerFirstNameCannotBeEmptyString() {
        newSignerWithEmail("joe@blow.com").withFirstName("").build();
    }

    @Test(expected = EslException.class)
    public void signerLastNameCannotBeNull() {
        newSignerWithEmail("joe@blow.com").withLastName(null).build();
    }

    @Test(expected = EslException.class)
    public void signerLastNameCannotBeEmptyString() {
        newSignerWithEmail("joe@blow.com").withLastName("").build();
    }

    @Test(expected = EslException.class)
    public void placeholderCannotBeNull() {
        newSignerPlaceholder(new Placeholder(null)).build();
    }

    @Test
    public void canSpecifyTitleAndCompany() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withTitle("Super Manager")
                .withCompany("Acme Inc")
                .build();

        assertThat(signer.getTitle(), is("Super Manager"));
        assertThat(signer.getCompany(), is("Acme Inc"));
    }

    @Test
    public void authenticationDefaultsToEmail() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .build();

        assertThat(signer.getAuthenticationMethod(), is(AuthenticationMethod.EMAIL));
    }

    @Test
    public void providingQuestionAndAnswersSetsAuthenticationMethod() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .challengedWithQuestions(firstQuestion("What's your favorite sport?")
                        .answer("golf"))
                .build();

        assertThat(signer.getAuthenticationMethod(), is(AuthenticationMethod.CHALLENGE));
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

        assertThat(signer.getAuthenticationMethod(), is(SMS));
        assertThat(signer.getAuthPhoneNumber(), is("1112223333"));
    }

    @Test
    public void setsUpSSOAuthentication() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withSSOAuthentication()
                .build();

        assertThat(signer.getAuthenticationMethod(), is(SSO));
    }

    @Test
    public void setsUpIDVAuthentication() {
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withIDVAuthentication(newIdvWorkflow("00000000-0000-0001-0000-200000000055")
                        .withTenant("oss")
                        .withType("DVF")
                        .build())
                .build();

        assertThat(signer.getAuthenticationMethod(), is(IDV));
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

    @Test
    public void canSetAndGetAttachmentRequirements() {
        com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement = AttachmentRequirementBuilder.newAttachmentRequirementWithName("Driver's license")
                .withDescription("Please upload scanned driver's license.")
                .isRequiredAttachment()
                .build();

        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withAttachmentRequirement(attachmentRequirement)
                .build();

        assertThat("Signer should have only 1 attachment.", signer.getAttachmentRequirements().size(), is(1));
        assertThat("Attachment information was set incorrectly.", signer.getAttachmentRequirement("Driver's license"), is(attachmentRequirement));
    }

    @Test
    public void canAddTwoAttachmentRequirement() {
        com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement1 = AttachmentRequirementBuilder.newAttachmentRequirementWithName("Driver's license")
                .withDescription("Please upload scanned driver's license.")
                .isRequiredAttachment()
                .build();
        com.silanis.esl.sdk.AttachmentRequirement attachmentRequirement2 = AttachmentRequirementBuilder.newAttachmentRequirementWithName("Medicare card")
                .withDescription("Please upload scanned medicare card.")
                .isRequiredAttachment()
                .build();
        Signer signer = newSignerWithEmail("joe@blow.com")
                .withFirstName("Joe")
                .withLastName("Blow")
                .withAttachmentRequirement(attachmentRequirement1)
                .withAttachmentRequirement(attachmentRequirement2)
                .build();

        assertThat("Signer should have 2 attachment.", signer.getAttachmentRequirements().size(), is(2));
        assertThat("Attachment1 information was set incorrectly.", signer.getAttachmentRequirement("Driver's license"), is(attachmentRequirement1));
        assertThat("Attachment2 information was set incorrectly.", signer.getAttachmentRequirement("Medicare card"), is(attachmentRequirement2));
    }

}