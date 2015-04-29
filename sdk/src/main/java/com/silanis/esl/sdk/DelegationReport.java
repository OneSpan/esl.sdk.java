package com.silanis.esl.sdk;

import java.util.*;

/**
 * Created by schoi on 3/25/15.
 */
public class DelegationReport {

    private Map<String, List<DelegationEventReport>> delegationEventReports = new HashMap<String, List<DelegationEventReport>>();
    private Date from;
    private Date to;

    public Map<String, List<DelegationEventReport>> getDelegationEventReports() {
        return delegationEventReports;
    }

    public void setDelegationEventReports(Map<String, List<DelegationEventReport>> delegationEventReports) {
        this.delegationEventReports = delegationEventReports;
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
