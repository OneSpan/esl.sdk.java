package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICeremonySettings extends IViewSettings{
    public ICeremonySettings setDeclineButton( Boolean value);
    public Boolean getDeclineButton();
    public ICeremonySettings setDocumentToolbarOptions( DocumentToolbarOptions value);
    public DocumentToolbarOptions getDocumentToolbarOptions();
    public ICeremonySettings setEvents( CeremonyEvents value);
    public CeremonyEvents getEvents();
    public ICeremonySettings setHandOver( Link value);
    public Link getHandOver();
    public ICeremonySettings setHideCaptureText( Boolean value);
    public Boolean getHideCaptureText();
    public ICeremonySettings setHideWatermark( Boolean value);
    public Boolean getHideWatermark();
    public ICeremonySettings setInPerson( Boolean value);
    public Boolean getInPerson();
    public ICeremonySettings setLayout( LayoutOptions value);
    public LayoutOptions getLayout();
    public ICeremonySettings setMaxAuthFailsAllowed( Integer value);
    public Integer getMaxAuthFailsAllowed();
    public ICeremonySettings setOptOutButton( Boolean value);
    public Boolean getOptOutButton();
    public ICeremonySettings setOptOutReasons( List<String> value);
    public List<String> getOptOutReasons();
    public ICeremonySettings setStyle( LayoutStyle value);
    public LayoutStyle getStyle();
    }