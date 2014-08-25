package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lena on 2014-07-25.
 */
public class UsageReport {

    private Date from;
    private Date to;
    private List<SenderUsageReport> senderUsageReports = new ArrayList<SenderUsageReport>();

    public UsageReport() {
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

    public List<SenderUsageReport> getSenderUsageReports() {
        return senderUsageReports;
    }

    public void setSenderUsageReports(List<SenderUsageReport> senderUsageReports) {
        this.senderUsageReports = senderUsageReports;
    }

    public void addSenderUsageReport(SenderUsageReport senderUsageReport) {
        this.senderUsageReports.add(senderUsageReport);
    }
}
