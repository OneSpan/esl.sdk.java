package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by schoi on 3/25/15.
 */
public class DelegationReport {

    private List<DelegationEventReport> delegationEventReports = new ArrayList<DelegationEventReport>();
    private Date from;
    private Date to;

    public List<DelegationEventReport> getDelegationEventReports() {
        return delegationEventReports;
    }

    public void setDelegationEventReports(List<DelegationEventReport> delegationEventReports) {
        this.delegationEventReports = delegationEventReports;
    }

    public void addDelegationEventReports(DelegationEventReport delegationEventReport) {
        if (null == delegationEventReport) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.delegationEventReports.add(delegationEventReport);
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
}
