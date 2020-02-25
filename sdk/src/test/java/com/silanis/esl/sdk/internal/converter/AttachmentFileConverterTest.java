package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class AttachmentFileConverterTest implements ConverterTest {


    private static final Date NOW = new Date();

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.AttachmentFile attachmentFile =
                new AttachmentFileConverter((com.silanis.esl.sdk.AttachmentFile) null).toApiAttachmentFile();

        assertNull("Converter didn't return a null api object for a null sdk object", attachmentFile);
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        com.silanis.esl.api.model.AttachmentFile attachmentFile =
                new AttachmentFileConverter((com.silanis.esl.sdk.AttachmentFile) null).toApiAttachmentFile();

        assertNull("Converter didn't return a null sdk object for a null api object", attachmentFile);

    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        com.silanis.esl.sdk.AttachmentFile attachmentFile =
                new AttachmentFileConverter((com.silanis.esl.sdk.AttachmentFile) null).toSDKAttachmentFile();

        assertNull("Converter didn't return a null sdk object for a null sdk object", attachmentFile);
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        com.silanis.esl.api.model.AttachmentFile attachmentFile =
                new AttachmentFileConverter((com.silanis.esl.api.model.AttachmentFile) null).toApiAttachmentFile();

        assertNull("Converter didn't return a null api object for a null api object", attachmentFile);
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile1 = createSdkAttachmentFile();
        com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile2 = new AttachmentFileConverter(sdkAttachmentFile1).toSDKAttachmentFile();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkAttachmentFile2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkAttachmentFile2, is(sdkAttachmentFile1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        com.silanis.esl.api.model.AttachmentFile apiAttachmentFile1 = createApiAttachmentFile();
        com.silanis.esl.api.model.AttachmentFile apiAttachmentFile2 = new AttachmentFileConverter(apiAttachmentFile1).toApiAttachmentFile();

        assertThat("Converter returned a null sdk object for a non null sdk object", apiAttachmentFile2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiAttachmentFile2, is(apiAttachmentFile1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        com.silanis.esl.api.model.AttachmentFile apiAttachmentFile1 = createApiAttachmentFile();
        com.silanis.esl.sdk.AttachmentFile  sdkAttachmentFile1 = new AttachmentFileConverter(apiAttachmentFile1).toSDKAttachmentFile();

        assertThat("Converter returned a null api object for a non null api object", sdkAttachmentFile1, notNullValue());
        assertThat("Attachment file's id was not set correctly", sdkAttachmentFile1.getId(), is(apiAttachmentFile1.getId()));
        assertThat("Attachment file's insert date was not set correctly", sdkAttachmentFile1.getInsertDate(), is(apiAttachmentFile1.getInsertDate()));
        assertThat("Attachment file's name was not set correctly", sdkAttachmentFile1.getName(), is(apiAttachmentFile1.getName()));
        assertThat("Attachment file's preview was not set correctly", sdkAttachmentFile1.isPreview(), is(apiAttachmentFile1.isPreview()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile1 = createSdkAttachmentFile();
        com.silanis.esl.api.model.AttachmentFile  apiAttachmentFile1 = new AttachmentFileConverter(sdkAttachmentFile1).toApiAttachmentFile();

        assertThat("Converter returned a null api object for a non null api object", apiAttachmentFile1, notNullValue());
        assertThat("Attachment file's id was not set correctly", apiAttachmentFile1.getId(), is(sdkAttachmentFile1.getId()));
        assertThat("Attachment file's insert date was not set correctly", apiAttachmentFile1.getInsertDate(), is(sdkAttachmentFile1.getInsertDate()));
        assertThat("Attachment file's name was not set correctly", apiAttachmentFile1.getName(), is(sdkAttachmentFile1.getName()));
        assertThat("Attachment file's preview was not set correctly", apiAttachmentFile1.isPreview(), is(sdkAttachmentFile1.isPreview()));
    }

    private com.silanis.esl.api.model.AttachmentFile createApiAttachmentFile() {
        com.silanis.esl.api.model.AttachmentFile apiAttachmentFile = new com.silanis.esl.api.model.AttachmentFile();
        apiAttachmentFile.setId(1);
        apiAttachmentFile.setInsertDate(NOW.getTime());
        apiAttachmentFile.setName("apiAttachmentFile.jpeg");
        apiAttachmentFile.setPreview(true);
        return apiAttachmentFile;
    }

    private com.silanis.esl.sdk.AttachmentFile createSdkAttachmentFile() {
        com.silanis.esl.sdk.AttachmentFile sdkAttachmentFile = new com.silanis.esl.sdk.AttachmentFile();
        sdkAttachmentFile.setId(1);
        sdkAttachmentFile.setInsertDate(NOW);
        sdkAttachmentFile.setName("apiAttachmentFile.jpeg");
        sdkAttachmentFile.setPreview(true);
        return sdkAttachmentFile;
    }
}
