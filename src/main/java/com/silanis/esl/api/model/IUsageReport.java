package com.silanis.esl.api.model;
//
import java.util.List;

public interface IUsageReport extends IReport{
    public IUsageReport setFrom( java.util.Date value);
    public java.util.Date getFrom();
    public IUsageReport setSenders( List<SenderUsageReport> value);
    public List<SenderUsageReport> getSenders();
    public IUsageReport setTo( java.util.Date value);
    public java.util.Date getTo();
    }