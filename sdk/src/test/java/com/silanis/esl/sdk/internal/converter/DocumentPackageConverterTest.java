
package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Message;
import com.silanis.esl.api.model.Sender;
import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Placeholder;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.internal.Serialization;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
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

    private static final String FIRST_SIGNER_ID = "firstSignerWithoutEmail";
    private static final String SECOND_SIGNER_ID = "secondSignerWithoutEmail";

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
     * Signatures assigned by custom id to two signers without email go to their own signer's role.
     */
    @Test
    public void convertPackageWithTwoSignersWithoutEmailAssignsSignaturesById() {
        sdkDocumentPackage1 = PackageBuilder.newPackageNamed("Signers without email")
                .withSigner(newSignerWithEmail("")
                        .withCustomId(FIRST_SIGNER_ID)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withSigner(newSignerWithEmail("")
                        .withCustomId(SECOND_SIGNER_ID)
                        .withFirstName("Jane")
                        .withLastName("Doe"))
                .withDocument(newDocumentWithName("Contract")
                        .fromStream(new ByteArrayInputStream(new byte[0]), DocumentType.PDF)
                        .withSignature(signatureFor(new Placeholder(FIRST_SIGNER_ID))
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(new Placeholder(SECOND_SIGNER_ID))
                                .onPage(0)
                                .atPosition(100, 300)))
                .build();

        apiPackage1 = new DocumentPackageConverter(sdkDocumentPackage1).toAPIPackage();

        assertThat("Both signers must have their own role", apiPackage1.getRoles().size(), is(2));
        assertThat("First role id was not set from the custom id", apiPackage1.getRoles().get(0).getId(), is(FIRST_SIGNER_ID));
        assertThat("First signer email was not kept empty", apiPackage1.getRoles().get(0).getSigners().get(0).getEmail(), is(""));
        assertThat("Second role id was not set from the custom id", apiPackage1.getRoles().get(1).getId(), is(SECOND_SIGNER_ID));
        assertThat("Second signer email was not kept empty", apiPackage1.getRoles().get(1).getSigners().get(0).getEmail(), is(""));

        com.silanis.esl.api.model.Document apiDocument =
                new DocumentConverter(sdkDocumentPackage1.getDocument("Contract")).toAPIDocument(apiPackage1);

        assertThat("Both signatures must be converted", apiDocument.getApprovals().size(), is(2));
        assertThat("First signature was not assigned to the first signer's role",
                apiDocument.getApprovals().get(0).getRole(), is(FIRST_SIGNER_ID));
        assertThat("Second signature was not assigned to the second signer's role",
                apiDocument.getApprovals().get(1).getRole(), is(SECOND_SIGNER_ID));
    }

    /**
     * A package with two SIGNER roles with "email": "" is read, and each signer can be found by its id.
     */
    @Test
    public void convertAPIPackageWithTwoSignersWithoutEmailToSDK() {
        apiPackage1 = Serialization.fromJson("{"
                + "\"id\":\"packageId\","
                + "\"name\":\"Signers without email\","
                + "\"status\":\"DRAFT\","
                + "\"roles\":["
                + roleJsonWithoutEmail(FIRST_SIGNER_ID, "John", "Smith", 1)
                + ","
                + roleJsonWithoutEmail(SECOND_SIGNER_ID, "Jane", "Doe", 2)
                + "]}", com.silanis.esl.api.model.Package.class);

        sdkDocumentPackage1 = new DocumentPackageConverter(apiPackage1).toSDKPackage();

        assertThat("Both signers must be read", sdkDocumentPackage1.getSigners().size(), is(2));
        Signer first = sdkDocumentPackage1.getSignerById(FIRST_SIGNER_ID);
        Signer second = sdkDocumentPackage1.getSignerById(SECOND_SIGNER_ID);
        assertThat("First signer was not found by id", first, notNullValue());
        assertThat("First signer email was not read as empty", first.getEmail(), is(""));
        assertThat("First signer first name was not read", first.getFirstName(), is("John"));
        assertThat("Second signer was not found by id", second, notNullValue());
        assertThat("Second signer email was not read as empty", second.getEmail(), is(""));
        assertThat("Second signer first name was not read", second.getFirstName(), is("Jane"));
    }

    /**
     * Signatures read for two signers without email stay on their own signer's role when sent back.
     */
    @Test
    public void readSignaturesOfTwoSignersWithoutEmailKeepTheirRolesWhenSentBack() {
        apiPackage1 = Serialization.fromJson("{"
                + "\"id\":\"packageId\","
                + "\"name\":\"Signers without email\","
                + "\"status\":\"DRAFT\","
                + "\"roles\":["
                + roleJsonWithoutEmail(FIRST_SIGNER_ID, "John", "Smith", 1)
                + ","
                + roleJsonWithoutEmail(SECOND_SIGNER_ID, "Jane", "Doe", 2)
                + "],"
                + "\"documents\":[{"
                + "\"id\":\"documentId\","
                + "\"name\":\"Contract\","
                + "\"index\":0,"
                + "\"approvals\":["
                + approvalJson("firstSignature", FIRST_SIGNER_ID, 100)
                + ","
                + approvalJson("secondSignature", SECOND_SIGNER_ID, 300)
                + "]}]"
                + "}", com.silanis.esl.api.model.Package.class);

        sdkDocumentPackage1 = new DocumentPackageConverter(apiPackage1).toSDKPackage();
        com.silanis.esl.api.model.Document sentBack =
                new DocumentConverter(sdkDocumentPackage1.getDocument("Contract")).toAPIDocument(apiPackage1);

        Map<String, String> roleBySignatureId = new HashMap<>();
        for (Approval approval : sentBack.getApprovals()) {
            roleBySignatureId.put(approval.getId(), approval.getRole());
        }
        assertThat("First signature was not kept on the first signer's role",
                roleBySignatureId.get("firstSignature"), is(FIRST_SIGNER_ID));
        assertThat("Second signature was not kept on the second signer's role",
                roleBySignatureId.get("secondSignature"), is(SECOND_SIGNER_ID));
    }

    private static String approvalJson(String id, String roleId, int top) {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"role\":\"" + roleId + "\","
                + "\"optional\":false,"
                + "\"disabled\":false,"
                + "\"enforceCaptureSignature\":false,"
                + "\"fields\":[{"
                + "\"id\":\"" + id + "Field\","
                + "\"type\":\"SIGNATURE\","
                + "\"subtype\":\"FULLNAME\","
                + "\"page\":0,"
                + "\"left\":100,"
                + "\"top\":" + top + ","
                + "\"width\":200,"
                + "\"height\":50"
                + "}]"
                + "}";
    }

    private static String roleJsonWithoutEmail(String id, String firstName, String lastName, int index) {
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"name\":\"" + id + "\","
                + "\"type\":\"SIGNER\","
                + "\"index\":" + index + ","
                + "\"signers\":[{"
                + "\"id\":\"" + id + "\","
                + "\"email\":\"\","
                + "\"firstName\":\"" + firstName + "\","
                + "\"lastName\":\"" + lastName + "\","
                + "\"auth\":{\"scheme\":\"NONE\",\"challenges\":[]},"
                + "\"notificationMethods\":{\"primary\":[]}"
                + "}]"
                + "}";
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
