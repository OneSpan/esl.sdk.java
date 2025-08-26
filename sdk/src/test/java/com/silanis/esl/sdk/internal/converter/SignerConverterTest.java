package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.api.util.AdHocGroupUtils;
import com.silanis.esl.sdk.builder.AttachmentRequirementBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.junit.Test;

import java.util.*;

import static com.silanis.esl.sdk.builder.SignerBuilder.NotificationMethodsBuilder.newNotificationMethods;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 2:00 PM
 * <p/>
 * Test SignerConverterF
 */
public class SignerConverterTest implements ConverterTest {
    private com.silanis.esl.sdk.Signer sdkSigner1 = null;
    private com.silanis.esl.sdk.Signer sdkSigner2 = null;
    private com.silanis.esl.api.model.Signer apiSigner1 = null;
    private com.silanis.esl.api.model.Signer apiSigner2 = null;
    private com.silanis.esl.api.model.Role apiRole = null;
    private SignerConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSigner1 = null;
        converter = new SignerConverter(sdkSigner1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISigner(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSigner1 = null;
        converter = new SignerConverter(apiRole);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSigner(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSigner1 = null;
        converter = new SignerConverter(sdkSigner1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSigner(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiRole = null;
        converter = new SignerConverter(apiRole);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISigner(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSigner1 = createTypicalSDKSigner();
        sdkSigner2 = new SignerConverter(sdkSigner1).toSDKSigner();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSigner2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSigner2, is(sdkSigner1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiRole = createTypicalAPIRole();
        apiSigner2 = new SignerConverter(apiRole).toAPISigner();

        assertThat("Converter returned a null api object for a non null api object", apiSigner2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiSigner2, is(apiRole.getSigners().get(0)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiRole = createTypicalAPIRole();
        apiSigner1 = apiRole.getSigners().get(0);

        sdkSigner1 = new SignerConverter(apiRole).toSDKSigner();

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, notNullValue());
        assertThat("Email was not correctly set", apiSigner1.getEmail(), is(sdkSigner1.getEmail()));
        assertThat("First name was not correctly set", apiSigner1.getFirstName(), is(sdkSigner1.getFirstName()));
        assertThat("Last name was not correctly set", apiSigner1.getLastName(), is(sdkSigner1.getLastName()));
        assertThat("Company was not correctly set", apiSigner1.getCompany(), is(sdkSigner1.getCompany()));
        assertThat("SignerType was not correctly set", apiSigner1.getSignerType(), is(sdkSigner1.getSignerType()));
        assertThat("Title was not correctly set", apiSigner1.getTitle(), is(sdkSigner1.getTitle()));
        assertThat("Language was not correctly set", apiSigner1.getLanguage(), is(sdkSigner1.getLanguage().getLanguage()));
        assertThat("Notification methods was not correctly set", NotificationMethodsConverter.convertNotificationMethodsToSDK(apiSigner1.getNotificationMethods().getPrimary()), is(sdkSigner1.getNotificationMethods().getPrimary()));
        assertThat("Notification phone number was not correctly set", sdkSigner1.getNotificationMethods().getPhone(), isEmptyOrNullString());

        assertThat("Signer ID was not correctly set", apiRole.getId(), is(sdkSigner1.getId()));
        assertThat("Signing order was not correctly set", apiRole.getIndex(), is(sdkSigner1.getSigningOrder()));
        assertThat("Can change signer flag was not correctly set", apiRole.getReassign(), is(sdkSigner1.canChangeSigner()));
        assertThat("Email was not correctly set", apiRole.getEmailMessage().getContent(), is(sdkSigner1.getMessage()));

        String attachmentName = apiRole.getAttachmentRequirements().get(0).getName();
        assertThat("Attachment's name was not set correctly", attachmentName, is(sdkSigner1.getAttachmentRequirement(attachmentName).getName()));
        assertThat("Attachment's description was not set correctly", apiRole.getAttachmentRequirements().get(0).getDescription(), is(sdkSigner1.getAttachmentRequirement(attachmentName).getDescription()));
        assertThat("Attachment's required property was not set correctly", apiRole.getAttachmentRequirements().get(0).getRequired(), is(sdkSigner1.getAttachmentRequirement(attachmentName).isRequired()));
        assertThat("Attachment's status was not set correctly", apiRole.getAttachmentRequirements().get(0).getStatus(), is(sdkSigner1.getAttachmentRequirement(attachmentName).getStatus().toString()));
        assertThat("Attachment's comments was not set correctly", apiRole.getAttachmentRequirements().get(0).getComment(), is(sdkSigner1.getAttachmentRequirement(attachmentName).getSenderComment()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSigner1 = createTypicalSDKSigner();
        apiSigner1 = new SignerConverter(sdkSigner1).toAPISigner();
        assertThat("Converter returned a null api object for a non null sdk object", apiSigner1, notNullValue());
        assertThat("Email was not correctly set", apiSigner1.getEmail(), is(sdkSigner1.getEmail()));
        assertThat("First name was not correctly set", apiSigner1.getFirstName(), is(sdkSigner1.getFirstName()));
        assertThat("Last name was not correctly set", apiSigner1.getLastName(), is(sdkSigner1.getLastName()));
        assertThat("Company was not correctly set", apiSigner1.getCompany(), is(sdkSigner1.getCompany()));
        assertThat("Title was not correctly set", apiSigner1.getTitle(), is(sdkSigner1.getTitle()));
        assertThat("Notification methods was not correctly set", NotificationMethodsConverter.convertNotificationMethodsToSDK(apiSigner1.getNotificationMethods().getPrimary()), is(sdkSigner1.getNotificationMethods().getPrimary()));

    }

    @Test
    public void convertSDKSignerToAPIRole() {

        sdkSigner1 = createTypicalSDKSigner();
        String roleId = UUID.randomUUID().toString().replace("-", "");
        apiRole = new SignerConverter(sdkSigner1).toAPIRole(roleId);

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, notNullValue());
        assertThat("Email was not correctly set", apiRole.getSigners().get(0).getEmail(),
                is(sdkSigner1.getEmail()));
        assertThat("First name was not correctly set", apiRole.getSigners().get(0).getFirstName(),
                is(sdkSigner1.getFirstName()));
        assertThat("Last name was not correctly set", apiRole.getSigners().get(0).getLastName(),
                is(sdkSigner1.getLastName()));
        assertThat("Company was not correctly set", apiRole.getSigners().get(0).getCompany(),
                is(sdkSigner1.getCompany()));
        assertThat("Title was not correctly set", apiRole.getSigners().get(0).getTitle(),
                is(sdkSigner1.getTitle()));
        assertThat("Language was not correctly set", apiRole.getSigners().get(0).getLanguage(),
                is(sdkSigner1.getLanguage().getLanguage()));

        assertThat("ID was not set correctly", apiRole.getId(), is(sdkSigner1.getId()));
        assertThat("Name was not set correctly", apiRole.getName(), is(sdkSigner1.getId()));
        assertThat("Message was not set correctly", apiRole.getEmailMessage().getContent(), is(sdkSigner1.getMessage()));

        String attachmentName = apiRole.getAttachmentRequirements().get(0).getName();
        assertThat("Attachment's name was not set correctly", attachmentName, is(sdkSigner1.getAttachmentRequirement(attachmentName).getName()));
        assertThat("Attachment's description was not set correctly", apiRole.getAttachmentRequirements().get(0).getDescription(), is(sdkSigner1.getAttachmentRequirement(attachmentName).getDescription()));
        assertThat("Attachment's required property was not set correctly", apiRole.getAttachmentRequirements().get(0).getRequired(), is(sdkSigner1.getAttachmentRequirement(attachmentName).isRequired()));
        assertThat("Notification methods was not correctly set", NotificationMethodsConverter.convertNotificationMethodsToSDK(apiRole.getSigners().get(0).getNotificationMethods().getPrimary()), is(sdkSigner1.getNotificationMethods().getPrimary()));

    }

    @Test
    public void convertSDKSignerWithNullEntriesToAPIRole() {
        String roleId = UUID.randomUUID().toString().replace("-", "");
        sdkSigner1 = SignerBuilder.newSignerWithEmail("abc@test.com")
                .canChangeSigner()
                .deliverSignedDocumentsByEmail()
                .signingOrder(1)
                .withCompany("ABC Inc.")
                .withLanguage(Locale.FRENCH)
                .withFirstName("first name")
                .withLastName("last name")
                .withTitle("Miss")
                .build();

        apiRole = new SignerConverter(sdkSigner1).toAPIRole(roleId);

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, notNullValue());
        assertThat("Email was not correctly set", apiRole.getSigners().get(0).getEmail(),
                is(sdkSigner1.getEmail()));
        assertThat("First name was not correctly set", apiRole.getSigners().get(0).getFirstName(),
                is(sdkSigner1.getFirstName()));
        assertThat("Last name was not correctly set", apiRole.getSigners().get(0).getLastName(),
                is(sdkSigner1.getLastName()));
        assertThat("Company was not correctly set", apiRole.getSigners().get(0).getCompany(),
                is(sdkSigner1.getCompany()));
        assertThat("Title was not correctly set", apiRole.getSigners().get(0).getTitle(),
                is(sdkSigner1.getTitle()));
        assertThat("Language was not correctly set", apiRole.getSigners().get(0).getLanguage(),
                is(sdkSigner1.getLanguage().getLanguage()));
        assertThat("Notification methods was not correctly set", apiRole.getSigners().get(0).getNotificationMethods()
                , nullValue());


        assertThat("ID was not set correctly", apiRole.getId(), is(roleId));
        assertThat("Name was not set correctly", apiRole.getName(), is(roleId));
        assertThat("Message was not set correctly", apiRole.getEmailMessage(), nullValue());
    }

    @Test
    public void convertAPIRoleToSDKAdHocGroupSigner() {
        apiRole = createTypicalAPIAdHocGroupRole();
        apiSigner1 = apiRole.getSigners().get(0);

        sdkSigner1 = new SignerConverter(apiRole).toSDKSigner();

        assertThat("Converter returned a null sdk object for a non null api object", sdkSigner1, notNullValue());
        assertThat("Email was not correctly set", sdkSigner1.getEmail(), is(apiSigner1.getEmail()));
        assertThat("First name was not correctly set", sdkSigner1.getGroup().getName(), is(apiSigner1.getFirstName()));
        assertThat("Company was not correctly set", sdkSigner1.getCompany(), is(apiSigner1.getCompany()));
        assertThat("Language was not correctly set", sdkSigner1.getLanguage().getLanguage(), is(apiSigner1.getLanguage()));
        assertThat("SignerType was not correctly set", sdkSigner1.getSignerType(), is(apiSigner1.getSignerType()));
        assertThat("isAdhocGroupSigner was not correctly set", sdkSigner1.getSignerType(), is(AdHocGroupUtils.AD_HOC_GROUP_SIGNER_TYPE));
        assertThat("Group was not correctly set", sdkSigner1.getGroup(), notNullValue());
        assertThat("Group name was not correctly set", sdkSigner1.getGroup().getName(), is(apiSigner1.getGroup().getName()));
        assertThat("Group members were not correctly set", sdkSigner1.getGroup().getMembers(), hasSize(1));
        assertThat("Group member email was not correctly set", sdkSigner1.getGroup().getMembers().get(0).getEmail(), is(apiSigner1.getGroup().getMembers().get(0).getEmail()));
        assertThat("Custom ID was not correctly set", sdkSigner1.getId(), is(apiSigner1.getId()));
        assertThat("Role ID was not correctly set", sdkSigner1.getId(), is(apiRole.getId()));
        assertThat("Signing order was not correctly set", sdkSigner1.getSigningOrder(), is(apiRole.getIndex()));
        assertThat("Can change signer was not correctly set", sdkSigner1.canChangeSigner(), is(apiRole.getReassign()));
        assertThat("Email message was not correctly set", sdkSigner1.getMessage(), is(apiRole.getEmailMessage().getContent()));
        assertThat("Locked was not correctly set", sdkSigner1.isLocked(), is(apiRole.getLocked()));
    }

    private com.silanis.esl.api.model.Role createTypicalAPIAdHocGroupRole() {
        final com.silanis.esl.api.model.Role role = new com.silanis.esl.api.model.Role();
        role.setId("signerId");
        role.setIndex(1);
        role.setReassign(true);
        final BaseMessage message = new BaseMessage();
        message.setContent("Ad-hoc group message");
        role.setEmailMessage(message);
        role.setLocked(true);

        final com.silanis.esl.api.model.Signer signer = new com.silanis.esl.api.model.Signer();
        signer.setEmail(AdHocGroupUtils.generateAdHocGroupEmail());
        signer.setFirstName("AdHocGroup");
        signer.setCompany("AdHocCompany");
        signer.setLanguage("en");
        signer.setSignerType(AdHocGroupUtils.AD_HOC_GROUP_SIGNER_TYPE);
        signer.setId("signerId");

        final com.silanis.esl.api.model.Group group = new com.silanis.esl.api.model.Group();
        group.setName("AdHocGroup");
        final com.silanis.esl.api.model.GroupMember member = new com.silanis.esl.api.model.GroupMember();
        member.setEmail("member@email.com");
        member.setFirstName("MemberFirstName");
        member.setLastName("MemberLastName");
        member.setMemberType("REGULAR");
        group.addMember(member);
        signer.setGroup(group);

        final List<com.silanis.esl.api.model.Signer> signers = new ArrayList<>();
        signers.add(signer);
        role.setSigners(signers);

        return role;
    }

    /**
     * Create an SDK Signer.
     *
     * @return SDK Signer.
     */
    private com.silanis.esl.sdk.Signer createTypicalSDKSigner() {

        return SignerBuilder.newSignerWithEmail("abc@test.com")
                .canChangeSigner()
                .deliverSignedDocumentsByEmail()
                .signingOrder(1)
                .withCompany("ABC Inc.")
                .withLanguage(Locale.FRENCH)
                .withCustomId("1")
                .withFirstName("first name")
                .withLastName("last name")
                .withEmailMessage("Email message.")
                .withTitle("Miss")
                .withAttachmentRequirement(AttachmentRequirementBuilder.newAttachmentRequirementWithName("driver license")
                        .withDescription("Please upload your scanned driver license.")
                        .isRequiredAttachment()
                        .build())
                .withNotificationMethods(newNotificationMethods()
                        .withPrimaryMethods(com.silanis.esl.sdk.NotificationMethod.EMAIL)
                )
                .build();
    }

    /**
     * Create an API Role.
     *
     * @return API Role.
     */
    private com.silanis.esl.api.model.Role createTypicalAPIRole() {
        com.silanis.esl.api.model.Role apiRole = new com.silanis.esl.api.model.Role();

        com.silanis.esl.api.model.Signer apiSigner = new com.silanis.esl.api.model.Signer();
        apiSigner.setEmail("test@abc.com");
        apiSigner.setFirstName("Signer first name");
        apiSigner.setLastName("Signer last name");
        apiSigner.setCompany("ABC Inc.");
        apiSigner.setLanguage("fr");
        apiSigner.setTitle("Doctor");
        apiSigner.setSignerType("THIRD_PARTY_SIGNER");

        Set<NotificationMethod> methods = new HashSet<>();
        methods.add(NotificationMethod.EMAIL);
        apiSigner.setNotificationMethods(new NotificationMethods(methods));

        Delivery delivery = new Delivery();
        delivery.setDownload(true);
        delivery.setEmail(true);

        apiSigner.setDelivery(delivery);
        apiSigner.setId("1");

        List<Signer> signers = new ArrayList<Signer>();
        signers.add(apiSigner);
        apiRole.setSigners(signers);

        apiRole.setId("3");
        apiRole.setName("Signer name");
        apiRole.setIndex(0);
        apiRole.setReassign(true);
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setContent("Base message content.");
        apiRole.setEmailMessage(baseMessage);
        apiRole.setLocked(true);

        AttachmentRequirement attachmentRequirement = new AttachmentRequirement();
        attachmentRequirement.setName("Driver license");
        attachmentRequirement.setDescription("Please upload your scanned driver license.");
        attachmentRequirement.setStatus("INCOMPLETE");
        attachmentRequirement.setRequired(true);
        attachmentRequirement.setComment("Attachment was not uploaded");
        apiRole.addAttachmentRequirement(attachmentRequirement);

        return apiRole;
    }


}
