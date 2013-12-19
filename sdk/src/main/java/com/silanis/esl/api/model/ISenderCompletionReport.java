package com.silanis.esl.api.model;
//
import java.util.List;

public interface ISenderCompletionReport {
    public ISenderCompletionReport setPackages( List<PackageCompletionReport> value);
    public List<PackageCompletionReport> getPackages();
    public ISenderCompletionReport setSender( Sender value);
    public Sender getSender();
    }