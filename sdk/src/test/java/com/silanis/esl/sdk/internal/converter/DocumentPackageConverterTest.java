
package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Message;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.builder.PackageBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 3:16 PM
 * <p>
 * Test DocumentPackageConverter.
 */
public class DocumentPackageConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.Package apiPackage1 = null;
    private com.silanis.esl.api.model.Package apiPackage2 = null;
    private com.silanis.esl.sdk.DocumentPackage sdkDocumentPackage1 = null;
    private com.silanis.esl.sdk.DocumentPackage sdkDocumentPackage2 = null;
    private DocumentPackageConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkDocumentPackage1 = null;
        converter = new DocumentPackageConverter(sdkDocumentPackage1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIPackage(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiPackage1 = null;
        converter = new DocumentPackageConverter(apiPackage1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKPackage(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkDocumentPackage1 = null;
        converter = new DocumentPackageConverter(sdkDocumentPackage1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKPackage(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiPackage1 = null;
        converter = new DocumentPackageConverter(apiPackage1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIPackage(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkDocumentPackage1 = createTypicalSDKDocumentPackage();
        sdkDocumentPackage2 = new DocumentPackageConverter(sdkDocumentPackage1).toSDKPackage();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkDocumentPackage2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkDocumentPackage2, is(sdkDocumentPackage1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiPackage1 = createTypicalAPIDocumentPackage();
        apiPackage2 = new DocumentPackageConverter(apiPackage1).toAPIPackage();

        assertThat("Converter returned a null api object for a non null api object", apiPackage2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiPackage2, is(apiPackage1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiPackage1 = createTypicalAPIDocumentPackage();
        sdkDocumentPackage1 = new DocumentPackageConverter(apiPackage1).toSDKPackage();

        assertThat("Converter returned a null sdk object for a non null api object", sdkDocumentPackage1, notNullValue());
        assertThat("ID was not correctly set", apiPackage1.getId(), is(sdkDocumentPackage1.getId().toString()));
        assertThat("Language was not correctly set", apiPackage1.getLanguage(), is(sdkDocumentPackage1.getLanguage().getLanguage()));
        assertThat("Auto complete was not correctly set", apiPackage1.getAutocomplete(), is(sdkDocumentPackage1.getAutocomplete()));
        assertThat("Description was not correctly set", apiPackage1.getDescription(), is(sdkDocumentPackage1.getDescription()));
        assertThat("Due date was not correctly set", apiPackage1.getDue(), is(sdkDocumentPackage1.getExpiryDate()));
        assertThat("Message was not correctly set", apiPackage1.getEmailMessage(), is(sdkDocumentPackage1.getPackageMessage()));
        assertThat("Name was not correctly set", apiPackage1.getName(), is(sdkDocumentPackage1.getName()));
        assertThat("Status was not correctly set", apiPackage1.getStatus(), is(sdkDocumentPackage1.getStatus().getApiValue()));
        assertThat("Message status was not correctly set", apiPackage1.getMessages().get(0).getStatus(), is(sdkDocumentPackage1.getMessages().get(0).getStatus().toString()));
        assertThat("Message content was not correctly set", apiPackage1.getMessages().get(0).getContent(), is(sdkDocumentPackage1.getMessages().get(0).getContent()));
        assertThat("Sender email address was not correctly set", apiPackage1.getSender().getEmail(), is(sdkDocumentPackage1.getSenderInfo().getEmail()));
        assertThat("Creation date was not correctly set", apiPackage1.getCreated(), is(sdkDocumentPackage1.getCreatedDate()));
        assertThat("Timezone ID was not correctly set", apiPackage1.getTimezoneId(), is(sdkDocumentPackage1.getTimezoneId()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkDocumentPackage1 = createTypicalSDKDocumentPackage();
        apiPackage1 = new DocumentPackageConverter(sdkDocumentPackage1).toAPIPackage();

        assertThat("Converter returned a null api object for a non null sdk object", apiPackage1, notNullValue());
        assertThat("ID was not correctly set", apiPackage1.getId(), is(sdkDocumentPackage1.getId().toString()));
        assertThat("Language was not correctly set", apiPackage1.getLanguage(), is(sdkDocumentPackage1.getLanguage().getLanguage()));
        assertThat("Auto complete was not correctly set", apiPackage1.getAutocomplete(), is(sdkDocumentPackage1.getAutocomplete()));
        assertThat("Description was not correctly set", apiPackage1.getDescription(), is(sdkDocumentPackage1.getDescription()));
        assertThat("Due date was not correctly set", apiPackage1.getDue(), is(sdkDocumentPackage1.getExpiryDate()));
        assertThat("Message was not correctly set", apiPackage1.getEmailMessage(), is(sdkDocumentPackage1.getPackageMessage()));
        assertThat("Name was not correctly set", apiPackage1.getName(), is(sdkDocumentPackage1.getName()));
        assertThat("Status was not correctly set", apiPackage1.getStatus(), is(sdkDocumentPackage1.getStatus().getApiValue()));
        assertThat("Timezone ID was not correctly set", apiPackage1.getTimezoneId(), is(sdkDocumentPackage1.getTimezoneId()));
    }

    /**
     * Create an SDK DocumentPackage.
     *
     * @return SDK DocumentPackage.
     */
    private com.silanis.esl.sdk.DocumentPackage createTypicalSDKDocumentPackage() {
        com.silanis.esl.sdk.DocumentPackage sdkDocumentPackage = PackageBuilder.newPackageNamed("SDK Package Name")
                .withID(new PackageId("packageId"))
                .withStatus(PackageStatus.DRAFT)
                .describedAs("typical description")
                .withEmailMessage("typical email message")
                .withLanguage(Locale.ENGLISH)
                .withTimezoneId("Canada/Mountain")
                .build();
        return sdkDocumentPackage;
    }

    /**
     * Create an API DocumentPackage.
     *
     * @return API DocumentPackage.
     */
    private com.silanis.esl.api.model.Package createTypicalAPIDocumentPackage() {
        com.silanis.esl.api.model.Package apiDocumentPackage = new com.silanis.esl.api.model.Package();
        apiDocumentPackage.setId("1");
        apiDocumentPackage.setLanguage("en");
        apiDocumentPackage.setAutocomplete(true);
        apiDocumentPackage.setConsent("Consent");
        apiDocumentPackage.setCompleted(new Date());
        apiDocumentPackage.setDescription("API document package description");
        apiDocumentPackage.setDue(new Date());
        apiDocumentPackage.setName("API package name");
        apiDocumentPackage.setStatus(PackageStatus.DRAFT.getApiValue());
        apiDocumentPackage.setCreated(new Date());
        apiDocumentPackage.setTimezoneId("Canada/Mountain");

        Message apiMessage = new Message();
        apiMessage.setContent("opt-out reason");
        apiMessage.setStatus("NEW");
        User fromUser = new User();
        fromUser.setFirstName("John");
        fromUser.setLastName("Smith");
        fromUser.setEmail("email@email.com");
        apiMessage.setFrom(fromUser);
        apiDocumentPackage.setMessages(new ArrayList<Message>(Collections.singletonList(apiMessage)));

        Sender sender = new Sender();
        sender.setEmail("sender@email.com");
        apiDocumentPackage.setSender(sender);

        return apiDocumentPackage;
    }

}
