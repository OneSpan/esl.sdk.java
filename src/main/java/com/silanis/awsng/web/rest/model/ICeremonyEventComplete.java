package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICeremonyEventComplete {
    public ICeremonyEventComplete setDialog( Boolean value);
    public Boolean getDialog();
    public ICeremonyEventComplete setRedirect( String value);
    public String getRedirect();
    }