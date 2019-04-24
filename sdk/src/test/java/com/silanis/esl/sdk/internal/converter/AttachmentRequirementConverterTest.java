package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.builder.AttachmentRequirementBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-05-09.
 */
public class AttachmentRequirementConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.AttachmentRequirement sdkAttachmentRequirement1 = null;
    private com.silanis.esl.sdk.AttachmentRequirement sdkAttachmentRequirement2 = null;
    private com.silanis.esl.api.model.AttachmentRequirement apiAttachmentRequirement1 = null;
    private com.silanis.esl.api.model.AttachmentRequirement apiAttachmentRequirement2 = null;
    private AttachmentRequirementConverter converter;


    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkAttachmentRequirement1 = null;
        converter = new AttachmentRequirementConverter(sdkAttachmentRequirement1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIAttachmentRequirement(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiAttachmentRequirement1 = null;
        converter = new AttachmentRequirementConverter(apiAttachmentRequirement1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKAttachmentRequirement(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkAttachmentRequirement1 = null;
        converter = new AttachmentRequirementConverter(sdkAttachmentRequirement1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKAttachmentRequirement(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiAttachmentRequirement1 = null;
        converter = new AttachmentRequirementConverter(apiAttachmentRequirement1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIAttachmentRequirement(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkAttachmentRequirement1 = createTypicalSDKAttachmentRequirement();
        sdkAttachmentRequirement2 = new AttachmentRequirementConverter(sdkAttachmentRequirement1).toSDKAttachmentRequirement();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkAttachmentRequirement2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkAttachmentRequirement2, is(sdkAttachmentRequirement1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiAttachmentRequirement1 = createTypicalAPIAttachmentRequirement();
        apiAttachmentRequirement2 = new AttachmentRequirementConverter(apiAttachmentRequirement1).toAPIAttachmentRequirement();

        assertThat("Converter returned a null api object for a non null api object", apiAttachmentRequirement2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiAttachmentRequirement2, is(apiAttachmentRequirement1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiAttachmentRequirement1 = createTypicalAPIAttachmentRequirement();
        sdkAttachmentRequirement1 = new AttachmentRequirementConverter(apiAttachmentRequirement1).toSDKAttachmentRequirement();

        assertThat("Attachment's name was not set correctly", sdkAttachmentRequirement1.getName(), is(apiAttachmentRequirement1.getName()));
        assertThat("Attachment's description was not set correctly", sdkAttachmentRequirement1.getDescription(), is(apiAttachmentRequirement1.getDescription()));
        assertThat("Attachment's ID was not set correctly", sdkAttachmentRequirement1.getId(), is(apiAttachmentRequirement1.getId()));
        assertThat("Attachment's required property was not set correctly", sdkAttachmentRequirement1.isRequired(), is(apiAttachmentRequirement1.getRequired()));
        assertThat("Attachment's status was not set correctly", sdkAttachmentRequirement1.getStatus().toString(), is(apiAttachmentRequirement1.getStatus()));
        assertThat("Attachment's comments was not set correctly", sdkAttachmentRequirement1.getSenderComment(), is(apiAttachmentRequirement1.getComment()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkAttachmentRequirement1 = createTypicalSDKAttachmentRequirement();
        apiAttachmentRequirement1 = new AttachmentRequirementConverter(sdkAttachmentRequirement1).toAPIAttachmentRequirement();

        assertThat("Attachment's name was not set correctly", apiAttachmentRequirement1.getName(), is(sdkAttachmentRequirement1.getName()));
        assertThat("Attachment's description was not set correctly", apiAttachmentRequirement1.getDescription(), is(sdkAttachmentRequirement1.getDescription()));
        assertThat("Attachment's required property was not set correctly", apiAttachmentRequirement1.getRequired(), is(sdkAttachmentRequirement1.isRequired()));
        assertThat("Attachment's ID was not set correctly", apiAttachmentRequirement1.getId(), is(sdkAttachmentRequirement1.getId()));
        assertThat("Attachment's status was not set correctly", apiAttachmentRequirement1.getStatus(), is(sdkAttachmentRequirement1.getStatus().toString()));
        assertThat("Attachment's comments was not set correctly", apiAttachmentRequirement1.getComment(), is(sdkAttachmentRequirement1.getSenderComment()));
    }

    /**
     * Create a SDK AttachmentRequirement object.
     *
     * @return SDKAttachmentRequirement
     */
    private com.silanis.esl.sdk.AttachmentRequirement createTypicalSDKAttachmentRequirement() {
        AttachmentRequirement attachmentRequirement = AttachmentRequirementBuilder.newAttachmentRequirementWithName("Driver's license")
                .withDescription("Please upload a scanned copy of your driver's license")
                .isRequiredAttachment()
                .build();
        attachmentRequirement.setId("attachmentId");

        return attachmentRequirement;
    }

    /**
     * Creates a API AttachmentRequirement object.
     *
     * @return AttachmentRequirement
     */
    private com.silanis.esl.api.model.AttachmentRequirement createTypicalAPIAttachmentRequirement() {
        com.silanis.esl.api.model.AttachmentRequirement attachmentRequirement = new com.silanis.esl.api.model.AttachmentRequirement();
        attachmentRequirement.setName("Driver's license");
        attachmentRequirement.setId("attachment1");
        attachmentRequirement.setDescription("Please upload a scanned copy of your driver's license");
        attachmentRequirement.setRequired(true);

        return attachmentRequirement;
    }
}
