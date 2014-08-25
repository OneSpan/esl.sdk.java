package com.silanis.esl.sdk;

import java.util.Date;
import java.util.Map;

/**
 * Message class describes the message from signer to package owner. The message contains the signer's reason for
 * opting-out or declining package.
 */
public class Message {
    private String content;
    private Date created;
    private MessageStatus status;
    private Signer from;
    private Map<String, Signer> to;

    public Message(MessageStatus status, String content, Signer from) {
        this.status = status;
        this.content = content;
        this.from = from;
    }

    /**
     * Gets the reason for signer opting-out or declining package.
     *
     * @return the reason
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the date when the package was opted-out or declined by signer.
     *
     * @return the date the message was created
     */
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets the message's status.
     *
     * @return the message status
     */
    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    /**
     * Gets the signer who created the message.
     *
     * @return the from signer
     */
    public Signer getFrom() {
        return from;
    }

    public void setFrom(Signer from) {
        this.from = from;
    }

    /**
     * Gets a list of signers who this message is intended for.
     *
     * @return the list of to signers
     */
    public Map<String, Signer> getTo() {
        return to;
    }

    public void setTo(Map<String, Signer> to) {
        this.to = to;
    }
}
