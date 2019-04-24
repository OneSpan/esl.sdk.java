package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.EslException;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.AttachmentRequirementBuilder.newAttachmentRequirementWithName;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-12.
 */
public class AttachmentRequirementBuilderTest {

    @Test
    public void buildWithSpecificValues() {
        String name = "Driver's license";
        String description = "Please upload driver's license.";
        boolean isRequired = true;

        AttachmentRequirement attachmentRequirement = newAttachmentRequirementWithName(name)
                .withDescription(description)
                .isRequiredAttachment()
                .build();

        assertThat("Attachment's name was not set correctly.", attachmentRequirement.getName(), is(name));
        assertThat("Attachment's description was not set correctly.", attachmentRequirement.getDescription(), is(description));
        assertThat("Attachment's required property was not set correctly", attachmentRequirement.isRequired(), is(isRequired));
    }

    @Test(expected = EslException.class)
    public void attachmentNameCannotBeNull() {
        newAttachmentRequirementWithName(null).build();
    }

    @Test(expected = EslException.class)
    public void attachmentNameCannotBeEmptyString() {
        newAttachmentRequirementWithName("").build();
    }

}
