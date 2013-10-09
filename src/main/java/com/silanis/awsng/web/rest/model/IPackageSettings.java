package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IPackageSettings extends ISettings{
    public IPackageSettings setCeremony( CeremonySettings value);
    public CeremonySettings getCeremony();
    }