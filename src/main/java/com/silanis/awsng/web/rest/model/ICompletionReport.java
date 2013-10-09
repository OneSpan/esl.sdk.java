package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICompletionReport extends IReport{
    public ICompletionReport setFrom( java.util.Date value);
    public java.util.Date getFrom();
    public ICompletionReport setSenders( List<SenderCompletionReport> value);
    public List<SenderCompletionReport> getSenders();
    public ICompletionReport setTo( java.util.Date value);
    public java.util.Date getTo();
    }