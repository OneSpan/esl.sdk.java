package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.User;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lena on 2014-07-03.
 */
public class MessageConverter {

    private com.silanis.esl.sdk.Message sdkMessage = null;
    private com.silanis.esl.api.model.Message apiMessage = null;

    /**
     * Construct with API Message object involved in conversion.
     *
     * @param apiMessage the API Message
     */
    public MessageConverter(com.silanis.esl.api.model.Message apiMessage) {
        this.apiMessage = apiMessage;
    }

    /**
     * Construct with SDK Message object involved in conversion.
     *
     * @param sdkMessage the SDK Message
     */
    public MessageConverter(com.silanis.esl.sdk.Message sdkMessage) {
        this.sdkMessage = sdkMessage;
    }

    /**
     * Convert from SDK Message to API Message.
     *
     * @return the API Message
     */
    public com.silanis.esl.api.model.Message toAPIMessage() {
        if (sdkMessage == null) {
            return apiMessage;
        }

        com.silanis.esl.api.model.Message result = new com.silanis.esl.api.model.Message();

        if (sdkMessage.getContent() != null) {
            result.setContent(sdkMessage.getContent());
        }

        if (sdkMessage.getFrom() != null) {
            Signer fromSigner = sdkMessage.getFrom();
            User fromUser = new User();
            fromUser.setEmail(fromSigner.getEmail());
            fromUser.setFirstName(fromSigner.getFirstName());
            fromUser.setLastName(fromSigner.getLastName());
            fromUser.setId(fromSigner.getId());
            fromUser.setCompany(fromSigner.getCompany());
            fromUser.setTitle(fromSigner.getTitle());

            result.setFrom(fromUser);
        }

        if (sdkMessage.getTo() != null && !sdkMessage.getTo().isEmpty()) {
            List<User> toUsers = new ArrayList<User>();
            for (Signer toSigner : sdkMessage.getTo().values()) {
                User toUser = new User();
                toUser.setEmail(toSigner.getEmail());
                toUser.setFirstName(toSigner.getFirstName());
                toUser.setLastName(toSigner.getLastName());
                toUser.setCompany(toSigner.getCompany());
                toUser.setTitle(toSigner.getTitle());

                toUsers.add(toUser);
            }
            result.setTo(toUsers);
        }

        if (sdkMessage.getCreated() != null) {
            result.setCreated(sdkMessage.getCreated());
        }

        if (sdkMessage.getStatus() != null) {
            result.setStatus(new MessageStatusConverter(sdkMessage.getStatus()).toAPIMessageStatus());
        }

        return result;
    }

    /**
     * Convert from API Message to SDK Message.
     *
     * @return the SDK Message
     */
    public com.silanis.esl.sdk.Message toSDKMessage() {
        if (apiMessage == null) {
            return sdkMessage;
        }

        User fromUser = apiMessage.getFrom();
        Signer fromSigner = SignerBuilder.newSignerWithEmail(fromUser.getEmail())
                .withCompany(fromUser.getCompany())
                .withFirstName(fromUser.getFirstName())
                .withLastName(fromUser.getLastName())
                .withCustomId(fromUser.getId())
                .withTitle(fromUser.getTitle())
                .build();

        com.silanis.esl.sdk.Message result = new com.silanis.esl.sdk.Message(
                new MessageStatusConverter(apiMessage.getStatus()).toSDKMessageStatus(),
                apiMessage.getContent(),
                fromSigner);

        if (apiMessage.getTo() != null && !apiMessage.getTo().isEmpty()) {
            Map<String, Signer> toSigners = new HashMap<String, Signer>();
            for (User toUser : apiMessage.getTo()) {
                Signer to = SignerBuilder.newSignerWithEmail(toUser.getEmail())
                        .withCompany(toUser.getCompany())
                        .withFirstName(toUser.getFirstName())
                        .withLastName(toUser.getLastName())
                        .withCustomId(toUser.getId())
                        .withTitle(toUser.getTitle())
                        .build();

                toSigners.put(to.getEmail().toLowerCase(), to);
            }

            result.setTo(toSigners);
        }

        if (apiMessage.getCreated() != null) {
            result.setCreated(apiMessage.getCreated());
        }

        return result;
    }
}
