package com.silanis.esl.api.model;
//
import java.util.List;

public interface ICompletionReport extends IReport{
    public ICompletionReport setFrom( java.util.Date value);
    public java.util.Date getFrom();
    public ICompletionReport setSenders( List<SenderCompletionReport> value);
    public List<SenderCompletionReport> getSenders();
    public ICompletionReport setTo( java.util.Date value);
    public java.util.Date getTo();
    }