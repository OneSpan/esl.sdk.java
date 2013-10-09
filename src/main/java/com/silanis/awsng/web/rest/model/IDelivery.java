package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IDelivery {
    public IDelivery setDownload( Boolean value);
    public Boolean getDownload();
    public IDelivery setEmail( Boolean value);
    public Boolean getEmail();
    public IDelivery setProvider( Boolean value);
    public Boolean getProvider();
    }