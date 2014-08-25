package com.silanis.esl.sdk;

import java.util.EnumMap;

/**
 * Created by lena on 2014-07-25.
 */
public class SenderUsageReport {

    private EnumMap<UsageReportCategory, Integer> countByUsageReportCategory = new EnumMap<UsageReportCategory, Integer>(UsageReportCategory.class);
    private Sender sender;

    public EnumMap<UsageReportCategory, Integer> getCountByUsageReportCategory() {
        return countByUsageReportCategory;
    }

    public void setCountByUsageReportCategory(EnumMap<UsageReportCategory, Integer> countByUsageReportCategory) {
        this.countByUsageReportCategory = countByUsageReportCategory;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}
