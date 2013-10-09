package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ITextualSignatureStyle {
    public ITextualSignatureStyle setColor( String value);
    public String getColor();
    public ITextualSignatureStyle setFont( String value);
    public String getFont();
    }