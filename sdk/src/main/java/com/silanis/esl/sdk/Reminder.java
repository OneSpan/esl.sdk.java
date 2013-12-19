package com.silanis.esl.sdk;

import java.util.Date;

/**
 * Represents a specific reminder either sent, or to be sent.
 */
public class Reminder {
    // The date when the reminder is slated to be sent
    private Date date;
    // The date when the reminder was actually sent -- null means it hasn't be sent yet!
    private Date sentDate;

    public Reminder( Date date, Date sentDate ) {
        this.date = date;
        this.sentDate = sentDate;
    }

    public Date getDate() {
        return date;
    }

    public Date getSentDate() {
        return sentDate;
    }
}
