package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.builder.AttachmentRequirementBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 03/12/13
 * Time: 2:00 PM
 * <p/>
 * Test SignerConverter
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
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISigner(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSigner1 = null;
        converter = new SignerConverter(apiRole);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSigner(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSigner1 = null;
        converter = new SignerConverter(sdkSigner1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSigner(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiRole = null;
        converter = new SignerConverter(apiRole);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISigner(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSigner1 = createTypicalSDKSigner();
        sdkSigner2 = new SignerConverter(sdkSigner1).toSDKSigner();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSigner2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSigner2, is(equalTo(sdkSigner1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiRole = createTypicalAPIRole();
        apiSigner2 = new SignerConverter(apiRole).toAPISigner();

        assertThat("Converter returned a null api object for a non null api object", apiSigner2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiSigner2, is(equalTo(apiRole.getSigners().get(0))));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiRole = createTypicalAPIRole();
        apiSigner1 = apiRole.getSigners().get(0);

        sdkSigner1 = new SignerConverter(apiRole).toSDKSigner();

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, is(notNullValue()));
        assertThat("Email was not correctly set", apiSigner1.getEmail(), is(equalTo(sdkSigner1.getEmail())));
        assertThat("First name was not correctly set", apiSigner1.getFirstName(), is(equalTo(sdkSigner1.getFirstName())));
        assertThat("Last name was not correctly set", apiSigner1.getLastName(), is(equalTo(sdkSigner1.getLastName())));
        assertThat("Company was not correctly set", apiSigner1.getCompany(), is(equalTo(sdkSigner1.getCompany())));
        assertThat("Title was not correctly set", apiSigner1.getTitle(), is(equalTo(sdkSigner1.getTitle())));
        assertThat("Authenticated signing flag was not correctly set", apiSigner1.getAuths(), hasSize(1));
        assertThat("Authenticated signing flag was not correctly set", apiSigner1.getAuths().get(0).getScheme(), is("CERTIFICATE"));
        assertThat("Signer ID was not correctly set", apiRole.getId(), is(equalTo(sdkSigner1.getId())));
        assertThat("Signing order was not correctly set", apiRole.getIndex(), is(equalTo(sdkSigner1.getSigningOrder())));
        assertThat("Can change signer flag was not correctly set", apiRole.getReassign(), is(equalTo(sdkSigner1.canChangeSigner())));
        assertThat("Email was not correctly set", apiRole.getEmailMessage().getContent(), is(equalTo(sdkSigner1.getMessage())));

        String attachmentName = apiRole.getAttachmentRequirements().get(0).getName();
        assertThat("Attachment's name was not set correctly", attachmentName, is(equalTo(sdkSigner1.getAttachmentRequirement(attachmentName).getName())));
        assertThat("Attachment's description was not set correctly", apiRole.getAttachmentRequirements().get(0).getDescription(), is(equalTo(sdkSigner1.getAttachmentRequirement(attachmentName).getDescription())));
        assertThat("Attachment's required property was not set correctly", apiRole.getAttachmentRequirements().get(0).getRequired(), is(sdkSigner1.getAttachmentRequirement(attachmentName).isRequired()));
        assertThat("Attachment's status was not set correctly", apiRole.getAttachmentRequirements().get(0).getStatus().toString(), is(sdkSigner1.getAttachmentRequirement(attachmentName).getStatus().toString()));
        assertThat("Attachment's comments was not set correctly", apiRole.getAttachmentRequirements().get(0).getComment(), is(Matchers.equalTo(sdkSigner1.getAttachmentRequirement(attachmentName).getSenderComment())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSigner1 = createTypicalSDKSigner();
        apiSigner1 = new SignerConverter(sdkSigner1).toAPISigner();
        assertThat("Converter returned a null api object for a non null sdk object", apiSigner1, is(notNullValue()));
        assertThat("Email was not correctly set", apiSigner1.getEmail(), is(equalTo(sdkSigner1.getEmail())));
        assertThat("First name was not correctly set", apiSigner1.getFirstName(), is(equalTo(sdkSigner1.getFirstName())));
        assertThat("Last name was not correctly set", apiSigner1.getLastName(), is(equalTo(sdkSigner1.getLastName())));
        assertThat("Company was not correctly set", apiSigner1.getCompany(), is(equalTo(sdkSigner1.getCompany())));
        assertThat("Title was not correctly set", apiSigner1.getTitle(), is(equalTo(sdkSigner1.getTitle())));

    }

    @Test
    public void convertSDKSignerToAPIRole() {

        sdkSigner1 = createTypicalSDKSigner();
        String roleId = UUID.randomUUID().toString().replace("-", "");
        apiRole = new SignerConverter(sdkSigner1).toAPIRole(roleId);

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, is(notNullValue()));
        assertThat("Email was not correctly set", apiRole.getSigners().get(0).getEmail(),
                is(equalTo(sdkSigner1.getEmail())));
        assertThat("First name was not correctly set", apiRole.getSigners().get(0).getFirstName(),
                is(equalTo(sdkSigner1.getFirstName())));
        assertThat("Last name was not correctly set", apiRole.getSigners().get(0).getLastName(),
                is(equalTo(sdkSigner1.getLastName())));
        assertThat("Company was not correctly set", apiRole.getSigners().get(0).getCompany(),
                is(equalTo(sdkSigner1.getCompany())));
        assertThat("Title was not correctly set", apiRole.getSigners().get(0).getTitle(),
                is(equalTo(sdkSigner1.getTitle())));

        assertThat("ID was not set correctly", apiRole.getId().toString(), is(equalTo(sdkSigner1.getId())));
        assertThat("Name was not set correctly", apiRole.getName().toString(), is(equalTo(sdkSigner1.getId())));
        assertThat("Message was not set correctly", apiRole.getEmailMessage().getContent(), is(equalTo(sdkSigner1.getMessage())));

        String attachmentName = apiRole.getAttachmentRequirements().get(0).getName();
        assertThat("Attachment's name was not set correctly", attachmentName, is(equalTo(sdkSigner1.getAttachmentRequirement(attachmentName).getName())));
        assertThat("Attachment's description was not set correctly", apiRole.getAttachmentRequirements().get(0).getDescription(), is(equalTo(sdkSigner1.getAttachmentRequirement(attachmentName).getDescription())));
        assertThat("Attachment's required property was not set correctly", apiRole.getAttachmentRequirements().get(0).getRequired(), is(sdkSigner1.getAttachmentRequirement(attachmentName).isRequired()));
    }

    @Test
    public void convertSDKSignerWithNullEntriesToAPIRole() {
        String roleId = UUID.randomUUID().toString().replace("-", "");
        sdkSigner1 = SignerBuilder.newSignerWithEmail("abc@test.com")
                .canChangeSigner()
                .deliverSignedDocumentsByEmail()
                .signingOrder(1)
                .withCompany("ABC Inc.")
                .withFirstName("first name")
                .withLastName("last name")
                .withTitle("Miss")
                .build();

        apiRole = new SignerConverter(sdkSigner1).toAPIRole(roleId);

        assertThat("Converter returned a null api object for a non null sdk object", apiRole, is(notNullValue()));
        assertThat("Email was not correctly set", apiRole.getSigners().get(0).getEmail(),
                is(equalTo(sdkSigner1.getEmail())));
        assertThat("First name was not correctly set", apiRole.getSigners().get(0).getFirstName(),
                is(equalTo(sdkSigner1.getFirstName())));
        assertThat("Last name was not correctly set", apiRole.getSigners().get(0).getLastName(),
                is(equalTo(sdkSigner1.getLastName())));
        assertThat("Company was not correctly set", apiRole.getSigners().get(0).getCompany(),
                is(equalTo(sdkSigner1.getCompany())));
        assertThat("Title was not correctly set", apiRole.getSigners().get(0).getTitle(),
                is(equalTo(sdkSigner1.getTitle())));

        assertThat("ID was not set correctly", apiRole.getId().toString(), is(equalTo(roleId)));
        assertThat("Name was not set correctly", apiRole.getName().toString(), is(equalTo(roleId)));
        assertThat("Message was not set correctly", apiRole.getEmailMessage(), is(nullValue()));
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
                .withCustomId("1")
                .withFirstName("first name")
                .withLastName("last name")
                .withEmailMessage("Email message.")
                .withTitle("Miss")
                .withAttachmentRequirement(AttachmentRequirementBuilder.newAttachmentRequirementWithName("driver license")
                        .withDescription("Please upload your scanned driver license.")
                        .isRequiredAttachment()
                        .build())
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
        apiSigner.setTitle("Doctor");

        List<Auth> auths = new ArrayList<Auth>();
        Auth auth = new Auth();
        auth.setScheme("CERTIFICATE");
        auths.add(auth);
        apiSigner.setAuths(auths);

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
