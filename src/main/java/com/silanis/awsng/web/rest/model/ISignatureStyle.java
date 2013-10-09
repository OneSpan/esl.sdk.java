package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISignatureStyle {
    public ISignatureStyle setHanddrawn( String value);
    public String getHanddrawn();
    public ISignatureStyle setTextual( TextualSignatureStyle value);
    public TextualSignatureStyle getTextual();
    }