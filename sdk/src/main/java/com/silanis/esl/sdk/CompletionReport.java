package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lena on 2014-05-27.
 */
public class CompletionReport {

    private Date from;
    private Date to;
    private List<SenderCompletionReport> senders = new ArrayList<SenderCompletionReport>();

    public CompletionReport() {
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public List<SenderCompletionReport> getSenders() {
        return senders;
    }

    public void setSenders(List<SenderCompletionReport> senders) {
        this.senders = senders;
    }

    public void addSender(SenderCompletionReport sender) {
        this.senders.add(sender);
    }
}
