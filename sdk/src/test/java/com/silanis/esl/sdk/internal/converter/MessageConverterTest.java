package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.MessageStatus;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by lena on 2014-07-03.
 */
public class MessageConverterTest implements ConverterTest {

    private com.silanis.esl.api.model.Message apiMessage1 = null;
    private com.silanis.esl.api.model.Message apiMessage2 = null;
    private com.silanis.esl.sdk.Message sdkMessage1 = null;
    private com.silanis.esl.sdk.Message sdkMessage2 = null;
    private MessageConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkMessage1 = null;
        converter = new MessageConverter(sdkMessage1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIMessage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiMessage1 = null;
        converter = new MessageConverter(apiMessage1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKMessage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkMessage1 = null;
        converter = new MessageConverter(sdkMessage1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKMessage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiMessage1 = null;
        converter = new MessageConverter(apiMessage1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIMessage(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkMessage1 = createTypicalSDKMessage();
        sdkMessage2 = new MessageConverter(sdkMessage1).toSDKMessage();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkMessage2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkMessage2, is(equalTo(sdkMessage1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiMessage1 = createTypicalAPIMessage();
        apiMessage2 = new MessageConverter(apiMessage1).toAPIMessage();

        assertThat("Converter returned a null api object for a non null api object", apiMessage2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiMessage2, is(equalTo(apiMessage1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiMessage1 = createTypicalAPIMessage();
        sdkMessage1 = new MessageConverter(apiMessage1).toSDKMessage();

        assertThat("Converter returned a null sdk object for a non null api object", sdkMessage1, is(notNullValue()));
        assertThat("Message status was not correctly set", sdkMessage1.getStatus().toString(), is(equalTo(apiMessage1.getStatus().toString())));
        assertThat("Message content was not correctly set", sdkMessage1.getContent(), is(equalTo(apiMessage1.getContent())));
        assertThat("Message created date was not correctly set", sdkMessage1.getCreated(), is(apiMessage1.getCreated()));
        assertThat("Message from signer first name was not correctly set", sdkMessage1.getFrom().getFirstName(), is(equalTo(apiMessage1.getFrom().getFirstName())));
        assertThat("Message from signer last name was not correctly set", sdkMessage1.getFrom().getLastName(), is(equalTo(apiMessage1.getFrom().getLastName())));
        assertThat("Message from signer email was not correctly set", sdkMessage1.getFrom().getEmail(), is(equalTo(apiMessage1.getFrom().getEmail())));
        assertThat("Message to signer first name was not correctly set", sdkMessage1.getTo().get("email2@email.com").getFirstName(), is(equalTo(apiMessage1.getTo().get(0).getFirstName())));
        assertThat("Message to signer last name was not correctly set", sdkMessage1.getTo().get("email2@email.com").getLastName(), is(equalTo(apiMessage1.getTo().get(0).getLastName())));
        assertThat("Message to signer email was not correctly set", sdkMessage1.getTo().get("email2@email.com").getEmail(), is(equalTo(apiMessage1.getTo().get(0).getEmail())));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkMessage1 = createTypicalSDKMessage();
        apiMessage1 = new MessageConverter(sdkMessage1).toAPIMessage();

        assertThat("Converter returned a null sdk object for a non null api object", apiMessage1, is(notNullValue()));
        assertThat("Message status was not correctly set", apiMessage1.getStatus().toString(), is(equalTo(sdkMessage1.getStatus().toString())));
        assertThat("Message content was not correctly set", apiMessage1.getContent(), is(equalTo(sdkMessage1.getContent())));
        assertThat("Message created date was not correctly set", apiMessage1.getCreated(), is(sdkMessage1.getCreated()));
        assertThat("Message from signer first name was not correctly set", apiMessage1.getFrom().getFirstName(), is(equalTo(sdkMessage1.getFrom().getFirstName())));
        assertThat("Message from signer last name was not correctly set", apiMessage1.getFrom().getLastName(), is(equalTo(sdkMessage1.getFrom().getLastName())));
        assertThat("Message from signer email was not correctly set", apiMessage1.getFrom().getEmail(), is(equalTo(sdkMessage1.getFrom().getEmail())));
        assertThat("Message to signer first name was not correctly set", apiMessage1.getTo().get(0).getFirstName(), is(equalTo(sdkMessage1.getTo().get("email2@email.com").getFirstName())));
        assertThat("Message to signer last name was not correctly set", apiMessage1.getTo().get(0).getLastName(), is(equalTo(sdkMessage1.getTo().get("email2@email.com").getLastName())));
        assertThat("Message to signer email was not correctly set", apiMessage1.getTo().get(0).getEmail(), is(equalTo(sdkMessage1.getTo().get("email2@email.com").getEmail())));
    }

    private com.silanis.esl.sdk.Message createTypicalSDKMessage() {

        Signer fromSigner = SignerBuilder.newSignerWithEmail("email1@email.com")
                .withFirstName("John")
                .withLastName("Smith")
                .withCustomId("user1")
                .build();

        com.silanis.esl.sdk.Message sdkMessage = new com.silanis.esl.sdk.Message(MessageStatus.NEW, "Decline reason", fromSigner);

        sdkMessage.setCreated(new Date());

        Map<String, Signer> toSigners = new HashMap<String, Signer>();
        Signer toSigner = SignerBuilder.newSignerWithEmail("email2@email.com")
                .withFirstName("Patty")
                .withLastName("Galant")
                .withCustomId("user2")
                .build();
        toSigners.put(toSigner.getEmail(), toSigner);
        sdkMessage.setTo(toSigners);

        return sdkMessage;
    }

    private com.silanis.esl.api.model.Message createTypicalAPIMessage() {
        com.silanis.esl.api.model.Message apiMessage = new com.silanis.esl.api.model.Message();
        apiMessage.setContent("Opt-out reason");
        apiMessage.setStatus("READ");
        apiMessage.setCreated(new Date());

        User fromUser = new User();
        fromUser.setFirstName("John");
        fromUser.setLastName("Smith");
        fromUser.setId("user1");
        fromUser.setEmail("email1@email.com");
        apiMessage.setFrom(fromUser);

        List<User> toUsers = new ArrayList<User>();
        User toUser = new User();
        toUser.setFirstName("Patty");
        toUser.setLastName("Galant");
        toUser.setId("user2");
        toUser.setEmail("email2@email.com");
        toUsers.add(toUser);
        apiMessage.setTo(toUsers);

        return apiMessage;
    }
}
