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
public interface IUsageReport extends IReport{
    public IUsageReport setFrom( java.util.Date value);
    public java.util.Date getFrom();
    public IUsageReport setSenders( List<SenderUsageReport> value);
    public List<SenderUsageReport> getSenders();
    public IUsageReport setTo( java.util.Date value);
    public java.util.Date getTo();
    }