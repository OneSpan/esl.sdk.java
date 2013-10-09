package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IAuthChallenge {
    public IAuthChallenge setAnswer( String value);
    public String getAnswer();
    public IAuthChallenge setMaskInput( Boolean value);
    public Boolean getMaskInput();
    public IAuthChallenge setQuestion( String value);
    public String getQuestion();
    }