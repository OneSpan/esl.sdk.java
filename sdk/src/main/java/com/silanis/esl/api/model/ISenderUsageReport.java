package com.silanis.esl.api.model;
//
import java.util.Map;

public interface ISenderUsageReport {
    public ISenderUsageReport setPackages( Map<String, Object> value);
    public Map<String, Object> getPackages();
    public ISenderUsageReport setSender( Sender value);
    public Sender getSender();
    }